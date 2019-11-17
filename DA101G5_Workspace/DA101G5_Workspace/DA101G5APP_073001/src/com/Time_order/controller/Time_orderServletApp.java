package com.Time_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import com.Teacher.model.*;
import com.Time_order.model.Time_orderDAO;
import com.Time_order.model.Time_orderVOApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.main.tool.ImageUtil;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_trans.model.Point_transDAO;
import com.point_trans.model.Point_transVOApp;

/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/Time_orderServletApp")
public class Time_orderServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Time_orderDAO dao = new Time_orderDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

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
			String member_id = inputJsonObject.get("member_id").getAsString();
			Time_orderVOApp time_orderVOApp = new Time_orderVOApp();
			String temp = inputJsonObject.get("time_orderVOApp").getAsString();
			temp = temp.replace("\\", "");
			System.out.println(temp);
			time_orderVOApp = gson.fromJson(temp, Time_orderVOApp.class);
			time_orderVOApp.setStudent_member_id(member_id);
			String output = dao.insert(time_orderVOApp);
			if (output == null) {
				writeText(res, "success");
				System.out.println("insert time order success");
			} else {
				writeText(res, "repeated");
				System.out.println("insert time order success");
			}

		} else if ("getAllApp".equals(action)) {
			String member_id = inputJsonObject.get("member_id").getAsString();
			List<Time_orderVOApp> time_orderVOAppList = dao.getAllJoinCourseOrder(member_id);
			Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			writeText(res, gsonBuilder.toJson(time_orderVOAppList));

		}
		if ("updateTOState".equals(action)) {

			String course_order_id = inputJsonObject.get("course_order_id").getAsString();
			String time_order_id = inputJsonObject.get("time_order_id").getAsString();
			String total_hour = inputJsonObject.get("total_hour").getAsString();
			String c_state = inputJsonObject.get("c_state").getAsString();
			String c_judge = inputJsonObject.get("c_judge").getAsString();

			dao.updateTOState(course_order_id, time_order_id, total_hour, c_state, c_judge);
			writeText(res, "success");

		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: Iam here TO out!!!! " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		TeacherDAO dao = new TeacherDAO();
//		List<TeacherVO> cardVOList = dao.getAll();
//		writeText(res, new Gson().toJson(cardVOList));
	}
}
