package com.Course_order.controller;

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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.main.tool.ImageUtil;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/Course_orderServletApp")
public class CourseOrderServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Course_orderDAO dao = new Course_orderDAO();
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
			
			Course_orderVO course_orderVO = new Course_orderVO();
			String temp = inputJsonObject.get("course_orderVO").getAsString();
			temp = temp.replace("\\", "");
			System.out.println(temp);
			course_orderVO = gson.fromJson(temp, Course_orderVO.class);
			
			//判斷實際剩餘點數是否足夠
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.findByPrimaryKey(course_orderVO.getMember_id());
			if(memberVO.getMem_point().intValue() >= course_orderVO.getSpend_point()) {
				dao.insertApp(course_orderVO);
				writeText(res, "success");
				System.out.println("insert course order success");
			} else {
				writeText(res, "fail");
				System.out.println("insert course order fail");
			}
			
			
		} else  if ("getAllRecords".equals(action)) {
			
			List<Course_orderVOApp> colist = new ArrayList<Course_orderVOApp>();
			String member_id = inputJsonObject.get("member_id").getAsString();
			colist = dao.getMyCourseOrderRecords(member_id);
			
			writeText(res, gson.toJson(colist));
			
//			System.out.println(gson.toJson(colist));
			
			
		}else if ("getAllMyOneTeacherCourse".equals(action)) {
			
			List<Course_orderVOApp> colist = new ArrayList<Course_orderVOApp>();
			String member_id = inputJsonObject.get("member_id").getAsString();
			String teacher_id = inputJsonObject.get("teacher_id").getAsString();
			colist = dao.getMyOneTeacherCourse(member_id, teacher_id);
			
			writeText(res, gson.toJson(colist));
			
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
		System.out.println("outText: " + outText.substring(0, outText.length()/90));
//		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		List<TeacherVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
