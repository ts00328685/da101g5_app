package com.Course_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Course_order.model.Course_orderService;
import com.Course_order.model.Course_orderVO;
import com.Teacher.model.TeacherService;
import com.Teacher.model.TeacherVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;


@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)

public class Course_orderServlet extends HttpServlet{

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
				String course_order_id = req.getParameter("course_order_id");
				if (course_order_id == null || (course_order_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				/***************************2.開始查詢資料*****************************************/
				Course_orderService course_orderSvc = new Course_orderService();
				Course_orderVO course_orderVO = course_orderSvc.getOneCourse_order(course_order_id);
				if (course_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("course_orderVO", course_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/course_order/listOneCourse_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse_order.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_order/select_page.jsp");
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
				String course_order_id = req.getParameter("course_order_id");
				
				/***************************2.開始查詢資料****************************************/
				Course_orderService course_orderSvc = new Course_orderService();
				Course_orderVO course_orderVO = course_orderSvc.getOneCourse_order(course_order_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("course_orderVO", course_orderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/course_order/update_Course_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Course_order_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_order/listAllCourse_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Course_order_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String course_order_id = req.getParameter("course_order_id").trim();
				
				String member_id = req.getParameter("member_id");
				
				
				String teacher_id = req.getParameter("teacher_id").trim();
				if (teacher_id == null || teacher_id.trim().length() == 0) {
					errorMsgs.add("教師id請勿空白");
				}	
				Integer buy_time = new Integer(req.getParameter("buy_time").trim());
				Integer spend_point = new Integer(req.getParameter("spend_point").trim());
				Integer remain_hour = new Integer(req.getParameter("remain_hour").trim());
				Integer re_appointment = new Integer(req.getParameter("re_appointment").trim());

				Course_orderVO course_orderVO = new Course_orderVO();
				course_orderVO.setCourse_order_id(course_order_id);
				course_orderVO.setMember_id(member_id);
				course_orderVO.setTeacher_id(teacher_id);
				course_orderVO.setBuy_time(buy_time);
				course_orderVO.setSpend_point(spend_point);
				course_orderVO.setRemain_hour(remain_hour);
				course_orderVO.setRe_appointment(re_appointment);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("course_orderVO", course_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/course_order/update_Course_order_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Course_orderService course_orderSvc = new Course_orderService();
				course_orderVO = course_orderSvc.updateCourse_order(course_order_id, 
						member_id, teacher_id, buy_time, spend_point,remain_hour, re_appointment);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("course_orderVO", course_orderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/course_order/listOneCourse_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_order/update_Course_order_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//String course_order_id = req.getParameter("course_order_id").trim();
			Integer buy_time = new Integer(req.getParameter("buy_time").trim());
			
			
				HttpSession session=req.getSession();
				
				TeacherService teacherSvc=new TeacherService(); 
				String teacher_id = req.getParameter("teacher_id").trim();
				TeacherVO teacherVO=teacherSvc.getOneTeacher(teacher_id);
				List<TeacherVO>teacherList=teacherSvc.getSelectedAll();
				MemberVO memberVO;
				
				 memberVO=(MemberVO)session.getAttribute("memberVO");
				if(memberVO==null) {
					errorMsgs.add("請先註冊會員");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/teacher/selectedAllTeacher.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TeacherVO teacherVOS=(TeacherVO)session.getAttribute("teacherVO");
				if(teacherVOS!=null) {
					if(teacherVOS.equals(teacherVO)) {
						errorMsgs.add("請勿購買自己的課程");
//						String URI=req.getRequestURI();
						req.setAttribute("teacherVO", teacherVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/teacher/listOneTeacher.jsp");
						failureView.forward(req, res);
						return;
					}
					
				}
				
				
				
//				try {
//				String member_id=memberVO.getMember_id();
//				}catch (NullPointerException e){
//					errorMsgs.add("請先登入會員");
////					String URI=req.getRequestURI();
//					req.setAttribute("teacherVO", teacherVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/teacher/listOneTeacher.jsp");
//					failureView.forward(req, res);
//					return;
//				}	
				
				String member_id=memberVO.getMember_id();
				
				
				
				
				
				Integer spend_point=teacherVO.getCourse_price()*buy_time;

				Course_orderVO course_orderVO = new Course_orderVO();
				
				course_orderVO.setMember_id(member_id);
				course_orderVO.setTeacher_id(teacher_id);
				course_orderVO.setBuy_time(buy_time);
				course_orderVO.setSpend_point(spend_point);
				course_orderVO.setRemain_hour(buy_time);
				course_orderVO.setRe_appointment(0);
				
				if(memberVO.getMem_point()-spend_point<=0) {
					errorMsgs.add("餘額不足請儲值點數");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher/listOneTeacher.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				
				memberVO.setMem_point(memberVO.getMem_point()-spend_point);
				MemberService memberSvc=new MemberService();
				memberSvc.updateMember(memberVO);
				
				Course_orderService course_orderSvc=new Course_orderService();
				List<Course_orderVO> list=course_orderSvc.getAll();
				for(Course_orderVO course_orderVO1:list)
				 {//如果有重複課程訂單就累加原訂單的購買時數以及花費點數
					if(course_orderVO1.equals(course_orderVO)) {
						
						Integer buy_time1=course_orderVO1.getBuy_time()+buy_time;
						Integer remain_hour1=course_orderVO1.getRemain_hour()+buy_time;
						Integer spend_point1=course_orderVO1.getSpend_point()+spend_point;
						course_orderVO1.setBuy_time(buy_time1);
						course_orderVO1.setRemain_hour(remain_hour1);
						course_orderVO1.setSpend_point(spend_point1);
						course_orderSvc.updateCourse_order(course_orderVO1);
					}				
				}
				if(!list.contains(course_orderVO)){
					course_orderVO = course_orderSvc.addCourse_order(member_id, teacher_id, buy_time, spend_point, course_orderVO.getRemain_hour(), course_orderVO.getRe_appointment());
				}	
				// Send the use back to the form, if there were errors
				
					
					
					
					/***************************2.開始新增資料***************************************/
	//				Course_orderService course_orderSvc = new Course_orderService();
					
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/teacher/listOneTeacher.jsp";
				req.setAttribute("teacherVO", teacherVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
//			catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/teacher/selectedAllTeacher.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String course_order_id = req.getParameter("course_order_id");
				
				/***************************2.開始刪除資料***************************************/
				Course_orderService course_orderSvc = new Course_orderService();
				course_orderSvc.deleteCourse_order(course_order_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/course_order/listAllCourse_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/course_order/listAllCourse_order.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
