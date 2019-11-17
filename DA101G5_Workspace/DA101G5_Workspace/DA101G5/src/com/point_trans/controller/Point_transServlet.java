package com.point_trans.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.point_trans.model.Point_transService;
import com.point_trans.model.Point_transVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_trans.model.*;

public class Point_transServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String str = req.getParameter("point_trans_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入點數交易編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/point_trans/select_point_trans.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String point_trans_id = null;
				try {
					point_trans_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("點數交易編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/point_trans/select_point_trans.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Point_transService point_transSvc = new Point_transService();
				Point_transVO point_transVO = point_transSvc.getOnePoint_trans(point_trans_id);
				if (point_transVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/point_trans/select_point_trans.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("point_transVO", point_transVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/point_trans/listOnePoint_trans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/point_trans/select_point_trans.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String point_trans_id = new String(req.getParameter("point_trans_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Point_transService point_transSvc = new Point_transService();
				Point_transVO point_transVO = point_transSvc.getOnePoint_trans(point_trans_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("point_transVO", point_transVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/point_trans/update_point_trans_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/point_trans/listAllPoint_trans.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_member_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String point_trans_id = req.getParameter("point_trans_id");
				if (point_trans_id == null || point_trans_id.trim().length() == 0) {
					errorMsgs.add("交易編號請勿空白");
				}
				String member_id = req.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
//				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

				Double point_record = null;
				try {
					point_record = new Double(req.getParameter("point_record").trim());
				} catch (NumberFormatException e) {
					point_record = 0.0;
					errorMsgs.add("點數紀錄請填數字.");
				}
				java.sql.Date transdate = null;
				try {
					transdate = java.sql.Date.valueOf(req.getParameter("transdate").trim());
				} catch (IllegalArgumentException e) {
					transdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入交易日期!");
				}

				Point_transVO point_transVO = new Point_transVO();
				point_transVO.setPoint_trans_id(point_trans_id);
				point_transVO.setMember_id(member_id);
				point_transVO.setPoint_record(point_record);
				point_transVO.setTransdate(transdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("point_transVO", point_transVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/point_trans/update_point_trans_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Point_transService point_transSvc = new Point_transService();
				point_transVO = point_transSvc.updatePoint_trans(point_trans_id, member_id, point_record, transdate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("point_transVO", point_transVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/point_trans/listOnePoint_trans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/point_trans/update_point_trans_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String member_id=null;

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {

				member_id = req.getParameter("member_id").trim();
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}

				Double point_record = null;
				try {
					point_record = new Double(req.getParameter("point_record").trim());
				} catch (NumberFormatException e) {
					point_record = 0.0;
					errorMsgs.add("點數紀錄請填數字.");
				}

				java.sql.Date transdate = null;
				transdate = new java.sql.Date(System.currentTimeMillis());

				Point_transVO point_transVO = new Point_transVO();

				point_transVO.setMember_id(member_id);
				point_transVO.setPoint_record(point_record);
				point_transVO.setTransdate(transdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("point_transVO", point_transVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/point_trans/addPoint_trans.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Point_transService point_transSvc = new Point_transService();
				point_transVO = point_transSvc.addPoint_trans(member_id, point_record, transdate);

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(member_id);
				HttpSession session = req.getSession();
				session.setAttribute("memberVO", memberVO);
				
				
				
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/point_trans/point_transAllByOneId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/point_trans/addPoint_trans.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String point_trans_id = new String(req.getParameter("point_trans_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Point_transService point_transSvc = new Point_transService();
				point_transSvc.deletePoint_trans(point_trans_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/point_trans/listAllPoint_trans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/point_trans/listAllPoint_trans.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
