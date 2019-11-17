package com.card.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.card.model.CardService;
import com.card.model.CardVO;
import com.card.model.*;

public class CardServlet extends HttpServlet {

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
				String str = req.getParameter("card_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入Card編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String card_id = null;
				try {
					card_id = str;
				} catch (Exception e) {
					errorMsgs.add("Card編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CardService cardSvc = new CardService();
				CardVO cardVO = cardSvc.getOneCard(card_id);
				if (cardVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cardVO", cardVO); // 資料庫取出的cardVO物件,存入req
				String url = "/card/listOneCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCard.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCard.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String card_id = req.getParameter("card_id");
				
				/***************************2.開始查詢資料****************************************/
				CardService cardSvc = new CardService();
				CardVO cardVO = cardSvc.getOneCard(card_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cardVO", cardVO);         // 資料庫取出的cardVO物件,存入req
				String url = "/card/update_card_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_card_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/listAllCard.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_card_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String card_id = req.getParameter("card_id");
				
				String teacher_id = req.getParameter("teacher_id");
				
				String word = req.getParameter("word").trim();

				//String wordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,;.)]{0,30}$";
				String wordReg = "^[(a-zA-Z)]{0,50}$";
				if (word == null || word.trim().length() == 0) {
					errorMsgs.add("單字: 請勿空白");
				} else if(!word.trim().matches(wordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("單字只能是英文字母, 且長度必需在0到10之間");
	            }
				
				String phonetic_symbol = req.getParameter("phonetic_symbol").trim();
				
				String native_explain = req.getParameter("native_explain").trim();
				
				String example = req.getParameter("example").trim();
				
				CardVO cardVO = new CardVO();
				cardVO.setCard_id(card_id);
				cardVO.setTeacher_id(teacher_id);
				cardVO.setWord(word);
				cardVO.setPhonetic_symbol(phonetic_symbol);
				cardVO.setVoice(null);
				cardVO.setNative_explain(native_explain);
				cardVO.setExample(example);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cardVO", cardVO); // 含有輸入格式錯誤的cardVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/update_card_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CardService cardSvc = new CardService();
				cardVO = cardSvc.updateCard(card_id, teacher_id, word, phonetic_symbol, null, native_explain, example);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cardVO", cardVO); // 資料庫update成功後,正確的的cardVO物件,存入req
				String url = "/card/listOneCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCard.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/update_card_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addCard.jsp的請求  
			
        	
        	
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String teacher_id = req.getParameter("teacher_id");
				
				String word = req.getParameter("word").trim();

				//String wordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,;.)]{0,30}$";
				String wordReg = "^[(a-zA-Z)]{0,50}$";
				if (word == null || word.trim().length() == 0) {
					errorMsgs.add("單字: 請勿空白");
				} else if(!word.trim().matches(wordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("單字只能是英文字母, 且長度必需在0到10之間");
	            }
				
				String phonetic_symbol = req.getParameter("phonetic_symbol").trim();
				
				String native_explain = req.getParameter("native_explain").trim();
				
				String example = req.getParameter("example").trim();
				
				CardVO cardVO = new CardVO();
				cardVO.setTeacher_id(teacher_id);
				cardVO.setWord(word);
				cardVO.setPhonetic_symbol(phonetic_symbol);
				cardVO.setVoice(null);
				cardVO.setNative_explain(native_explain);
				cardVO.setExample(example);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cardVO", cardVO); // 含有輸入格式錯誤的cardVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/addCard.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CardService cardSvc = new CardService();
				cardVO = cardSvc.addCard(teacher_id, word, phonetic_symbol, null, native_explain, example);
				System.out.println(cardVO.getWord());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/card/listAllCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCard.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/addCard.jsp");
				failureView.forward(req, res);
			}
			
			
			
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCard.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String card_id = req.getParameter("card_id");
				
				/***************************2.開始刪除資料***************************************/
				CardService cardSvc = new CardService();
				cardSvc.deleteCard(card_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/card/listAllCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/listAllCard.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
