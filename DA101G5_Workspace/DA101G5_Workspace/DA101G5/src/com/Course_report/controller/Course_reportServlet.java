package com.Course_report.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Course_report.model.Course_reportService;
import com.Course_report.model.Course_reportVO;
import com.Teacher.model.TeacherService;
import com.Teacher.model.TeacherVO;
import com.Time_order.model.Time_orderService;
import com.Time_order.model.Time_orderVO;
import com.member.model.MemberVO;

@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class Course_reportServlet extends HttpServlet{
	
	
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
				String course_report_id = req.getParameter("course_report_id");
				if (course_report_id == null || (course_report_id.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
//				String teacher_id = req.getParameter("teacher_id");
//				if (teacher_id == null || (teacher_id.trim()).length() == 0) {
//					errorMsgs.add("請輸入老師編號");
//				}
//				String member_id = req.getParameter("member_id");
//				if (member_id == null || (member_id.trim()).length() == 0) {
//					errorMsgs.add("請輸入自己的會員編號");
//				}
//				String report_text = req.getParameter("report_text");
//				if (report_text == null || (report_text.trim()).length() == 0) {
//					errorMsgs.add("請輸入檢舉內容");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/course_report/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//
//				try {
//					Integer report_state = new Integer(req.getParameter("report_state"));
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Course_reportService course_reportSvc = new Course_reportService();
				Course_reportVO course_reportVO = course_reportSvc.getOneCourse_report(course_report_id);
				if (course_reportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("course_reportVO", course_reportVO); // 資料庫取出的course_reportVO物件,存入req
				String url = "/front-end/course_report/listOneCourse_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse_report.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCourse_report.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String course_report_id = req.getParameter("course_report_id");
				
				/***************************2.開始查詢資料****************************************/
				Course_reportService course_reportSvc = new Course_reportService();
				Course_reportVO course_reportVO = course_reportSvc.getOneCourse_report(course_report_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("course_reportVO", course_reportVO);         // 資料庫取出的course_reportVO物件,存入req
				String url = "/front-end/course_report/update_Course_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Course_report_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_report/listAllCourse_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Course_report_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String course_report_id = req.getParameter("course_report_id").trim();
				
				String teacher_id = req.getParameter("teacher_id").trim();
				String member_id = req.getParameter("member_id").trim();
				String report_text = req.getParameter("report_text").trim();
				
				Integer report_state=new Integer(req.getParameter("report_state").trim());
				
				
				

				Course_reportVO course_reportVO = new Course_reportVO();
				course_reportVO.setCourse_report_id(course_report_id);
				course_reportVO.setTeacher_id(teacher_id);
				course_reportVO.setMember_id(member_id);
				course_reportVO.setReport_text(report_text);
				course_reportVO.setReport_state(report_state);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("course_reportVO", course_reportVO); // 含有輸入格式錯誤的course_reportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_report/update_Course_report_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Course_reportService course_reportSvc = new Course_reportService();
				course_reportVO = course_reportSvc.updateCourse_report(course_report_id, teacher_id, member_id, report_text, report_state);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("course_reportVO", course_reportVO); // 資料庫update成功後,正確的的course_reportVO物件,存入req
				String url = "/front-end/course_report/listOneCourse_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCourse_report.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_report/update_Course_report_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addCourse_report.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session=req.getSession();
				MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
							
				String time_order_id=req.getParameter("time_order_id").trim();
				
				Time_orderService time_orderSvc=new Time_orderService();
				Time_orderVO time_orderVO=time_orderSvc.getOneTime_order(time_order_id);
				String teacher_id =time_orderVO.getTeacher_id();
				

				String member_id = memberVO.getMember_id();
				String report_text = req.getParameter("report_text").trim();
				
				Integer report_state=new Integer(2);
				
				
					

//				Course_reportVO course_reportVO = new Course_reportVO();
//		
//				course_reportVO.setTeacher_id(teacher_id);
//				course_reportVO.setMember_id(member_id);
//				course_reportVO.setReport_text(report_text);
//				course_reportVO.setReport_state(report_state);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("course_reportVO", course_reportVO); // 含有輸入格式錯誤的course_reportVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/course_report/addCourse_report.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************2.開始新增資料***************************************/
				Course_reportService course_reportSvc = new Course_reportService();
				 course_reportSvc.addCourse_report(teacher_id, member_id, report_text, report_state);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-end/course_report/listAllCourse_report.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCourse_report.jsp
//				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_report/addCourse_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCourse_report.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String course_report_id =req.getParameter("course_report_id");
				
				/***************************2.開始刪除資料***************************************/
				Course_reportService course_reportSvc = new Course_reportService();
				course_reportSvc.deleteCourse_report(course_report_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/course_report/listAllCourse_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_report/listAllCourse_report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		==========orderAll====================================================
			if ("orderAll".equals(action)) { // 來自update_Course_report_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session=req.getSession();
			String location1=(String)session.getAttribute("location1");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Course_reportService course_reportSvc=new Course_reportService();
				TeacherService teacherSvc=new TeacherService();
				List<Course_reportVO> orderAllCourseReport=course_reportSvc.orderAll();
				
				
				
				Map<Course_reportVO,TeacherVO> courseReportWithTeacher=new TreeMap<Course_reportVO, TeacherVO>();
				for(Course_reportVO course_reportVO:orderAllCourseReport) {
					String course_report_id=course_reportVO.getCourse_report_id();
					String teacher_id=course_reportVO.getTeacher_id();
					TeacherVO teacherVO=teacherSvc.getOneTeacher(teacher_id);
					courseReportWithTeacher.put(course_reportVO, teacherVO);
					
				}
				
//				List<Map.Entry<Course_reportVO, TeacherVO>> list2 = new ArrayList<>();
//				list2.addAll(courseReportWithTeacher.entrySet());
//				Collections.sort(list2, (o1, o2) -> o1.getKey().getReport_state()-o2.getKey().getReport_state());
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("course_reportVO", course_reportVO); // 含有輸入格式錯誤的course_reportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/course_report/listOrderCourseReport.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderAllCourseReport", orderAllCourseReport);
				req.setAttribute("courseReportWithTeacher", courseReportWithTeacher);
//				req.setAttribute("course_reportVO", course_reportVO); // 資料庫update成功後,正確的的course_reportVO物件,存入req
				String url = "/back-end/course_report/listOrderCourseReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCourse_report.jsp

				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(location1);
				failureView.forward(req, res);
			}
		}
//			course_reportUpdate==============================
			
			if ("course_reportUpdate".equals(action)) { // 來自update_Course_report_input.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session=req.getSession();
				String location1=(String)session.getAttribute("location1");
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String course_report_id=req.getParameter("course_report_id").trim();
					Integer report_state=new Integer(req.getParameter("report_state").trim());
					
					Course_reportService course_reportSvc=new Course_reportService();
					Course_reportVO course_reportVO=course_reportSvc.getOneCourse_report(course_report_id);
					course_reportVO.setReport_state(report_state);
					
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("course_reportVO", course_reportVO); // 含有輸入格式錯誤的course_reportVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/course_report/listOrderCourseReport.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					course_reportSvc.updateCourse_report(course_reportVO);
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
//					req.setAttribute("course_reportVO", course_reportVO); // 資料庫update成功後,正確的的course_reportVO物件,存入req
//					String url = "/front-end/course_report/listOrderCourseReport.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(location1); // 修改成功後,轉交listOneCourse_report.jsp
////
//					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} 
			catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					failureView.forward(req, res);
				}
			}
//			==============
	}

}
