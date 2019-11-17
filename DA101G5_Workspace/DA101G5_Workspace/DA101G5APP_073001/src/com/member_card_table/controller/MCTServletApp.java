package com.member_card_table.controller;

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
import com.member_card_table.model.Member_card_tableDAO;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/MCTServletApp")
public class MCTServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		CardDAO dao = new CardDAO();
		Member_card_tableDAO mctDAO = new Member_card_tableDAO();
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
			
		}   else if ("updateNum".equals(action)) {
			
			String card_id = jsonObject.get("card_id").getAsString();
			String wrong_number = jsonObject.get("wrong_number").getAsString();
			String right_number = jsonObject.get("right_number").getAsString();
			String choice_card_group = jsonObject.get("choice_card_group").getAsString();
			
			mctDAO.updateNum(member_card_group_id, card_id, wrong_number, right_number, choice_card_group);
			
			
			writeText(res, "success");
			
		}   else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("MCT outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CardDAO dao = new CardDAO();
		List<CardVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
