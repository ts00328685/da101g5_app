package com.favorque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorque.model.FavorqueService;
import com.favorque.model.FavorqueVO;

public class FavorqueServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/******* 加入最愛 ***********************************/
		if ("addfavorque".equals(action)) {
			PrintWriter out = resp.getWriter();

			String member_id = req.getParameter("member_id");
			String que_id = req.getParameter("que_id");

			FavorqueService favorqueSvc = new FavorqueService();
			List<FavorqueVO> favorquelist = favorqueSvc.findFavorqueByMemberId(member_id);
			if (favorquelist.size() == 0) {// 第一次加最愛
				favorqueSvc.addFavorque(que_id, member_id);
				return;
			}

			for (int i = 0; i < favorquelist.size(); i++) {
				if (favorquelist.get(i).getQue_id().equals(que_id)) {// 該問題已加過最愛
					out.write("false");
					return;
				}
			}
			favorqueSvc.addFavorque(que_id, member_id);
		}
		/***********移除最愛***********************************/
		if("deletefavorque".equals(action)) {
			//接收參數
			String que_id = req.getParameter("que_id");
			String member_id = req.getParameter("member_id");
			//刪除
			FavorqueService favorqueSvc = new FavorqueService();
			favorqueSvc.deleteFavorque(que_id, member_id);
			PrintWriter out = resp.getWriter();
			out.write("true");
			//轉回最愛問題網頁
			resp.sendRedirect("/DA101G5/front-end/favorque/favorqueIndex.jsp");
			return;
			
		}
	}
}
