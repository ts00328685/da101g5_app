package com.commodity.controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.commodity.model.*;
import com.commodity.model.MailService;
import com.member.model.*;
import com.point_trans.model.Point_transService;
import com.point_trans.model.Point_transVO;

@MultipartConfig
public class CommodityServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		MemberVO memberVO = null;

		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("com_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入Commodity編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String com_id = null;
				try {
					com_id = str;
				} catch (Exception e) {
					errorMsgs.add("Commodity編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CommodityService commoditySvc = new CommodityService();
				CommodityVO commodityVO = commoditySvc.getOneCommodity(com_id);
				if (commodityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commodityVO", commodityVO); // 資料庫取出的commodityVO物件,存入req
				String url = "/front-end/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCommodity.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCommodity.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			String com_id = req.getParameter("com_id");

			/*************************** 2.開始查詢資料 ****************************************/
			CommodityService commoditySvc = new CommodityService();
			CommodityVO commodityVO = commoditySvc.getOneCommodity(com_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("commodityVO", commodityVO); // 資料庫取出的commodityVO物件,存入req
			String url = "/front-end/commodity/update_commodity_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_commodity_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/commodity/listAllCommodity.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 來自update_commodity_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String com_id = req.getParameter("com_id");
				String com_description = req.getParameter("com_description").trim();
				String member_id = req.getParameter("member_id").trim();
				Part com_pic = req.getPart("com_pic");
				String com_download = req.getParameter("com_download").trim();
				String com_price = req.getParameter("com_price");
				String com_detail = req.getParameter("com_detail").trim();
				CommodityVO commodityVO = new CommodityVO();
				
				
				
				commodityVO.setCom_id(com_id);
				commodityVO.setCom_description(com_description);
				commodityVO.setMember_id(member_id);
				
				InputStream is = com_pic.getInputStream();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						int nRead;
						byte[] data = new byte[is.available()];
						while ((nRead = is.read(data, 0, data.length)) != -1) {
						  buffer.write(data, 0, nRead);
						}
				commodityVO.setCom_pic(data);
				commodityVO.setCom_download(com_download);
				commodityVO.setCom_price(Integer.valueOf(com_price));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				java.sql.Timestamp sqlTimestamp = null;
				try {
					sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
				commodityVO.setTrandate(sqlTimestamp);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				commodityVO.setCom_description(com_detail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commodityVO", commodityVO); // 含有輸入格式錯誤的commodityVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/commodity/update_commodity_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CommodityService commoditySvc = new CommodityService();
				commodityVO = commoditySvc.updateCommodity(com_id,member_id, com_description, 
						data, com_download, Integer.valueOf(com_price),
						sqlTimestamp, 1, 0.0D, 0, com_description, com_detail);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("commodityVO", commodityVO); // 資料庫update成功後,正確的的commodityVO物件,存入req
				String url = "/front-end/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCommodity.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/commodity/update_commodity_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addCommodity.jsp的請求  
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				//String wordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,;.)]{0,30}$";
//				String wordReg = "^[(a-zA-Z)]{0,50}$";
//				if (word == null || word.trim().length() == 0) {
//					errorMsgs.add("單字: 請勿空白");
//				} else if(!word.trim().matches(wordReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("單字只能是英文字母, 且長度必需在0到10之間");
//	            }
				String com_description = req.getParameter("com_description").trim();
				
				String member_id = req.getParameter("member_id").trim();
				
				
				
				Part com_pic = req.getPart("com_pic");
				String com_download = req.getParameter("com_download").trim();
				String com_price = req.getParameter("com_price");
				String com_detail = req.getParameter("com_detail").trim();
				
				CommodityVO commodityVO = new CommodityVO();
				
				commodityVO.setCom_description(com_description);
				commodityVO.setMember_id(member_id);
				
				InputStream is = com_pic.getInputStream();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						int nRead;
						byte[] data = new byte[is.available()];
						while ((nRead = is.read(data, 0, data.length)) != -1) {
						  buffer.write(data, 0, nRead);
						}
				commodityVO.setCom_pic(data);
				commodityVO.setCom_download(com_download);
				commodityVO.setCom_price(Integer.valueOf(com_price));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				java.sql.Timestamp sqlTimestamp = null;
				try {
					sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
				commodityVO.setTrandate(sqlTimestamp);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				commodityVO.setCom_detail(com_detail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commodityVO", commodityVO); // 含有輸入格式錯誤的commodityVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/commodity/addCommodity.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CommodityService commoditySvc = new CommodityService();
				commodityVO = commoditySvc.addCommodity(member_id, com_description, data, com_download, Integer.valueOf(com_price),
						sqlTimestamp, 1, 0.0D, 0, commodityVO.getE_des(), com_detail);
				System.out.println(commodityVO.toString());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/commodity/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCommodity.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/commodity/addCommodity.jsp");
//				failureView.forward(req, res);
//			}
			
			
			
		}

		if ("delete".equals(action)) { // 來自listAllCommodity.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String com_id = req.getParameter("com_id");
				
				/***************************2.開始刪除資料***************************************/
				CommodityService commoditySvc = new CommodityService();
				commoditySvc.deleteCommodity(com_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/commodity/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/commodity/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("checkout".equals(action)) { // 來自Eshop的請求
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			memberVO = (MemberVO) session.getAttribute("memberVO");

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			try {
			List<String> errorMsgs = new LinkedList<String>();
			MemberService membersvc = new MemberService();
			Double point_owner = (membersvc.getOneMember(memberVO.getMember_id())).getMem_point();
			CommodityService commoditysvc = new CommodityService();
			
			String com_id = req.getParameter("com_id");
			String com_download = req.getParameter("com_download");
			
			CommodityVO commodityVO = commoditysvc.getOneCommodity(com_id);
			
			Integer comP = (commoditysvc.getOneCommodity(commodityVO.getCom_id())).getCom_price();
			
			

//			}catch (Exception e) {
//						errorMsgs.add("無法取得資料唷 ");
//						e.printStackTrace();
//						RequestDispatcher failure = req.getRequestDispatcher("/front-end/commodity/Eshop.jsp");
//						failure.forward(req, res);				
//			}

			/*************************** 2.開始修改資料 *****************************************/
			if (point_owner >= comP) {
				System.out.println("point_owner >= comP -> " + (point_owner >= comP));
				membersvc.updateMember(memberVO.getMember_id(), memberVO.getMem_name(), memberVO.getMem_pwd(),
						memberVO.getMem_nick(), memberVO.getMem_email(), memberVO.getMem_birthday(),
						memberVO.getMem_mobile(), memberVO.getMem_city(), memberVO.getMem_country(),
						memberVO.getMem_sex(), memberVO.getMem_status(), memberVO.getFriend_pic(),
						memberVO.getFriend_profile(), memberVO.getFriend_choose(), memberVO.getFriend_appli(),
						point_owner - comP, memberVO.getCouponqty(), memberVO.getMem_profile(),
						memberVO.getMem_createtime(), memberVO.getMem_pic());
				memberVO.setMem_point(point_owner - comP);
			} else {errorMsgs.add("沒錢可以購物");}
			
			if (!errorMsgs.isEmpty()) {
				System.out.println("!errorMsgs.isEmpty()");
				RequestDispatcher failure = req.getRequestDispatcher("/front-end/commodity/shop_index.jsp");
				failure.forward(req, res);
				return;
			}
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
			req.setAttribute("commodityVO", commodityVO);
			
			String url = "/front-end/commodity/Eshop.jsp"; /* 訂單的jsp */
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCommodity.jsp
			successView.forward(req, res);
			
			String to = memberVO.getMem_email();
			String mem = memberVO.getMem_name();
		    String subject = "筆記商城購物連結請查收"; 
		    String ch_name = memberVO.getMem_name();
		    String download = commodityVO.getCom_download();
		    String messageText = "Hello! " + ch_name + " 您所購買的筆記下載連結如下: " +"\n"+ download; 
		       
		      MailService mailService = new MailService();
		      mailService.sendMail(to, subject, messageText);
		      System.out.println("emailget");
			/*************************** 其他可能的錯誤處理 *************************************/

			

//			} catch (Exception e) {
//				List<String> errorMsgs = new LinkedList<String>();
//				errorMsgs.add("購買失敗:"+e.getMessage());
//				System.out.println("購買失敗");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/commodity/Eshop.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
}
