package com.Teacher_course_list.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Course_order.model.Course_orderDAO;
import com.Course_order.model.Course_orderVO;
import com.Course_order.model.Course_orderVOApp;
import com.Teacher.model.*;
import com.Teacher_course_list.model.Teacher_course_listDAO;
import com.Teacher_course_list.model.Teacher_course_listVOApp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.main.tool.ImageUtil;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/Teacher_course_listServletApp")
public class Teacher_course_listServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Teacher_course_listDAO dao = new Teacher_course_listDAO();
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		System.out.println("input: " + jsonIn);
		
		JsonObject inputJsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		String action = inputJsonObject.get("action").getAsString();
		
		if ("add".equals(action)) {
			
			
			
		} else  if ("getAllRecords".equals(action)) {
			
			
			
		}else if ("getAllOneTeacherCourse".equals(action)) {
			
			List<Teacher_course_listVOApp> tcolist = new ArrayList<Teacher_course_listVOApp>();
			String teacher_id = inputJsonObject.get("teacher_id").getAsString();
			tcolist = dao.getAllOneTeacherCourseList(teacher_id);
			
			writeText(res, gson.toJson(tcolist));
			
//			System.out.println(gson.toJson(colist));
			
			
		}else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText.substring(0, outText.length()));
//		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		List<TeacherVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
