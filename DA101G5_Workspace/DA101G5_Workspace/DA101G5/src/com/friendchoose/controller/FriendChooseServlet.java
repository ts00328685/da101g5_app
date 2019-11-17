package com.friendchoose.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.friendchoose.model.FriendChooseService;
import com.friendchoose.model.FriendChooseVO;
import com.member.model.MemberVO;
import com.friendmanage.model.FriendManageService;
import com.friendmanage.model.FriendManageVO;
import com.message.model.MessageService;

public class FriendChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = null;
		
		if("goToChoose".equals(action)) { //來自select_page.jsp的請求
		
			memberVO = (MemberVO) session.getAttribute("memberVO");
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			/*-------------判斷當日是否有抽過----------------*/
			try {
				
				String where = request.getParameter("choose_location");
				
				FriendChooseService friendChooseSvc =  new FriendChooseService();
				Integer isAppli = (friendChooseSvc.getOnePro(memberVO.getMember_id())).getFriend_appli();
				if (isAppli == null || isAppli == 0) {
					errorMsgs.add("請填寫學伴資料");
					//尚未取得資格之訊息
				}else {
					Integer status = (friendChooseSvc.getOnePro(memberVO.getMember_id())).getFriend_choose();
					if (status == null) {
						errorMsgs.add("status沒有取得");						
					}else {
						if (status == 1) {
							errorMsgs.add("今日已抽過囉!");
						}else {							
							RequestDispatcher succussView = request.getRequestDispatcher("/front-end/friend/chooseFriend.jsp");
							succussView.forward(request, response);							
						}
					}
				}					
			
				if(!errorMsgs.isEmpty()) {
					String url = where;
					
					
					RequestDispatcher failure = request.getRequestDispatcher(url);
					failure.forward(request, response);
					return;
				}	
				
			}catch (Exception e) {
				
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/select_page.jsp");
				failure.forward(request, response);				
			}
		}	
		
		
		
		
		
		
		if ("choose".equals(action)) { //來自chooseFriend.jsp的請求
			
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");			
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				
				/*-------------性別選擇確認----------------*/				
				Integer condition_sex = null;
				if (request.getParameter("condition_sex") == null) {
					errorMsgs.add("請選擇性別");
				}else {
					condition_sex = Integer.parseInt(request.getParameter("condition_sex"));
				}
				
				/*-------------語言選擇確認----------------*/
				String condition_language_id = null;
				if (request.getParameter("condition_language_id") == null ||"-1".equals(request.getParameter("condition_language_id"))) {
					errorMsgs.add("請選擇語言");
					condition_language_id = request.getParameter("condition_language_id");
				}else {
					condition_language_id = request.getParameter("condition_language_id");
				}
				
				/*-------------包裝資料----------------*/
				FriendChooseVO friendChooseVO = new FriendChooseVO();				
				friendChooseVO.setCondition_language_id(condition_language_id);
				friendChooseVO.setCondition_sex(condition_sex);
				
				
				/*-------------有錯誤訊息之處理----------------*/
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("friendChooseVO", friendChooseVO);
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/chooseFriend.jsp");
					failure.forward(request, response);
					return;
				}						
				
				/*-------------把條件傳給friendchooser抽選----------------*/
				request.setAttribute("friendChooseVO", friendChooseVO);
				RequestDispatcher successView = request.getRequestDispatcher("/friend/friendChooser.do");
				successView.forward(request, response);
				
				/*-------------其他例外----------------*/
				}catch (Exception e) {
					e.printStackTrace();
				
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listFriend.jsp");
				failure.forward(request, response);				
				}
		}
		
		
		if("SayNoInvite".equals(action)) { //來自listFriend.jsp的請求(不邀請)
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
			
			try {
				String token = request.getParameter("token");
				String token2 = request.getParameter("token2");
				String sessionToken = request.getSession().getAttribute("token").toString();
				
				if(token == null && token2 == null) {
					RequestDispatcher isAgain = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					isAgain.forward(request, response);
					return;									
				}else if(token != null && token2 == null){
					if(token.equals(sessionToken)) {
						request.getSession().removeAttribute("token");
					}
				}else if (token == null && token2.equals("else")) {
					
				}
			}catch (Exception e) {
				RequestDispatcher isAgain = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				isAgain.forward(request, response);
				return;
			}
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				FriendManageService friendManageSvc = new FriendManageService();
				List<FriendManageVO> fmvoList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				String choosewho = request.getParameter("choosewho");
				
				FriendManageVO friendManageVO = new FriendManageVO();
				for(FriendManageVO fmvo: fmvoList) {
					if(fmvo.getFriend_member_fid().equals(choosewho)) {						
						friendManageVO.setFriend_member_fid(choosewho);
						friendManageVO.setFriend_member_id(memberVO.getMember_id());
						friendManageVO.setFriend_status(2);
						friendManageVO.setFriend_time(new Date(System.currentTimeMillis()));
					}
				}
				
				if(friendManageVO == null) {
					
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;
				}
				
				/*--------修改學伴狀態(取消狀態)----------*/
				
				friendManageSvc.updateFriendManage(friendManageVO.getFriend_member_id(), friendManageVO.getFriend_member_fid(), friendManageVO.getFriend_time(), 2); 
				
				/*-----------轉交至manageFriend.jsp--------------*/
				String requestURL = request.getParameter("requestURL");
				String url = null;
				if("/front-end/friend/listInviteMessage.jsp".equals(requestURL)) {
					url = "/front-end/friend/listInviteMessage.jsp";
				}else {
					url = "/front-end/friend/listManageFriend.jsp";
				}
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);				
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);	
			}
			
			
			
		}
		
		
		
		if("SayInvite".equals(action)) { //來自listFriend.jsp的請求(邀請)
			
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
			
			try {
				
				String token = request.getParameter("token");
				String token2 = request.getParameter("token2");
				String sessionToken = request.getSession().getAttribute("token").toString();
				
				if(token == null && token2 == null) {
					RequestDispatcher isAgain = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					isAgain.forward(request, response);
					return;									
				}else if(token != null && token2 == null){
					if(token.equals(sessionToken)) {
						request.getSession().removeAttribute("token");
					}
				}else if (token == null && token2.equals("else")) {
					
				}
				
			}catch (Exception e) {
				RequestDispatcher isAgain = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				isAgain.forward(request, response);
				return;
			}
			
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				FriendManageService friendManageSvc = new FriendManageService();
				List<FriendManageVO> fmvoList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				String choosewho = request.getParameter("choosewho");
				
				FriendManageVO friendManageVO = new FriendManageVO();
				for(FriendManageVO fmvo: fmvoList) {
					if(fmvo.getFriend_member_fid().equals(choosewho)) {
						
						friendManageVO.setFriend_member_fid(choosewho);
						friendManageVO.setFriend_member_id(memberVO.getMember_id());
						friendManageVO.setFriend_status(3);
						friendManageVO.setFriend_time(new Date(System.currentTimeMillis()));
					}
				}
				
				if(friendManageVO == null) {
					
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;
				}
				
				/*--------修改學伴狀態(送出邀請狀態)----------*/
				friendManageSvc.updateFriendManage(memberVO.getMember_id(), choosewho, friendManageVO.getFriend_time(), 3);
				
				
				try {
					
					/*--------寄出邀請信----------*/
					MessageService messageSvc = new MessageService();
					messageSvc.addMessage(friendManageVO.getFriend_member_fid(), null, new Date(System.currentTimeMillis()),memberVO.getMember_id(), 0);
					/*--------重導至manageFriend.jsp----------*/
					String requestURL = request.getParameter("requestURL");
					String url = null;
					if("/front-end/friend/listInviteMessage.jsp".equals(requestURL)) {
						url = "/front-end/friend/listInviteMessage.jsp";
					}else {
						url = "/front-end/friend/listManageFriend.jsp";
					}
								
					String location = request.getContextPath() + url;
					response.sendRedirect(location);					
				}catch (Exception e) {					
					e.printStackTrace();
				}				
				
				
			}catch (Exception e ) {
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);	
				
			}
			
		}
		
		
		
		
	}
	
	

}
