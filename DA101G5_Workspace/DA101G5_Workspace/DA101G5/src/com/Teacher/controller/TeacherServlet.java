package com.Teacher.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Teacher.model.TeacherDAO;
import com.Teacher.model.TeacherJDBCDAO;
import com.Teacher.model.TeacherService;
import com.Teacher.model.TeacherVO;
import com.Teacher_ad.model.Teacher_adService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import oracle.sql.BLOB;

@MultipartConfig(fileSizeThreshold= 1024*1024 , maxFileSize=5*1024*1024, maxRequestSize=100*1024*1024)


public class TeacherServlet extends HttpServlet{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		doPost(req, res);
	}
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");  //可用Filter
		String action = req.getParameter("action");
//        
	

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String teacher_id=(String) req.getParameter("teacher_id").trim();
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO=teacherSvc.getOneTeacher(teacher_id);

//				String str = req.getParameter("teacher_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入老師編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String teacher_id = null;
//				try {
//					teacher_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("老師編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/

//				TeacherService teacherSvc = new TeacherService();
//				TeacherVO teacherVO = teacherSvc.getOneTeacher(teacher_id);
//				
//				if (teacherVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacherVO", teacherVO); // 資料庫取出的teacherVO物件,存入req
				String url = "/front-end/teacher/listOneTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTeacher.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher/selectedAllTeacher.jsp");
				failureView.forward(req, res);
				}
		}
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllTeacher.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
//				String teacher_id = req.getParameter("teacher_id");
//				String teacher_id=teacherVO.getTeacher_id();
				
				/***************************2.開始查詢資料****************************************/
//				TeacherService teacherSvc = new TeacherService();
//				TeacherVO teacherVO = teacherSvc.getOneTeacher(teacher_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("teacherVO", teacherVO);         // 資料庫取出的teacherVO物件,存入req
				String url = "/front-end/teacher/update_teacher_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_teacher_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher/listAllTeacher.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_teacher_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL");
		
			try {
				
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				TeacherService teacherSvc=new TeacherService();
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO)session.getAttribute("teacherVO");
				String teacher_id=teacherVO.getTeacher_id();
				String member_id=((MemberVO)session.getAttribute("memberVO")).getMember_id();
				
								
//     			String teacher_id = req.getParameter("teacher_id").trim();
//     			System.out.println(teacher_id);
//     			String member_id=null;
//				try{
//					 member_id = req.getParameter("member_id").trim();
//				}catch (Exception e){
//					 member_id ="M00015";
//				}
				
				
//				TeacherDAO dao = new TeacherDAO();
//				List<TeacherVO> list = dao.getAll();
//				Iterator<TeacherVO> it=list.iterator();
//				if( list.iterator().hasNext()) {
//					TeacherVO t=(TeacherVO) it.next();
//					if(t.getMember_id().equals(member_id)) {
//						errorMsgs.add("你已經註冊過了");
//					}
//				}

				String work_experience= req.getParameter("work_experience").trim();
				String ed_background= req.getParameter("ed_background").trim();
				String certification= req.getParameter("certification").trim();
				String teacher_introduce= req.getParameter("teacher_introduce").trim();
				Integer course_price =new Integer(req.getParameter("course_price").trim());
				
				Part part = req.getPart("introduce_pic");
				InputStream is =  part.getInputStream();
				
				byte[] introduce_pic = new byte[is.available()];
				is.read(introduce_pic);
				System.out.println(teacherSvc.getFileNameFromPart(part));
System.out.println(introduce_pic.length);
				//只有一種情況要抓資料庫的資源  沒有上傳圖片且資料庫有東西
				try {
					if(teacherSvc.getFileNameFromPart(part)==null
							&&teacherSvc.getOneTeacher(teacher_id).getIntroduce_pic()!=null) {
						System.out.println(741);
//						System.out.println(teacherSvc.getOneTeacher(teacher_id).getIntroduce_pic().length==0);
						 introduce_pic= new byte[teacherSvc.getOneTeacher(teacher_id).getIntroduce_pic().length];
						introduce_pic=teacherSvc.getOneTeacher(teacher_id).getIntroduce_pic();
						is.read(introduce_pic);
					}
				}catch (NullPointerException e){
					errorMsgs.add("請上傳圖片");
				}
				
				
				
				teacherVO.setTeacher_introduce(teacher_introduce);
				teacherVO.setWork_experience(work_experience);
				teacherVO.setEd_background(ed_background);
				teacherVO.setCertification(certification);
				teacherVO.setTeacher_introduce(teacher_introduce);
				teacherVO.setCourse_price(course_price);
				teacherVO.setIntroduce_pic(introduce_pic);
			
//				Integer teacher_state=null;
//				try {
//				String teacher_stateG= req.getParameter("teacher_state").trim();
//				String teacher_stateReg= "^[012]$";
//				if (teacher_stateG == null || teacher_stateG.trim().length() == 0) {
//					errorMsgs.add("老師狀態: 請勿空白");
//				} else if(!teacher_stateG.trim().matches(teacher_stateReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("老師狀態: 注意格式");
//	            }
//					teacher_state= new Integer(req.getParameter("teacher_state").trim());
//				}catch (Exception e) {
//					errorMsgs.add("teacher_state格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				
//				Integer appraisal_accum = null;
//					
//			try {
//					appraisal_accum= new Integer(req.getParameter("appraisal_accum").trim());
//				} catch (Exception e) {
//					errorMsgs.add("appraisal_accum格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer appraisal_count=null;
//				
//				try {
//					appraisal_count= new Integer(req.getParameter("appraisal_count").trim());
//				} catch (Exception e) {
//					errorMsgs.add("appraisal_count格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer course_price=null;
//				try {
//					course_price =new Integer(req.getParameter("course_price").trim());
//				} catch (Exception e) {
//					errorMsgs.add("course_price格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				
				
				
				
//				===================================================================================
//				TeacherVO teacherVO = new TeacherVO();
//				teacherVO.setTeacher_id(teacher_id);
//				teacherVO.setMember_id(member_id);
//				teacherVO.setWork_experience(work_experience);
//				teacherVO.setEd_background(ed_background);
//				teacherVO.setCertification(certification);
//				teacherVO.setTeacher_introduce( teacher_introduce);
//				teacherVO.setTeacher_state( teacher_state);
//				teacherVO.setAppraisal_accum( appraisal_accum);
//				teacherVO.setAppraisal_count(appraisal_count);
//				teacherVO.setCourse_price( course_price);
//				teacherVO.setIntroduce_pic( introduce_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的teacherVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				
				/***************************2.開始修改資料*****************************************/
//				TeacherService teacherSvc = new TeacherService();
				teacherSvc.updateTeacher(teacherVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("teacherVO", teacherVO); // 資料庫update成功後,正確的的teacherVO物件,存入req
				session.setAttribute("teacherVO", teacherVO);
				String url = "/front-end/teacher/update_teacher_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTeacher.jsp
				
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addTeacher.jsp的請求  
//        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			MemberVO memberVO=(MemberVO)req.getAttribute("memberVO");
				
//				String member_id = req.getParameter("member_id").trim();
			HttpSession session=req.getSession();
				MemberVO memberVO =(MemberVO) session.getAttribute("memberVO");
				String member_id=memberVO.getMember_id();
				//
				TeacherService teacherSvc=new TeacherService();
				List<TeacherVO>list=teacherSvc.getAll();
				boolean isteacher=list.stream().anyMatch(vo->vo.getMember_id().equals(member_id));
				if(isteacher) {
					errorMsgs.add("已經申請過囉!");
				}
//				TeacherDAO dao = new TeacherDAO();
//				List<TeacherVO> list = dao.getAll();
//				Iterator<TeacherVO> it=list.iterator();
//				if( list.iterator().hasNext()) {
//					TeacherVO t=(TeacherVO) it.next();
//					if(t.getMember_id().equals(member_id)) {
//						errorMsgs.add("你已經註冊過了");
//					}
//				}
				
				
				//可
				String work_experience= req.getParameter("work_experience").trim();
				if (work_experience == null || work_experience.trim().length() == 0) {
					errorMsgs.add("工作經驗: 請勿空白");
				}
				String ed_background= req.getParameter("ed_background").trim();
				if (ed_background == null || ed_background.trim().length() == 0) {
					errorMsgs.add("教育背景: 請勿空白");
				}
				String certification= req.getParameter("certification").trim();
				if (certification == null || certification.trim().length() == 0) {
					errorMsgs.add("證書: 請勿空白");
				}
				String teacher_introduce= req.getParameter("teacher_introduce").trim();
				if (teacher_introduce == null || teacher_introduce.trim().length() == 0) {
					errorMsgs.add("自我介紹: 請勿空白");
				}
				
				//預設為0
				Integer teacher_state=null;
				teacher_state=new Integer("0");
//				Integer teacher_state=null;
//				try {
//				String teacher_stateG= req.getParameter("teacher_state").trim();
//				String teacher_stateReg= "^[01]$";
//				if (teacher_stateG == null || teacher_stateG.trim().length() == 0) {
//					errorMsgs.add("老師狀態: 請勿空白");
//				} else if(!teacher_stateG.trim().matches(teacher_stateReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("老師狀態: 注意格式");
//	            }
//					teacher_state= new Integer(req.getParameter("teacher_state").trim());
//				}catch (Exception e) {
//					errorMsgs.add("teacher_state格式不正確");
//				}
				
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/teacher/addTeacher.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				
				Integer appraisal_accum = null;
				appraisal_accum=new Integer("0");

				
				Integer appraisal_count=null;
				appraisal_count=new Integer("0");

				
				Integer course_price=null;
				
				try {
					course_price =new Integer(req.getParameter("course_price").trim());
					if(course_price<0) {
						errorMsgs.add("課程價格:請填寫正確的數字");
					}
				} catch (Exception e) {
					errorMsgs.add("課程價格:請填寫正確的數字");
				}

				

//				TeacherService teacherSvc=new TeacherService();
				 
				Part part = req.getPart("introduce_pic");
				InputStream is =  part.getInputStream();
//				if(is.available()==0) {
//					errorMsgs.add("請上傳圖片");
//				}
				byte[] introduce_pic = new byte[is.available()];
				is.read(introduce_pic);	
				//只有一種情況要抓資料庫的資源  沒有上傳圖片且資料庫有東西
				
				
				
				
				
			
				TeacherVO teacherVO = new TeacherVO();

				teacherVO.setMember_id(member_id);
				teacherVO.setWork_experience(work_experience);
				teacherVO.setEd_background(ed_background);
				teacherVO.setCertification(certification);
				teacherVO.setTeacher_introduce(teacher_introduce);
				teacherVO.setTeacher_state(teacher_state);
				teacherVO.setAppraisal_accum(appraisal_accum);
				teacherVO.setAppraisal_count(appraisal_count);
				teacherVO.setCourse_price(course_price);
				teacherVO.setIntroduce_pic(introduce_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的teacherVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher/addTeacher.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.開始新增資料***************************************/
//				TeacherService teacherSvc = new TeacherService();
				teacherSvc.addTeacher( member_id, work_experience,
						 ed_background, certification,  teacher_introduce,   teacher_state
						, appraisal_accum,  appraisal_count, course_price,introduce_pic);
				Teacher_adService teacher_adSvc=new Teacher_adService();
				teacher_adSvc.addTeacher_ad(member_id);
				teacherVO=teacherSvc.getOneTeacherWithMem(member_id);
				session.setAttribute("teacherVO", teacherVO);
				
//				System.out.println(teacherVO.getMember_id());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/teacher/update_teacher_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTeacher.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
//			catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/teacher/addTeacher.jsp");
//				failureView.forward(req, res);
//			}
//		
//		}
		
		
		if ("delete".equals(action)) { // 來自listAllTeacher.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String teacher_id = req.getParameter("teacher_id");
				
				/***************************2.開始刪除資料***************************************/
				TeacherService teacherSvc = new TeacherService();
				teacherSvc.deleteTeacher(teacher_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/teacher/listAllTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher/listAllTeacher.jsp");
				failureView.forward(req, res);
			}
		}	
		
		
//		===========================Change_state====================
			if ("updateState".equals(action)) { // 來自listAllTeacher.jsp的請求
						
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
								
     			String teacher_id = req.getParameter("teacher_id").trim();
				
//     		System.out.println(teacher_id);
     			String teacher_state = req.getParameter("teacher_state").trim();
//				System.out.println(teacher_state);
				
//			
//				Integer teacher_state=null;
//				try {
//				
//				
//				String teacher_stateReg= "^[012]$";
//				if (teacher_stateG == null || teacher_stateG.trim().length() == 0) {
//					errorMsgs.add("老師狀態: 請勿空白");
//				} else if(!teacher_stateG.trim().matches(teacher_stateReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("老師狀態: 注意格式");
//	            }
//					teacher_state= new Integer(req.getParameter("teacher_state").trim());
//				}catch (Exception e) {
//					errorMsgs.add("teacher_state格式不正確");
//				}
//				
				
//				System.out.println();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/teacher/update_teacher_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				
				
//				===================================================================================
				TeacherVO teacherVO = new TeacherVO();
				teacherVO.setTeacher_id(teacher_id);
				
				teacherVO.setTeacher_state(Integer.valueOf( teacher_state));
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的teacherVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher/listAllTeacher.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				
				/***************************2.開始修改資料*****************************************/
				TeacherService teacherSvc = new TeacherService();
				teacherSvc.updateState(teacher_id, Integer.valueOf(teacher_state));
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				
//				String requestURL = req.getParameter("requestURL");
//				System.out.println(requestURL);
//				req.setAttribute("teacherVO", teacherVO); // 資料庫update成功後,正確的的teacherVO物件,存入req
//				String url = "/front-end/teacher/listAllTeacher.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTeacher.jsp
//				
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher/update_teacher_input.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("searchCourse".equals(action)||"".equals(action)) {

			String url = "/front-end/teacher/selectedAllTeacher.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTeacher.jsp
			
			successView.forward(req, res);
			
			
		} 
		
//		=search==================================================
		if("search".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session=req.getSession();
			TeacherService teachertSvc = new TeacherService();
			MemberService memberSvc=new MemberService();
			String location1=(String)session.getAttribute("location1");
			String keyword = null;
			String[] keywords = null;
			List<TeacherVO> teacherlist = teachertSvc.getAll();
			List<TeacherVO> searchingResult = new ArrayList<TeacherVO>();
			//1.接收參數
				keyword = req.getParameter("keyword");
				if(keyword.trim().length()==0) {
					
//					searchingResult=teacherlist;
//					req.setAttribute("searchingResult", searchingResult);
					req.getRequestDispatcher("/front-end/teacher/selectedAllTeacher.jsp").forward(req,res);
//					errorMsgs.put("keyword","關鍵字");
					return;
				}else {
					keywords = keyword.trim().split("\\s+");
				}
				
				if(!errorMsgs.isEmpty()) {
					req.getRequestDispatcher(location1).forward(req,res);
					return;
				}
			
			//2.資料比對
			
			
			to:
			for(int i=0;i<teacherlist.size();i++) {
				for(String key:keywords) {
					//自我介紹 工作經驗 名字
					String contentAppendTitle = teacherlist.get(i).getTeacher_introduce()+teacherlist.get(i).getWork_experience()+memberSvc.getOneMember(teacherlist.get(i).getMember_id()).getMem_nick();
					if(contentAppendTitle.contains(key)) {
						searchingResult.add(teacherlist.get(i));
						continue to;
					}
				}
			}
			
			
			req.setAttribute("searchingResult", searchingResult);
			req.getRequestDispatcher("/front-end/teacher/selectedAllTeacher.jsp").forward(req,res);
		}
		
		
	
	

	
//-----------------------doPost-----------------------------
	}
}
