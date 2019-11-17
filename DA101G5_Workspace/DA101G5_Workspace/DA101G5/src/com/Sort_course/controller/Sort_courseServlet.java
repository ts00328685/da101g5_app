package com.Sort_course.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Sort_course.model.Sort_courseService;
import com.Sort_course.model.Sort_courseVO;


@MultipartConfig(fileSizeThreshold=1224*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)

public class Sort_courseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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
				String sort_course_id = req.getParameter("sort_course_id");
				String language_idReg = "^[S][C]\\d{5}$";
				if (sort_course_id == null || (sort_course_id.trim()).length() == 0) {
					errorMsgs.add("請輸入課程種類編號");
				}else if(!sort_course_id.trim().matches(language_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程種類編號: SCxxxxx");
		         }
				// Send the use back to the form, if there were errors
								
//				String sort_course = null;
//				if (sort_course == null || (sort_course.trim()).length() == 0) {
//					errorMsgs.add("請輸入課程種類");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Sort_courseService Sort_courseSvc = new Sort_courseService();
				Sort_courseVO sort_courseVO = Sort_courseSvc.getOneSort_course(sort_course_id);
				if (sort_courseVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sort_courseVO", sort_courseVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/sort_course/listOneSort_course.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSort_course.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sort_course/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllLanguage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String sort_course_id = req.getParameter("sort_course_id");
				
				/***************************2.開始查詢資料****************************************/
				Sort_courseService Sort_courseSvc = new Sort_courseService();
				Sort_courseVO sort_courseVO = Sort_courseSvc.getOneSort_course(sort_course_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sort_courseVO", sort_courseVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/sort_course/update_Sort_course_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Sort_course_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sort_course/listAllSort_course.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Language_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String sort_course_id = req.getParameter("sort_course_id");
				String language_idReg = "^[S][C]\\d{5}$";
				if (sort_course_id == null || (sort_course_id.trim()).length() == 0) {
					errorMsgs.add("請輸入課程種類編號");
				}else if(!sort_course_id.trim().matches(language_idReg)) { 
					errorMsgs.add("課程種類編號: SCxxxxx");
				}
				
				
				String sort_course = req.getParameter("sort_course");
				if (sort_course == null || (sort_course.trim()).length() == 0) {
				errorMsgs.add("請輸入課程種類");
				}
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/addSort_course.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
			
				Sort_courseVO sort_courseVO = new Sort_courseVO();
				sort_courseVO.setSort_course_id(sort_course_id);
				sort_courseVO.setSort_course(sort_course);
				 

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sort_courseVO", sort_courseVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/update_Sort_course_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Sort_courseService Sort_courseSvc = new Sort_courseService();
				sort_courseVO = Sort_courseSvc.updateSort_course(sort_course_id, sort_course);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sort_courseVO", sort_courseVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/sort_course/listOneSort_course.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sort_course/update_Sort_course_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
									
				String sort_course = req.getParameter("sort_course");
				if (sort_course == null || (sort_course.trim()).length() == 0) {
				errorMsgs.add("請輸入課程種類");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Sort_courseVO sort_courseVO = new Sort_courseVO();
				sort_courseVO.setSort_course(sort_course);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sort_courseVO", sort_courseVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sort_course/addSort_course.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Sort_courseService Sort_courseSvc = new Sort_courseService();
				sort_courseVO = Sort_courseSvc.addSort_course(sort_course);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/sort_course/listAllSort_course.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllLanguage.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sort_course/addSort_course.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllLanguage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String sort_course_id = req.getParameter("sort_course_id");
				
				/***************************2.開始刪除資料***************************************/
				Sort_courseService Sort_courseSvc = new Sort_courseService();
				Sort_courseSvc.deleteSort_course(sort_course_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/sort_course/listAllSort_course.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sort_course/listAllSort_course.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	

}
