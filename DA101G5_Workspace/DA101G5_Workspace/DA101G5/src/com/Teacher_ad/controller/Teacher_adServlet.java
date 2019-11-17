package com.Teacher_ad.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Teacher.model.TeacherVO;
import com.Teacher_ad.model.Teacher_adService;
import com.Teacher_ad.model.Teacher_adVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;



@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class Teacher_adServlet extends HttpServlet {

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
		
//		HttpSession session=req.getSession();
//		TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
//		Teacher_adVO teacher_adVO=(Teacher_adVO) session.getAttribute("teacher_adVO");
//		if(teacherVO.getTeacher_id()==teacher_adVO.getTeacher_id()) {
//			
//		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String teacher_ad_id = req.getParameter("teacher_ad_id");
				if (teacher_ad_id == null || (teacher_ad_id.trim()).length() == 0) {
					errorMsgs.add("請輸入教師廣告編號");
				}
				// Send the use back to the form, if there were errors
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_ad/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Teacher_adService teacher_adSvc = new Teacher_adService();
				Teacher_adVO teacher_adVO = teacher_adSvc.getOneTeacher_ad(teacher_ad_id);
				if (teacher_adVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_ad/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacher_adVO", teacher_adVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/teacher_ad/listOneTeacher_ad.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTeacher_ad.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_ad/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
//				String teacher_ad_id = req.getParameter("teacher_ad_id");
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
				Teacher_adVO teacher_adVO=(Teacher_adVO) session.getAttribute("teacher_adVO");
//System.out.println(teacherVO.getTeacher_id());
				
				
				/***************************2.開始查詢資料****************************************/
//				Teacher_adService teacher_adSvc = new Teacher_adService();
//				Teacher_adVO teacher_adVO = teacher_adSvc.getOneTeacher_ad(teacher_ad_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("teacher_adVO", teacher_adVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/teacher_ad/update_Teacher_ad_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Teacher_ad_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_ad/listAllTeacher_ad.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
				Teacher_adVO teacher_adVO=(Teacher_adVO) session.getAttribute("teacher_adVO");
				
				
				MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
				String teacher_id =teacherVO.getTeacher_id();
				Double point =memberVO.getMem_point();
				
				
//				String teacher_ad_id = req.getParameter("teacher_ad_id");
//				String teacher_ad_idReg = "^[T][A]\\d{5}$";
//				if (teacher_ad_id == null || (teacher_ad_id.trim()).length() == 0) {
//					errorMsgs.add("請輸入教師廣告編號");
//				}else if(!teacher_ad_id.trim().matches(teacher_ad_idReg)) { 
//					System.out.println(teacher_ad_id);
//					errorMsgs.add("教師廣告編號: TAxxxxx");
//				}				
				
//				String teacher_ad_id=teacher_adVO.getTeacher_ad_id();

				
//				String teacher_id = req.getParameter("teacher_id");
//				String teacher_id=teacherVO.getTeacher_id();
				
//System.out.println(teacher_id);
				
				String ad_time1 = req.getParameter("ad_time").trim();
				if (ad_time1 == null || ad_time1.trim().length() == 0) {
					errorMsgs.add("廣告時間請勿空白");
				}
				Integer ad_time =new Integer(ad_time1);
				
				java.sql.Timestamp ad_start = null;
//System.out.println(req.getParameter("ad_start").trim());
				
				try {
					ad_start = java.sql.Timestamp.valueOf(req.getParameter("ad_start").trim()+" 00:00:00");
				} catch (IllegalArgumentException e) {
					ad_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請選擇開始廣告日期!");
				}
				
				int pastAdTime=new Integer(teacher_adVO.getAd_time()*60*60*24*1000);
				long pastAdStart=teacher_adVO.getAd_start().getTime();
//				+teacher_adVO.getAd_start().getTime();
//				Long end_time=ad_time*1000*60*60*24+ad_start.getTime();
//				System.out.println(System.currentTimeMillis());
//				System.out.println(end_time);
				if((pastAdTime+pastAdStart)>System.currentTimeMillis()) {
					System.out.println("end_time: "+(pastAdTime+pastAdStart));
					System.out.println("System.currentTimeMillis(): "+System.currentTimeMillis());
					errorMsgs.add("廣告時間上未使用完");
				}
			
				
//				Integer ad_state =new Integer(0);

//				Teacher_adVO teacher_adVO = new Teacher_adVO();
//				teacher_adVO.setTeacher_ad_id(teacher_ad_id);
//				teacher_adVO.setTeacher_id(teacher_id);
				Integer tempTime=teacher_adVO.getAd_time();
//				System.out.println(tempTime);
				Double newPoint=(point-ad_time*100);
				
				if(newPoint<0) {
					errorMsgs.add("點數不足");
				}
				
				
//---------------------------------------				
				
//				ad_time=tempTime+ad_time;
				
//				teacher_adVO.setAd_state(ad_state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacher_adVO", teacher_adVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_ad/update_Teacher_ad_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				teacher_adVO.setAd_time(ad_time);
				
				teacher_adVO.setAd_start(ad_start);
				memberVO.setMem_point(newPoint);
				/***************************2.開始修改資料*****************************************/
				
				
				Teacher_adService teacher_adSvc = new Teacher_adService();
				teacher_adSvc.updateTeacher_ad(teacher_adVO);
				
				MemberService memberService=new MemberService();
				memberService.updateMember(memberVO);
				errorMsgs.add("下個發大財的就是你!!!");
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacher_adVO", teacher_adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/teacher_ad/update_Teacher_ad_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			}
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_ad/update_Teacher_ad_input.jsp");
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
				TeacherVO teacherVO=(TeacherVO)req.getAttribute("teacherVO");
				String teacher_id=teacherVO.getTeacher_id();
//				String teacher_id = req.getParameter("teacher_id");
				
				
				String ad_time1 = req.getParameter("ad_time").trim();
				if (ad_time1 == null || ad_time1.trim().length() == 0) {
					errorMsgs.add("廣告時間請勿空白");
				}
				Integer ad_time =new Integer(ad_time1);
//				------------------
				
				java.sql.Timestamp ad_start = null;
				try {

					ad_start = java.sql.Timestamp.valueOf(req.getParameter("ad_start").trim());

				
				} 
        catch (IllegalArgumentException e) {
					ad_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請重新輸入開始廣告日期!");
				}
//				--------------
//				java.sql.Timestamp ad_start = java.sql.Timestamp.valueOf(req.getParameter("ad_start").trim());
				
			
				String ad_state1 = req.getParameter("ad_state").trim();
				if (ad_state1 == null || ad_state1.trim().length() == 0) {
					errorMsgs.add("廣告狀態請勿空白");
				}
				Integer ad_state =new Integer(ad_state1);

				Teacher_adVO teacher_adVO = new Teacher_adVO();
				teacher_adVO.setTeacher_id(teacher_id);
				teacher_adVO.setAd_time(ad_time);
			
				teacher_adVO.setAd_start(ad_start);
				teacher_adVO.setAd_state(ad_state);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacher_adVO", teacher_adVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/teacher_ad/addTeacher_ad.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Teacher_adService teacher_adSvc = new Teacher_adService();
				teacher_adVO = teacher_adSvc.addTeacher_ad(teacher_id, ad_time, ad_start, ad_state);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/teacher_ad/listAllTeacher_ad.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
        catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_ad/addTeacher_ad.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String teacher_ad_id = req.getParameter("teacher_ad_id");
				
				/***************************2.開始刪除資料***************************************/
				Teacher_adService teacher_adSvc = new Teacher_adService();
				teacher_adSvc.deleteTeacher_ad(teacher_ad_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/teacher_ad/listAllTeacher_ad.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_ad/listAllTeacher_ad.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	
}
