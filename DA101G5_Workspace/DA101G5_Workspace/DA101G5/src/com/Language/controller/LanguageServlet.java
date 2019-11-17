package com.Language.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Language.model.LanguageService;
import com.Language.model.LanguageVO;


@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class LanguageServlet extends HttpServlet{

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
				String language_id = req.getParameter("language_id");
				String language_idReg = "^[L]\\d{5}$";
				if (language_id == null || (language_id.trim()).length() == 0) {
					errorMsgs.add("請輸入語言編號");
				}else if(!language_id.trim().matches(language_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("語言編號: Lxxxxx");
		         }
				// Send the use back to the form, if there were errors
								
//				String language = null;
//				if (language == null || (language.trim()).length() == 0) {
//					errorMsgs.add("請輸入語言");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LanguageService LanguageSvc = new LanguageService();
				LanguageVO languageVO = LanguageSvc.getOneLanguage(language_id);
				if (languageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("languageVO", languageVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/language/listOneLanguage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneLanguage.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/language/select_page.jsp");
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
				String language_id = req.getParameter("language_id");
				
				/***************************2.開始查詢資料****************************************/
				LanguageService LanguageSvc = new LanguageService();
				LanguageVO languageVO = LanguageSvc.getOneLanguage(language_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("languageVO", languageVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/language/update_Language_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Language_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/language/listAllLanguage.jsp");
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
				String language_id = req.getParameter("language_id");
				String language_idReg = "^[L]\\d{5}$";
				if (language_id == null || (language_id.trim()).length() == 0) {
					errorMsgs.add("請輸入語言編號");
				}else if(!language_id.trim().matches(language_idReg)) { 
					errorMsgs.add("語言編號: Lxxxxx");
				}
				
				
				String language = req.getParameter("language");
				if (language == null || (language.trim()).length() == 0) {
				errorMsgs.add("請輸入語言");
				}
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/addLanguage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
			
				LanguageVO languageVO = new LanguageVO();
				languageVO.setLanguage_id(language_id);
				languageVO.setLanguage(language);
				 

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("languageVO", languageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/update_Language_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LanguageService LanguageSvc = new LanguageService();
				languageVO = LanguageSvc.updateLanguage(language_id, language);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("languageVO", languageVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/language/listOneLanguage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/language/update_Language_input.jsp");
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
				
									
				String language = req.getParameter("language");
				if (language == null || (language.trim()).length() == 0) {
				errorMsgs.add("請輸入語言");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				LanguageVO languageVO = new LanguageVO();
				languageVO.setLanguage(language);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("languageVO", languageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/language/addLanguage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LanguageService LanguageSvc = new LanguageService();
				languageVO = LanguageSvc.addLanguage(language);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/language/listAllLanguage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllLanguage.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/language/addLanguage.jsp");
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
				String language_id = req.getParameter("language_id");
				
				/***************************2.開始刪除資料***************************************/
				LanguageService LanguageSvc = new LanguageService();
				LanguageSvc.deleteLanguage(language_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/language/listAllLanguage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/language/listAllLanguage.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

}
