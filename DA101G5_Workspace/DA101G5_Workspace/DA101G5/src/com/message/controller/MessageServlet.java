package com.message.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberVO;
import com.message.model.MessageService;
import com.message.model.MessageVO;


//@WebServlet("/message.do") //檔案上傳要加這兩行
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MessageServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
//		HttpSession session=req.getSession();
//		MessageVO messageVO=(MessageVO)session.getAttribute("messageVO");
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("message_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訊息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/select_message.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String message_id = null;
				try {
					message_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訊息編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/select_messages.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = messageSvc.getOneMessage(message_id);
				if (messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/select_message.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO", messageVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/message/select_message.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String message_id = new String(req.getParameter("message_id"));
				
				/***************************2.開始查詢資料****************************************/
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = messageSvc.getOneMessage(message_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("messageVO", messageVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				HttpSession session=req.getSession();
//				MessageVO messageVO=(MessageVO)session.getAttribute("messageVO");
				
				String message_id = req.getParameter("message_id");

				String member_id = req.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String member_id2 = req.getParameter("member_id2");
				if (member_id2 == null || member_id2.trim().length() == 0) {
					errorMsgs.add("對方會員編號請勿空白");
				}
//				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				java.sql.Date memmsg_date = null;
				try {
					memmsg_date = java.sql.Date.valueOf(req.getParameter("memmsg_date").trim());
				} catch (IllegalArgumentException e) {
					memmsg_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訊息日期!");
				}
				
				String memmsg_ent = new String(req.getParameter("memmsg_ent").trim());
				if (memmsg_ent == null || memmsg_ent.trim().length() == 0) {
					errorMsgs.add("訊息內容請勿空白");
				}
				
				Integer memmsg_state = new Integer(req.getParameter("memmsg_state").trim());
//				if (memmsg_state == null || memmsg_state.trim().length() == 0) {
//					errorMsgs.add("訊息狀態請勿空白");
//				}
			
//				Part part = req.getPart("friend_pic");
//				InputStream in = part.getInputStream();
//				byte[] friend_pic = new byte[in.available()];
//				in.read(friend_pic);
//				in.close();
			

				MessageVO messageVO = new MessageVO();
				messageVO.setMessage_id(message_id);
				messageVO.setMember_id(member_id);
				messageVO.setMember_id2(member_id2);
				messageVO.setMemmsg_date(memmsg_date);
				messageVO.setMemmsg_ent(memmsg_ent);
				messageVO.setMemmsg_state(memmsg_state);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/update_message_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageService messageSvc = new MessageService();
				messageVO = messageSvc.updateMessage(message_id,  member_id, member_id2, memmsg_date, memmsg_ent, memmsg_state 
				);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO", messageVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/message/update_message_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			try {	
//				String memmsg_id = req.getParameter("memmsg_id");
				
				String member_id = req.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String member_id2 = req.getParameter("member_id2");
				if (member_id2 == null || member_id2.trim().length() == 0) {
					errorMsgs.add("對方會員編號請勿空白");
				}
//				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				java.sql.Date memmsg_date = null;
				try {
					memmsg_date = java.sql.Date.valueOf(req.getParameter("memmsg_date").trim());
				} catch (IllegalArgumentException e) {
					memmsg_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訊息日期!");
				}
				
				String memmsg_ent = new String(req.getParameter("memmsg_ent").trim());
				if (memmsg_ent == null || memmsg_ent.trim().length() == 0) {
					errorMsgs.add("訊息內容請勿空白");
				}
				
				Integer memmsg_state = new Integer(req.getParameter("memmsg_state").trim());
//				if (memmsg_state == null || memmsg_state.trim().length() == 0) {
//					errorMsgs.add("訊息狀態請勿空白");
//				}
				
//				Part part = req.getPart("friend_pic");
//				InputStream in = part.getInputStream();
//				byte[] friend_pic = new byte[in.available()];
//				in.read(friend_pic);
//				in.close();
		
				MessageVO messageVO = new MessageVO();
				
				messageVO.setMember_id(member_id);
				messageVO.setMember_id2(member_id2);
				messageVO.setMemmsg_date(memmsg_date);
				messageVO.setMemmsg_ent(memmsg_ent);
				messageVO.setMemmsg_state(memmsg_state);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MessageService messageSvc = new MessageService();
				messageVO = messageSvc.addMessage(member_id, member_id2, memmsg_date, memmsg_ent, memmsg_state);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/message/addMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String message_id = new String(req.getParameter("message_id"));
				
				/***************************2.開始刪除資料***************************************/
				MessageService messageSvc = new MessageService();
				messageSvc.deleteMessage(message_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
