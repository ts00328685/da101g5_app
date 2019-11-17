package com.recrepo.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recrepo.model.RecrepoService;
import com.recruit.model.RecruitService;

public class RecrepoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
/******************送檢舉派單進DB--前台功能****************************************************/
		
		if("reportrecruit".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//1.接收參數
			String recruit_id = req.getParameter("recruit_id");
			String repocontent = req.getParameter("repocontent");
			
			if(repocontent.trim().length()==0) {
				errorMsgs.put("repocontent","檢舉理由不可為空");
			}
			
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("/front-end/recruit/reportForm.jsp").forward(req, resp);
				return;
			}
			
			//2.存入DB
			RecrepoService recrepoSvc = new RecrepoService();
			recrepoSvc.addRecrepo(recruit_id, repocontent);
			
			//3.重導向回派單首頁
			RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/recruit/RecruitIndex.jsp");
			requestTruck.forward(req,resp);
		}	
		
		/*************處理檢舉 隱藏派單+修改檢舉處理狀態--後台功能***********************************************/
		if("dorecrepo".equals(action)) {
			//1.接收參數
			String recruit_id = req.getParameter("recruit_id");
			String recrepo_id = req.getParameter("recrepo_id");
			String recstatus = req.getParameter("recstatus");
			String repostatus = req.getParameter("repostatus");

			//2.更改派單狀態 更改檢舉單狀態
			RecruitService recruitSvc = new RecruitService();
			RecrepoService recrepoSvc = new RecrepoService();
			recruitSvc.updateRecruit(recruit_id, Integer.parseInt(recstatus));//改派單狀態
			recrepoSvc.updateRecrepo(recrepo_id,Integer.parseInt(repostatus));//改檢舉狀態
			
			//3.回到處理檢舉區
			RequestDispatcher requestTruck = req.getRequestDispatcher("/back-end/recruit/RecrepoArea.jsp");
			requestTruck.forward(req,resp);
		}
			
	}
	
}
