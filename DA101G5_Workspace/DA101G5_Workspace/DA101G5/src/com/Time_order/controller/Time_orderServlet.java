package com.Time_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Course_order.model.Course_orderDAO;
import com.Course_order.model.Course_orderService;
import com.Course_order.model.Course_orderVO;
import com.Language.model.LanguageService;
import com.Language.model.LanguageVO;
import com.Sort_course.model.Sort_courseService;
import com.Sort_course.model.Sort_courseVO;
import com.Teacher.model.TeacherService;
import com.Teacher.model.TeacherVO;
import com.Teacher_course_list.model.Teacher_course_listService;
import com.Teacher_course_list.model.Teacher_course_listVO;
import com.Time_order.model.Time_orderService;
import com.Time_order.model.Time_orderVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;





@MultipartConfig(fileSizeThreshold= 1024*1024 , maxFileSize=5*1024*1024, maxRequestSize=100*1024*1024)
public class Time_orderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		String action1 = req.getParameter("action1");


//-----------getOne_For_Display--------------		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>(); 
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String time_order_id = req.getParameter("time_order_id");
				String time_order_idReg = "^[T][O]\\d{5}$";
				
				
				if (time_order_id == null || (time_order_id.trim()).length() == 0) {
					errorMsgs.add("請輸入預約時間編號");
				} else if(!time_order_id.trim().matches(time_order_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時間編號: TOxxxxx");
	            }
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/time_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
								
				/***************************2.開始查詢資料*****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
				if (time_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/time_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("time_orderVO", time_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/time_order/listOneTime_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/time_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//-------------getOne_For_Update------------------		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/time_order/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String time_order_id = req.getParameter("time_order_id");
				
				/***************************2.開始查詢資料****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("time_orderVO", time_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/time_order/update_Time_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
//------------		update---------------------		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/time_order/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String time_order_id = new String(req.getParameter("time_order_id").trim());

				String teacher_id= req.getParameter("teacher_id").trim();
				String course_order_id= req.getParameter("course_order_id").trim();
				String language_id= req.getParameter("language_id").trim();
				String sort_course_id= req.getParameter("sort_course_id").trim();
				Integer c_state= new Integer(req.getParameter("c_state").trim());
				Integer c_judge= new Integer(req.getParameter("c_judge").trim());
//				
				
//				
				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim());
				} catch (IllegalArgumentException e) {
					start_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp end_time = null;
				try {
					System.out.println("T");
					end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time").trim());
					System.out.println("Y");
				}
				catch (IllegalArgumentException e) {
					end_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				
				
				
//				----------------

				Time_orderVO time_orderVO = new Time_orderVO();
				time_orderVO.setTeacher_id(time_order_id);
				time_orderVO.setTeacher_id(teacher_id);
				time_orderVO.setCourse_order_id(course_order_id);
				time_orderVO.setLanguage_id(language_id);
				time_orderVO.setSort_course_id(sort_course_id);
				time_orderVO.setC_state(c_state);
				time_orderVO.setC_judge(c_judge);
				time_orderVO.setStart_time(start_time);
				time_orderVO.setEnd_time(end_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("time_orderVO", time_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/time_order/update_Time_order_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************2.開始修改資料*****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				time_orderVO = time_orderSvc.updateTime_order(time_order_id,teacher_id, course_order_id
						, language_id, sort_course_id,c_state, c_judge,start_time,end_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(deptno)); // 資料庫取出的list物件,存入request
				req.setAttribute("time_orderVO", time_orderVO); // 資料庫update成功後,正確的的teacherVO物件,存入req
				String url = "/front-end/time_order/listOneTime_order.jsp";
              	RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/time_order/update_Time_order_input.jsp");
				failureView.forward(req, res);
			}
		}
//-------------------insert
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//System.out.println(123);
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session=req.getSession();
				MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
				String location1=(String)session.getAttribute("location1");
				String member_id=memberVO.getMember_id();
//				===new====================
				String teacher_id=null;
				String language_id=null;
				String sort_course_id=null;
//				
				try {
					 teacher_id=req.getParameter("teacher_id").trim();
//					
				}catch(NullPointerException e){
					errorMsgs.add("請選擇老師");
					
				}
				try {
					 language_id=req.getParameter("language_id").trim();
				}catch(NullPointerException e){
					errorMsgs.add("請選擇語言");
				}
				try {
					 sort_course_id=req.getParameter("sort_course").trim();
				}catch(NullPointerException e){
					errorMsgs.add("請選擇課程");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					
					failureView.forward(req, res);
					return;
				}
//				
////				System.out.println("teacher_id: "+teacher_id);
////				System.out.println("language_id: "+language_id);
////				System.out.println("sort_course_id: "+sort_course_id);
////				System.out.println(teacher_id.equals("a"));
//				
				if(teacher_id==null||teacher_id.equals("-1")) {
					System.out.println("老師: "+teacher_id);
					errorMsgs.add("請選擇老師");
					
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					System.out.println(location1);
					failureView.forward(req, res);
					return;
				}
				System.out.println("language_id: "+language_id);
				if(language_id==null||language_id.equals("-1")) {
					
					errorMsgs.add("請選擇語言");
					
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					System.out.println(location1);
					failureView.forward(req, res);
					return;
				}
				System.out.println("sort_course_id: "+sort_course_id);
				if(sort_course_id==null||sort_course_id.equals("-1")) {
					
					errorMsgs.add("請選擇課程");
					
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					System.out.println(location1);
					failureView.forward(req, res);
					return;
				}
				
//				===new====================
				
				
				
				
				
				
				
				
				
				
				
				String start_time0=req.getParameter("start_time").trim();
				java.sql.Timestamp start_time = null;
				java.sql.Timestamp end_time = null;
				try {
				String start_time1=start_time0.substring(6,16);
				String start_time2=start_time0.substring(17,22);
				start_time0=start_time1+" "+start_time2+":00";
				
				
				String end_time0=req.getParameter("end_time").trim();
				
				String end_time1=end_time0.substring(6,16);
				String end_time2=end_time0.substring(17,22);
				end_time0=end_time1+" "+end_time2+":00";
							
//System.out.println(start_time0);
				start_time = java.sql.Timestamp.valueOf(start_time0);
				end_time = java.sql.Timestamp.valueOf(end_time0);
				}catch(StringIndexOutOfBoundsException e){
					
					errorMsgs.add("留意預約時間");
					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
					successView.forward(req, res);
					return;
				}

//				String teacher_course_option=req.getParameter("teacher_course_option");
//				if(teacher_course_option==null) {
//					errorMsgs.add("請選擇課程");
//					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
//					successView.forward(req, res);
//					return;
//				}
//				String[] str=teacher_course_option.split(":");
//				String mem_nick=str[0];
////System.out.println(mem_nick);
//				String course=str[1];
//				String[] st=course.split("-");
//				String language=st[0];
//				String sort_course=st[1];
				
//				LanguageVO languageVO =new LanguageVO();
//				languageVO.setLanguage(language);
//				LanguageService languageSvc=new LanguageService();
//				List<LanguageVO>languageList=languageSvc.getAll();
//				for(LanguageVO lan:languageList) {
//					if(lan.equals(languageVO)) {
//						languageVO=lan;//把正確的VO塞進去
//					}
//					
//				}
//				
//				Sort_courseVO sort_courseVO=new Sort_courseVO();
//				sort_courseVO.setSort_course(sort_course);
//				Sort_courseService sort_courseSvc=new Sort_courseService();
//				List<Sort_courseVO>sort_courseList=sort_courseSvc.getAll();
//				for(Sort_courseVO sc:sort_courseList) {
//					if(sc.equals(sort_courseVO)) {
//						sort_courseVO=sc;
//					}
//				}
				
//				MemberService memberSvc=new MemberService();
//				List<MemberVO>memList=memberSvc.getAll();
//				TeacherService teacherSvc=new TeacherService();
//				TeacherVO teacherVO=new TeacherVO();
//				for(MemberVO member:memList) {
//					if(member.getMem_nick().equals(mem_nick)) {
//						teacherVO=teacherSvc.getOneTeacherWithMem(member.getMember_id());
//					}
//				}
				
//				String teacher_id=teacherVO.getTeacher_id();
				Course_orderVO course_orderVO=new Course_orderVO();
				course_orderVO.setTeacher_id(teacher_id);
				course_orderVO.setMember_id(member_id);
				Course_orderService course_orderService=new Course_orderService();
				
				
				
				List<Course_orderVO>listCourse=course_orderService.getMemAll(member_id);
				for(Course_orderVO course_orderFake:listCourse) {
					if(course_orderFake.equals(course_orderVO)) {
						course_orderVO=course_orderFake;
					}
				}
				
				Integer hour;
				//把訂單的剩餘時數扣除預約時數
				String course_order_id=course_orderVO.getCourse_order_id();
//System.out.println(course_order_id);
				Course_orderVO course_orderVO1=course_orderService.getOneCourse_order(course_order_id);
				
				try {
					if(course_orderVO1==null) {
	System.out.println("course_orderVO1==null");
	System.out.println(course_orderVO1.toString());
	errorMsgs.add("請確實選擇課程內容!!");
	RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
	successView.forward(req, res);
						
						
					}
					
				}catch(Exception e) {
					System.out.println("近來囉");
					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
					successView.forward(req, res);
					return;
				}
				
				
				try {
										
					
				 hour=new Integer((int) (end_time.getTime()-start_time.getTime())/(60*60*1000));
				if(hour<=0
						||((end_time.getTime()-start_time.getTime())%(60*60*1000))!=0
						||start_time.getTime()<System.currentTimeMillis()) {
					errorMsgs.add("留意預約時間!");
					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
					successView.forward(req, res);
					return;
				}
				
				}
//				NumberFormatException NullPointerException
				catch(Exception e) {
					errorMsgs.add("留意預約時間!");
					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
					successView.forward(req, res);
					return;
				}
				if(course_orderVO1.getRemain_hour()<hour) {
					errorMsgs.add("再去購買堂數吧!");
				}
				
//System.out.println(remain_hour);
				
				
//				String course_order_option=req.getParameter("course_order_option");
				
				
				

//				String teacher_id= req.getParameter("teacher_id").trim();
//				String course_order_id= req.getParameter("course_order_id").trim();
//				String language_id= req.getParameter("language_id").trim();
//				String sort_course_id= req.getParameter("sort_course_id").trim();
//				Integer c_state= new Integer(req.getParameter("c_state").trim());
//				Integer c_judge= new Integer(req.getParameter("c_judge").trim());
////				
//				
				

//				String language_id=languageVO.getLanguage_id();
//				
//				String sort_course_id=sort_courseVO.getSort_course_id();
				
				
//				----------------

				Time_orderVO time_orderVO = new Time_orderVO();
				time_orderVO.setTeacher_id(teacher_id);
				time_orderVO.setCourse_order_id(course_order_id);
				time_orderVO.setLanguage_id(language_id);
				time_orderVO.setSort_course_id(sort_course_id);
				time_orderVO.setC_state(0);
				time_orderVO.setC_judge(0);
				time_orderVO.setStart_time(start_time);
				time_orderVO.setEnd_time(end_time);
				Time_orderService time_orderSvc = new Time_orderService();
				List<Time_orderVO> timeTeacherList=time_orderSvc.getAllForTeacher(teacher_id);
//				if(timeTeacherList.contains(time_orderVO)) {
//					errorMsgs.add("老師沒時間囉!再找其他時間吧!");
//				}
				for(Time_orderVO timeOrderVO:timeTeacherList) {
					if((timeOrderVO.getEnd_time().getTime()<start_time.getTime())
							||(timeOrderVO.getStart_time().getTime()>end_time.getTime())) {
//						System.out.println("老師的結束時間"+timeOrderVO.getEnd_time().getTime());
//						System.out.println("訂單的開始時間"+start_time.getTime());
//						System.out.println("老師的開始時間"+timeOrderVO.getStart_time().getTime());
//						System.out.println("訂單的結束時間"+end_time.getTime());
						
					}else {
						errorMsgs.add("老師沒時間囉!再找其他時間吧!");
						break;
					}
				}
//				System.out.println(13);
				
//
//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("time_orderVO", time_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(location1);
					
					failureView.forward(req, res);
					return;
				}
//				
//				/***************************2.開始新增資料***************************************/
				
				time_orderSvc.addTime_order(time_orderVO);
				
				Integer remain_hour=course_orderVO1.getRemain_hour()-hour;
				course_orderVO1.setRemain_hour(remain_hour);
				course_orderService.updateCourse_order(course_orderVO1);
//				time_orderVO = time_orderSvc.addTime_order(teacher_id, course_order_id, language_id, sort_course_id
//						, c_state, c_judge,start_time,end_time);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-end/time_order/listAllTime_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/memberCalendar.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
			} 
//			catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/member/memberCalendar.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
//        ----------------delete      
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/time_order/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String time_order_id = req.getParameter("time_order_id");
				String time_order_idReg = "^[T][O]\\d{5}$";
								
							
				if (time_order_id == null || (time_order_id.trim()).length() == 0) {
					errorMsgs.add("請輸入預約時間編號");
				} else if(!time_order_id.trim().matches(time_order_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時間編號: TOxxxxx");
				         }
							// Send the use back to the form, if there were errors
				
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/time_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始刪除資料***************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
				time_orderSvc.deleteTime_order(time_order_id);
				errorMsgs.add("已經成功取消預約");
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(time_orderVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
//		==========================================================================
		
		if ("commit".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求
//			System.out.println(123);
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session=req.getSession();		
			String location1 =session.getAttribute("location1").toString();
//System.out.println(location1+"123");
			try {
				/***************************1.接收請求參數****************************************/
//						System.out.println(location);
				String time_order_id = req.getParameter("time_order_id");
//System.out.println(time_order_id);
//System.out.println(123);
				/***************************2.開始查詢資料****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
//							Course_orderService course_orderSvc=new Course_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
				
				//狀態不為尚未預約
				if(time_orderVO.getC_state()!=0) {
					errorMsgs.add("搞什麼東西");
					RequestDispatcher failureView = req.getRequestDispatcher(location1);
					failureView.forward(req, res);
					return;
				}
				
				//確認預約
				time_orderVO.setC_state(1);
				time_orderSvc.updateTime_order(time_orderVO);
//							String course_order_id=time_orderVO.getCourse_order_id();
//							Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
//							Integer re_appointment=course_orderVO.getRe_appointment();
//							if(re_appointment==1||re_appointment.equals(null)) {
//								errorMsgs.add("已經確認預約了");
//								course_orderVO.setRe_appointment(1);
//							}else if(re_appointment==1) {
//								errorMsgs.add("已經不能取消預約了");
//								RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
//								successView.forward(req, res);
//								return;
//							}
//							course_orderSvc.updateCourse_order(course_orderVO);
//							time_orderSvc.deleteTime_order(time_order_id);
		
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher(location1);
//							TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
//							req.setAttribute("teacherVO", teacherVO);
				successView.forward(req, res);
				return;

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(location1);
				failureView.forward(req, res);
			}
		}
		
//		========================
		if ("getOne_For_Cancel".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session=req.getSession();		
			String location1 =session.getAttribute("location1").toString();

			try {
				/***************************1.接收請求參數****************************************/
//			System.out.println(location);
				String time_order_id = req.getParameter("time_order_id");
				
//System.out.println(time_order_id);
//System.out.println(123);
				/***************************2.開始查詢資料****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				Course_orderService course_orderSvc=new Course_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
				if(time_orderVO.getC_state()==3) {
					
					String url="/front-end/livestream/studentWatch.jsp?time_order_id="+time_order_id;
//					String url="https://10.120.39.20:8443/DA101G5_front_end(teacher)/front-end/livestream/studentWatch.jsp?time_order_id="+time_order_id;
//					res.sendRedirect(url);
					RequestDispatcher liveView = req.getRequestDispatcher(url);
					liveView.forward(req, res);
					return;
				}
				
				if(time_orderVO.getC_state()!=0) {
					errorMsgs.add("搞什麼東西");
					RequestDispatcher failureView = req.getRequestDispatcher(location1);
					failureView.forward(req, res);
					return;
				}
				
//				========送去實況室=======================================
				
				
				
				
//				==========================
				
				
				
				String course_order_id=time_orderVO.getCourse_order_id();
				Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
				Integer re_appointment=course_orderVO.getRe_appointment();
				if(re_appointment==0||re_appointment.equals(null)) {
					errorMsgs.add("已經取消預約了");
					course_orderVO.setRe_appointment(1);
					//抓結束-開始小時數
					Integer remain_hour=course_orderVO.getRemain_hour();
					Integer hour=new Integer((int) (time_orderVO.getEnd_time().getTime()-time_orderVO.getStart_time().getTime())/(60*60*1000));
					
					//把剩餘時間+上已經取消的時間
					remain_hour=remain_hour+hour;
					course_orderVO.setRemain_hour(remain_hour);
				}else if(re_appointment==1) {
					errorMsgs.add("已經不能取消預約了");
					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
					successView.forward(req, res);
					return;
				}
				course_orderSvc.updateCourse_order(course_orderVO);
				time_orderSvc.deleteTime_order(time_order_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher(location1);
//				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
//				req.setAttribute("teacherVO", teacherVO);
				successView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(location1);
				failureView.forward(req, res);
			}
		}
		
		
//		-------------***尚未測試***-------------------------------------------------------------------------------------
		if ("evaluate".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session=req.getSession();		
			String location1 =session.getAttribute("location1").toString();

			try {
				/***************************1.接收請求參數****************************************/
				

				String time_order_id = req.getParameter("time_order_id").trim();
				
				String judge=req.getParameter("c_judge");
//System.out.println(judge);
//System.out.println("judge: "+judge);
System.out.println("---------------------time_order_id: "+time_order_id);
//System.out.println(123);
				/***************************2.開始查詢資料****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
				Course_orderService course_orderSvc=new Course_orderService();
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
System.out.println("time_orderVO.getC_state()"+time_orderVO.getC_state());				
if(time_orderVO.getC_state()==2) {
	errorMsgs.add("已經評價過囉!!");
	RequestDispatcher failureView = req
			.getRequestDispatcher(location1);
	failureView.forward(req, res);
	return;
	
					
}
				long start_time=time_orderVO.getStart_time().getTime();
				long end_time=time_orderVO.getEnd_time().getTime();
				long period=(end_time-start_time)/(1000*60*60);
//System.out.println("period: "+period);
				
				Integer c_judge=new Integer(judge);
				
				time_orderVO.setC_judge(c_judge);
				
				time_orderVO.setC_state(2);
//				=======course_order==========================================================
				String course_order_id=time_orderVO.getCourse_order_id();
				Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
				course_orderVO.setRe_appointment(0);
				Integer hour=(int) ((int)course_orderVO.getRemain_hour()-period);
				
				course_orderVO.setRemain_hour(hour);
				

				
				
//				=============修改老師資料start===================
				String teacher_id=time_orderVO.getTeacher_id();
				TeacherService teacherSvc=new TeacherService();
				
				TeacherVO teacherVO=teacherSvc.getOneTeacher(teacher_id);
				int appraisalAccum=teacherVO.getAppraisal_accum();
				int appraisalCount=teacherVO.getAppraisal_count();
				teacherVO.setAppraisal_accum(appraisalAccum+c_judge);
				teacherVO.setAppraisal_count(appraisalCount+1);
//System.out.println(teacher_id);
				
//				=============修改老師資料end===================
				
//				=============修改 教師課程明細start===================
				Teacher_course_listService teacher_course_listSvc=new Teacher_course_listService();
				
				String sort_course_id=time_orderVO.getSort_course_id();
				String language_id=time_orderVO.getLanguage_id();
				
				
				Set<Teacher_course_listVO>course_list=(Set<Teacher_course_listVO>) teacher_course_listSvc.getAllForTeacher(teacher_id);
				
//				System.out.println(course_list.get(1).getTeacher_course_list_id());
				
				
//				Optional<Teacher_course_listVO> teacher_course_listVO=course_list.stream()
//							.filter(p->p.getSort_course_id().equals(sort_course_id))
//							.filter(p->p.getLanguage_id().equals(language_id))
//							.findAny();
//				if(teacher_course_listVO.isPresent()) {
				for(Teacher_course_listVO listVO:course_list) {
					
					if(listVO.getLanguage_id().equals(language_id)&&listVO.getSort_course_id().equals(sort_course_id)) {
						
//						Teacher_course_listVO vo=teacher_course_listVO.get();
						int accum=listVO.getCourse_appraisal_accum();
						int count=listVO.getCourse_appraisal_count();
						listVO.setCourse_appraisal_accum(accum+c_judge);
						listVO.setCourse_appraisal_count(count+1);
						
						
						//***************上傳*****************
						teacher_course_listSvc.updateTeacher_course_list(listVO);
						
						
					}
				}
				
				
//				=============修改 教師課程明細end===================
//				
//				String course_order_id=time_orderVO.getCourse_order_id();
//				Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
//				Integer re_appointment=course_orderVO.getRe_appointment();
//				if(re_appointment==0||re_appointment.equals(null)) {
//					errorMsgs.add("已經取消預約了");
//					course_orderVO.setRe_appointment(1);
//					//抓結束-開始小時數
//					Integer remain_hour=course_orderVO.getRemain_hour();
//					Integer hour=new Integer((int) (time_orderVO.getEnd_time().getTime()-time_orderVO.getStart_time().getTime())/(60*60*1000));
//					
//					//把剩餘時間+上已經取消的時間
//					remain_hour=remain_hour+hour;
//					course_orderVO.setRemain_hour(remain_hour);
//				}else if(re_appointment==1) {
//					errorMsgs.add("已經不能取消預約了");
//					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
//					successView.forward(req, res);
//					return;
//				}
				
				time_orderSvc.updateTime_order(time_orderVO);
				teacherSvc.updateTeacher(teacherVO);

				course_orderSvc.updateCourse_order(course_orderVO);
//course_orderSvc.updateCourse_order(course_orderVO);
				
				
				
				
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher(location1);
//
				successView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理************************************/
			} 
			catch (Exception e) {
				
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(location1);
				failureView.forward(req, res);
			}
		}
		
		
//		----------老師開啟直播--------------尚未測試
		
		if ("liveShow".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session=req.getSession();		
			String location1 =session.getAttribute("location1").toString();

			try {
				/***************************1.接收請求參數****************************************/
				String time_order_id = req.getParameter("time_order_id");

				/***************************2.開始查詢資料****************************************/
				Time_orderService time_orderSvc = new Time_orderService();
//				Course_orderService course_orderSvc=new Course_orderService();
				
				Time_orderVO time_orderVO = time_orderSvc.getOneTime_order(time_order_id);
				//已預約尚未完課
				if(time_orderVO.getC_state()==0||time_orderVO.getC_state()==2) {
					errorMsgs.add("搞什麼東西");
					RequestDispatcher failureView = req.getRequestDispatcher(location1);
					failureView.forward(req, res);
					return;
				}
				
				
				long current=System.currentTimeMillis();
				long start_timeL=time_orderVO.getStart_time().getTime();
				long end_timeL=time_orderVO.getEnd_time().getTime();
				//20分鐘的緩衝
				if(current>(start_timeL-1000*60*60*60*1000)&&current<(end_timeL+1000*60*60*60*1000)) {
					//實況中
					time_orderVO.setC_state(3);
					time_orderSvc.updateTime_order(time_orderVO);
//					
					String url="/front-end/livestream/teacherBroadcast.jsp?time_order_id="+time_order_id;
//					String url="/DA101G5_front_end(teacher)/front-end/livestream/teacherBroadcast.jsp?time_order_id="+time_order_id;
//					
//					res.sendRedirect(url);
					RequestDispatcher liveView = req.getRequestDispatcher(url);
					liveView.forward(req, res);
					return;
					
				}
				
				
					
				
				
				
				
				
				
//				String course_order_id=time_orderVO.getCourse_order_id();
//				Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
//				Integer re_appointment=course_orderVO.getRe_appointment();
//				if(re_appointment==0||re_appointment.equals(null)) {
//					errorMsgs.add("已經取消預約了");
//					course_orderVO.setRe_appointment(1);
//					//抓結束-開始小時數
//					Integer remain_hour=course_orderVO.getRemain_hour();
//					Integer hour=new Integer((int) (time_orderVO.getEnd_time().getTime()-time_orderVO.getStart_time().getTime())/(60*60*1000));
//					
//					//把剩餘時間+上已經取消的時間
//					remain_hour=remain_hour+hour;
//					course_orderVO.setRemain_hour(remain_hour);
//				}else if(re_appointment==1) {
//					errorMsgs.add("已經不能取消預約了");
//					RequestDispatcher successView = req.getRequestDispatcher(location1); // 成功轉交update_emp_input.jsp
//					successView.forward(req, res);
//					return;
//				}
//				course_orderSvc.updateCourse_order(course_orderVO);
//				time_orderSvc.deleteTime_order(time_order_id);
				errorMsgs.add("請在課程時間使用!!");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher(location1);
//				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
//				req.setAttribute("teacherVO", teacherVO);
				successView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(location1);
				failureView.forward(req, res);
			}
		}
//---------------------------------------------------------------------------------------	
//		====getSelect======================================
		
        if ("getSelect".equals(action)) { // 來自addEmp.jsp的請求  
//System.out.println(123);
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session=req.getSession();
				MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
				String member_id=memberVO.getMember_id();
				String teacher_id = req.getParameter("teacher_id");
				String language_id = req.getParameter("language_id");
				Course_orderService course_orderSvc=new Course_orderService();
				Time_orderService time_orderSvc=new Time_orderService();
				Teacher_course_listService teacher_course_listSvc=new Teacher_course_listService();
				LanguageService languageSvc=new LanguageService();
				Sort_courseService sortCourseSvc=new Sort_courseService();
				List <LanguageVO>languageList=languageSvc.getAll();
				List<Course_orderVO> courseList=course_orderSvc.getMemAll(member_id);
				List<Teacher_course_listVO> forTeacher=new ArrayList<Teacher_course_listVO>();
				List<Teacher_course_listVO> forLanguage=new ArrayList<Teacher_course_listVO>();
				Set<String> checkLanguage=new HashSet();
				
				JSONArray array = new JSONArray();
				
				if (!"".equals(teacher_id) && !"".equals(language_id)) {
					Set<Teacher_course_listVO> teacher_course_list=teacher_course_listSvc.getAllForTeacher(teacher_id);
					
					for(Teacher_course_listVO teacher_course_listVO:teacher_course_list) {
						 if(teacher_course_listVO.getLanguage_id().equals(language_id)) {
							 forLanguage.add(teacher_course_listVO);
						 }
					}
					
					for (Teacher_course_listVO csb : forLanguage) {
						JSONObject obj = new JSONObject();
						String course=sortCourseSvc.getOneSort_course(csb.getSort_course_id()).getSort_course();
						obj.put("teacher_id", teacher_id);
						obj.put("language_id", language_id);
						obj.put("course_id", csb.getSort_course_id());
						obj.put("course", course);
						array.put(obj);
					}
					
					
					
				}else {
					
					Set<Teacher_course_listVO> teacher_course_list=teacher_course_listSvc.getAllForTeacher(teacher_id);
					
					for(Teacher_course_listVO teacher_course_listVO:teacher_course_list) {
//						 language_id=teacher_course_listVO.getLanguage_id();
						 forTeacher.add(teacher_course_listVO);
					}
					Set<LanguageVO> languageVOSet=new HashSet();
					for (Teacher_course_listVO csb : forTeacher) {
						JSONObject obj = new JSONObject();
						
						languageVOSet.add(languageSvc.getOneLanguage(csb.getLanguage_id()));
						String language=languageSvc.getOneLanguage(csb.getLanguage_id()).getLanguage();
						String course=sortCourseSvc.getOneSort_course(csb.getSort_course_id()).getSort_course();
						obj.put("teacher_id", teacher_id);
						if(!checkLanguage.contains(language)) {
								obj.put("language", language);
								obj.put("language_id", csb.getLanguage_id());
								checkLanguage.add(language);
						}
						
						
						obj.put("course_id", csb.getSort_course_id());
						obj.put("course", course);
						array.put(obj);
					}
					
				}
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(array.toString());
				out.flush();
				out.close();
					
			} 
			catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/memberCalendar.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//=====================================================		
	}
}
