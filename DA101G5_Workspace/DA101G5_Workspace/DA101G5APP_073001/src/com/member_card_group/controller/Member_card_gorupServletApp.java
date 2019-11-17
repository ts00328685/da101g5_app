package com.member_card_group.controller;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member_card_group.model.Member_card_groupDAO;
import com.member_card_group.model.Member_card_groupVO;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/Member_card_gorupServletApp")
public class Member_card_gorupServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Member_card_groupDAO dao = new Member_card_groupDAO();
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
		String member_id = jsonObject.get("member_id").getAsString();

		if ("getAllByMemberId".equals(action) && member_id != null) {
			
			List<Member_card_groupVO> member_card_groupVOList = dao.getAllByMember_id(member_id);
			writeText(res, gson.toJson(member_card_groupVOList));
			System.out.println("success");
			
		}   else {
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
		Member_card_groupDAO dao = new Member_card_groupDAO();
		List<Member_card_groupVO> member_card_groupVOList = dao.getAllByMember_id("M00011");
		writeText(res, new Gson().toJson(member_card_groupVOList));
	}
}
