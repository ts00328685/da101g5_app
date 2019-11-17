package com.Teacher.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Teacher.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.main.tool.ImageUtil;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/TeacherServletApp")
public class TeacherServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		TeacherDAO dao = new TeacherDAO();
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		System.out.println("input: " + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		String action = jsonObject.get("action").getAsString();
		
		if ("getAll".equals(action)) {
			
			List<TeacherCardVO> tclist = dao.getAllTeacherCard();
			writeText(res, gson.toJson(tclist));
//			System.out.println(gson.toJson(tclist));
			System.out.println("get teacher success");
			
			
		}   else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String teacher_id = jsonObject.get("pk").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(teacher_id);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

		} else if ("getMyTeacher".equals(action)) {
			String member_id = jsonObject.get("member_id").getAsString();
			List<MyTeacherCardVO> mytclist = dao.getMyTeacherCard(member_id);
			writeText(res, gson.toJson(mytclist));
			
			
		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText.substring(0, outText.length()/200));
//		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		List<TeacherVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
