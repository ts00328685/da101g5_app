package com.Teacher_course_list.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Teacher.model.TeacherVO;
import com.Teacher_course_list.model.Teacher_course_listService;
import com.Teacher_course_list.model.Teacher_course_listVO;



@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class Teacher_course_listServlet extends HttpServlet{

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
				String str = req.getParameter("teacher_course_list_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_course_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String teacher_course_list_id =str;
				
				
				/***************************2.開始查詢資料*****************************************/
				Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
				Teacher_course_listVO teacher_course_listVO = teacher_course_listSvc.getOneTeacher_course_list(teacher_course_list_id);
				if (teacher_course_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_course_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacher_course_listVO", teacher_course_listVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/teacher_course_list/listOneTeacher_course_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTeacher_course_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_course_list/select_page.jsp");
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
				String teacher_course_list_id =req.getParameter("teacher_course_list_id");
				
				/***************************2.開始查詢資料****************************************/
				Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
				Teacher_course_listVO teacher_course_listVO = teacher_course_listSvc.getOneTeacher_course_list(teacher_course_list_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("teacher_course_listVO", teacher_course_listVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/teacher_course_list/update_Teacher_course_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Teacher_course_list_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_course_list/listAllTeacher_course_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Teacher_course_list_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				System.out.println(789);
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO)session.getAttribute("teacherVO");
				String teacher_id=teacherVO.getTeacher_id();
				Teacher_course_listService teacher_course_listSvc=new Teacher_course_listService();

				String teacher_course_list_id = req.getParameter("teacher_course_list_id").trim();
				
				Teacher_course_listVO teacher_course_listVO=teacher_course_listSvc.getOneTeacher_course_list(teacher_course_list_id);

				if(teacher_course_listVO.getCourse_state()==0) {
					teacher_course_listVO.setCourse_state(1);

				}
				else{
					teacher_course_listVO.setCourse_state(0);
					
				}

//				String sort_course_id = req.getParameter("sort_course_id").trim();
//				String language_id = req.getParameter("language_id").trim();
//				List<Teacher_course_listVO>list=teacher_course_listSvc.getAll();
//				for(int i=0;i<list.size();i++) {
//					if(list.get(i).getTeacher_id().equals(teacher_id)&&list.get(i).getSort_course_id().equals(sort_course_id)
//							&&list.get(i).getLanguage_id().equals(language_id)) {
//						list.get(i).getTeacher_course_list_id();
//					}
//				}
				
				

//				Integer course_appraisal_accum = new Integer(req.getParameter("course_appraisal_accum").trim());
//				Integer course_appraisal_count = new Integer(req.getParameter("course_appraisal_count").trim());
//				Integer course_state = new Integer(req.getParameter("course_state").trim());
//				Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();
//				teacher_course_listVO.setTeacher_course_list_id(teacher_course_list_id);
//				teacher_course_listVO.setSort_course_id(sort_course_id);
//				teacher_course_listVO.setLanguage_id(language_id);
//				teacher_course_listVO.setTeacher_id(teacher_id);
//				teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
//				teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
//				teacher_course_listVO.setCourse_state(course_state);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacher_course_listVO", teacher_course_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_course_list/update_Teacher_course_list_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
//				Teacher_course_listService teacher_course_listSvc1 = new Teacher_course_listService();
				teacher_course_listSvc.updateTeacher_course_list(teacher_course_listVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacher_course_listVO", teacher_course_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/teacher_course_list/addTeacher_course_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
				catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_course_list/addTeacher_course_list.jsp");
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
				
				HttpSession session=req.getSession();
				TeacherVO teacherVO=(TeacherVO) session.getAttribute("teacherVO");
				String teacher_id=teacherVO.getTeacher_id();
				String language_id = req.getParameter("language_id").trim();
				
				String sort_course_id = req.getParameter("sort_course_id").trim();
//				if(!language_id.equals("L00002")) {
//					if(sort_course_id.equals("SC00005")||sort_course_id.equals("SC00007")
//					||sort_course_id.equals("SC00008")||sort_course_id.equals("SC00009")||sort_course_id.equals("SC00010")) {
//						errorMsgs.add("請留意課程內容");
//					}
//				}
				
//				System.out.println(language_id);
//System.out.println(sort_course_id);
				
				Teacher_course_listService Teacher_course_listSvc=new Teacher_course_listService();
				Set<Teacher_course_listVO>list=Teacher_course_listSvc.getAllForTeacher(teacher_id);
				
				for(Teacher_course_listVO teacher_course_list:list) {
					if(teacher_course_list.getLanguage_id().equals(language_id)
						&&teacher_course_list.getSort_course_id().equals(sort_course_id)) {
						
						errorMsgs.add("已經申請過此課程");
		
					}
					
				}
				
				
				
				
				Teacher_course_listVO teacher_course_listVO=new Teacher_course_listVO();
				
				teacher_course_listVO.setLanguage_id(language_id);
				teacher_course_listVO.setSort_course_id(sort_course_id);
				teacher_course_listVO.setTeacher_id(teacher_id);
				teacher_course_listVO.setCourse_appraisal_accum(0);
				teacher_course_listVO.setCourse_appraisal_count(0);
				teacher_course_listVO.setCourse_state(1);
//				String memberid = req.getParameter("member_id").trim();
//				
//				String teacher_id = req.getParameter("teacher_id").trim();
//				
//				
//
//				Integer course_appraisal_accum = new Integer(req.getParameter("course_appraisal_accum").trim());
//				Integer course_appraisal_count = new Integer(req.getParameter("course_appraisal_count").trim());
//
//				Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();
//				
//				teacher_course_listVO.setSort_course_id(sort_course_id);
//				teacher_course_listVO.setLanguage_id(language_id);
//				teacher_course_listVO.setTeacher_id(teacher_id);
//				teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
//				teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacher_course_listVO", teacher_course_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/teacher_course_list/addTeacher_course_list.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
		
				
				/***************************2.開始新增資料***************************************/
				Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
				teacher_course_listSvc.addTeacher_course_list(teacher_course_listVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				req.setAttribute("teacherVO", teacherVO);
				String url = "/front-end/teacher_course_list/addTeacher_course_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
				catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_course_list/addTeacher_course_list.jsp");
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
				String teacher_course_list_id = req.getParameter("teacher_course_list_id");
				
				/***************************2.開始刪除資料***************************************/
				Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
				teacher_course_listSvc.deleteTeacher_course_list(teacher_course_list_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/teacher_course_list/listAllTeacher_course_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/teacher_course_list/listAllTeacher_course_list.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
