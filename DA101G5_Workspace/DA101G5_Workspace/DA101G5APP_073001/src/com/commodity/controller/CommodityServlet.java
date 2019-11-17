package com.commodity.controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.commodity.model.*;
import com.member.model.*;
@MultipartConfig
public class CommodityServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("com_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入Commodity編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String com_id = null;
				try {
					com_id = str;
				} catch (Exception e) {
					errorMsgs.add("Commodity編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CommodityService commoditySvc = new CommodityService();
				CommodityVO commodityVO = commoditySvc.getOneCommodity(com_id);
				if (commodityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("commodityVO", commodityVO); // 資料庫取出的commodityVO物件,存入req
				String url = "/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCommodity.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/commodity/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCommodity.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String com_id = req.getParameter("com_id");
				
				/***************************2.開始查詢資料****************************************/
				CommodityService commoditySvc = new CommodityService();
				CommodityVO commodityVO = commoditySvc.getOneCommodity(com_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("commodityVO", commodityVO);         // 資料庫取出的commodityVO物件,存入req
				String url = "/commodity/update_commodity_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_commodity_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commodityVO", commodityVO); // 含有輸入格式錯誤的commodityVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/commodity/update_commodity_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CommodityService commoditySvc = new CommodityService();
				commodityVO = commoditySvc.updateCommodity(com_id,member_id, com_description, 
						data, com_download, Integer.valueOf(com_price),
						sqlTimestamp, 1, 0.0D, 0, com_description);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("commodityVO", commodityVO); // 資料庫update成功後,正確的的commodityVO物件,存入req
				String url = "/commodity/listOneCommodity.jsp";
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

			try {
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commodityVO", commodityVO); // 含有輸入格式錯誤的commodityVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/commodity/addCommodity.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CommodityService commoditySvc = new CommodityService();
				commodityVO = commoditySvc.addCommodity(member_id, com_description, data, com_download, Integer.valueOf(com_price),
						sqlTimestamp, 1, 0.0D, 0, com_description);
				System.out.println(commodityVO.toString());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/commodity/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCommodity.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/commodity/addCommodity.jsp");
				failureView.forward(req, res);
			}
			
			
			
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
				String url = "/commodity/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/commodity/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
