package com.hashtag.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hashtag.model.HashtagService;

public class HashtagServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/************新增--後台功能****************************/
		if("addhashtagconfirm".equals(action)) {//從addhashtag首頁來
			//1.接收參數 錯誤處理
			Map<String,String> errorMsgs = new LinkedHashMap();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String hashtag = null;
			if(req.getParameter("hashtag").trim().length()==0) {
				errorMsgs.put("hashtag","hashtag不可為空");
			}else {
				hashtag = req.getParameter("hashtag");
			}
			
			//2.新增入DB
			HashtagService hashtagSvc = new HashtagService();
			hashtagSvc.addHashtag(hashtag);
			
			//3.重導回Hashtag首頁
			resp.sendRedirect("/DA101G5/back-end/hashtag/HashtagIndex.jsp");
			return;
		}
	}
	
}
