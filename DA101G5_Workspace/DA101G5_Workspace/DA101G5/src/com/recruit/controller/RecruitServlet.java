package com.recruit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.que.model.QueService;
import com.que.model.QueVO;
import com.recrepo.model.RecrepoService;
import com.recruit.model.RecruitService;
import com.recruit.model.RecruitVO;
import com.recruitmessage.model.RecruitmessageService;
import com.res.model.ResService;
import com.res.model.ResVO;

public class RecruitServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
     /******************************新增派單--前台功能***********************************/
		if("addRecruit".equals(action)) {			
			//0.建立錯誤訊息陣列
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//1.接收參數 錯誤處理
			String member_id = null;
			if(req.getParameter("member_id")!=null && req.getParameter("member_id").trim().length()!=0) {/*若會員id=null 導向登入頁面*/
				member_id = req.getParameter("member_id");
			}else {
				resp.sendRedirect(req.getContextPath()+"/front-end/recruit/RecruitIndex.jsp");
			}
			
			String rtitle = null;
			if(req.getParameter("rtitle")!=null && req.getParameter("rtitle").trim().length()!=0) {
				rtitle = req.getParameter("rtitle");
			}else {
				errorMsgs.put("rtitle","需求不可為空");
			}
			
			String rcontent = null;
			if(req.getParameter("rcontent")!=null && req.getParameter("rcontent").trim().length()!=0) {
				rcontent = req.getParameter("rcontent");
			}else {
				errorMsgs.put("rcontent","說明不可為空");
			}
			
			MemberService memberSvc = new MemberService();
			RecruitService recruitSvc = new RecruitService();//處理點數不足
			MemberVO memVO = memberSvc.getOneMember(member_id);
			if(memVO.getMem_point()<7) {
				errorMsgs.put("point", "點數餘額不足,新增一篇派單需要消耗7點,請確認點數餘額");
			}
			
			if(!errorMsgs.isEmpty()) {			
				RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/recruit/RecruitIndex.jsp");
				requestTruck.forward(req,resp);
				return;
			}
			
			//2.新增派單到DB 會員扣點
			recruitSvc.addRecruit(member_id, rcontent, rtitle);
			recruitSvc.updateMmePoint(member_id, memVO.getMem_point().intValue()-7);
			
			//3.回到派單區首頁
			RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/recruit/RecruitIndex.jsp");
			requestTruck.forward(req,resp);
		}
		
		/**********搜尋派單*********************************************/	
		if("search".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String keyword = null;
			String[] keywords = null;
			//1.接收參數
				keyword = req.getParameter("keyword");
				if(keyword.trim().length()==0) {
					errorMsgs.put("keyword","請勿輸入空白鍵當作關鍵字");
				}else {
					keywords = keyword.trim().split("\\s+");
				}
				
				if(!errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/front-end/recruit/RecruitIndex.jsp").forward(req,resp);
					return;
				}
			
			//2.資料比對
			RecruitService recruitSvc = new RecruitService();
			List<RecruitVO> recruitlist = recruitSvc.getAllRecruit();
			List<RecruitVO> searchingResult = new ArrayList<RecruitVO>();
			to:
			for(int i=0;i<recruitlist.size();i++) {
				for(String key:keywords) {
					String contentAppendTitle = recruitlist.get(i).getRcontent()+recruitlist.get(i).getRtitle();
					if(contentAppendTitle.contains(key)) {
						searchingResult.add(recruitlist.get(i));
						continue to;
					}
				}
			}
			
			//返回list到serachingresult
			req.setAttribute("list", searchingResult);
			req.getRequestDispatcher("/front-end/recruit/SearchingResult.jsp").forward(req,resp);
		}
/******************傳送私訊*****************************************************/		
		if("sendmessage".equals(action)) {
			//接收參數
			String sender_id = req.getParameter("sender_id");
			String reciver_id = req.getParameter("reciver_id");
			String message = req.getParameter("messagecontent");
			//DB存
			RecruitmessageService Svc = new RecruitmessageService();
			Svc.addrecruitmessage(sender_id, reciver_id, message);
			
			//導回派單首頁
			resp.sendRedirect(req.getContextPath()+"/front-end/recruit/RecruitIndex.jsp");
			return;
		}
	}
	
}
