package com.point_trans.controller;

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
@WebServlet("/Point_transServletApp")
public class Point_transServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Point_transDAO dao = new Point_transDAO();
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
		String member_id = inputJsonObject.get("member_id").getAsString();
		

		if ("add".equals(action)) {
			String points = inputJsonObject.get("points").getAsString();
			Point_transVOApp point_transVOApp = new Point_transVOApp();
			point_transVOApp.setMember_id(member_id);
			point_transVOApp.setPoint_record(Double.valueOf(points));
			point_transVOApp.setTransdate_app(new Timestamp(System.currentTimeMillis()));
			dao.insertApp(point_transVOApp);

			writeText(res, "success");

		} else if ("getAllPTS".equals(action)) {

			List<Point_transVOApp> ptsList = dao.getAllMyPointTrans(member_id);
			

			writeText(res, gson.toJson(ptsList));

		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		List<TeacherVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
