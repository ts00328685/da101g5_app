package com.card.controller;

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

import com.card.model.CardDAO;
import com.card.model.CardVO;
import com.card.model.CardVOApp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/CardServletApp")
public class CardServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		CardDAO dao = new CardDAO();
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
		String member_card_group_id = jsonObject.get("member_card_group_id").getAsString();

		if ("getAllByMCG_Id".equals(action)) {
			List<CardVOApp> cardVOAppList = dao.getAllByMCG_Id(member_card_group_id);
			writeText(res, gson.toJson(cardVOAppList));
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
		System.out.println("outText: " + outText.substring(0, 30));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CardDAO dao = new CardDAO();
		List<CardVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
