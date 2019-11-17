package com.friendmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;



import com.friendchoose.model.FriendChooseService;
import com.friendchoose.model.FriendChooseVO;
import com.friendmanage.model.FriendManageService;
import com.friendmanage.model.FriendManageVO;
import com.Language.model.LanguageService;
import com.Language.model.LanguageVO;
import com.member.model.MemberVO;
import com.message.model.MessageService;
import com.message.model.MessageVO;

public class FriendManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		
		
		MemberVO memberVO = null;
		
		if ("goToManage".equals(action)) {	//來自select_page.jsp的請求
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				FriendManageService friendManageSvc = new FriendManageService();			
				List<MemberVO> allFriendProfile = new ArrayList<MemberVO>();
				
				List<FriendManageVO> allFriendList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				
				
				if(!(allFriendList.isEmpty())) {
					
					for(FriendManageVO friendManageVO : allFriendList) {
						if(friendManageVO.getFriend_status() == 1) {
							MemberVO MemberProfile = (MemberVO) friendManageSvc.getOnePro(friendManageVO.getFriend_member_fid());
							allFriendProfile.add(MemberProfile);
						}
					}
					
					request.getSession().setAttribute("allFriendList", allFriendList);
					request.getSession().setAttribute("allFriendProfile", allFriendProfile);
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					successView.forward(request, response);
					return;
				}else {
					
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;				
				}
				
			}catch (Exception e) {
				
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
			
		}
		
		if("myInvite".equals(action)) {	//來自manageFriend.jsp的請求
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			List<MessageVO> inviteList = new ArrayList<MessageVO>();
			
			try {
				
				
				MessageService messageSvc = new MessageService();
				List<MessageVO> list = messageSvc.getOneAllMessages(memberVO.getMember_id());
				
				
				if(list.isEmpty()) {
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
					failure.forward(request, response);
					return;
				}
				
				for(MessageVO messageVO: list) {
					if(messageVO.getMember_id2() == null && messageVO.getMemmsg_state() == 0) {
						inviteList.add(messageVO);
					}
				}
				
				FriendManageService friendManageSvc = new FriendManageService();
				List<FriendManageVO> allFriendList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				
				
				
				if(!(inviteList.isEmpty())){					
					request.getSession().setAttribute("inviteList", inviteList);
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
					successView.forward(request, response);
					return;
				}else {	
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
					failure.forward(request, response);
					return;
				}
				
				
				
				
			}catch (Exception e) {
				
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
		}
		
		
		
		if("accept".equals(action)) {
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");	
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);			
			
			try {
				FriendManageService friendManageSvc = new FriendManageService();
				MessageService messageSvc = new MessageService();
				
				String whichOne = request.getParameter("whichOne");				
				List<MessageVO> list = (ArrayList<MessageVO>)request.getSession().getAttribute("inviteList");
				
				
				if(list.isEmpty()) {
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;
				}
				
				
				
				for(MessageVO messageVO: list) {
					if(messageVO.getMemmsg_ent().equals(whichOne)) {
			/*-------------修改抽者的FriendManageVO----------------*/
						friendManageSvc.updateFriendManage(whichOne, memberVO.getMember_id(), new Date(System.currentTimeMillis()), 1);
			/*-------------新增被抽者的FriendManageVO----------------*/						
						FriendManageVO friendManageVO = friendManageSvc.addFriendManage(memberVO.getMember_id(), whichOne, new Date(System.currentTimeMillis()));
						friendManageSvc.updateFriendManage(memberVO.getMember_id(), whichOne, friendManageVO.getFriend_time(), 1);
			/*-------------修改邀請通知的狀態----------------*/
						messageSvc.updateMessage(messageVO.getMessage_id(), messageVO.getMember_id(), messageVO.getMember_id2(), new Date(System.currentTimeMillis()), "已成功成為學伴!", 1);
						RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
						successView.forward(request, response);
						return;
					}
				}
				
			}catch (Exception e) {	
				
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
			}
			
			
		}
		
		if("reject".equals(action)) {
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");	
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);	
			
			try {
				FriendManageService friendManageSvc = new FriendManageService();
				MessageService messageSvc = new MessageService();
				
				String whichOne = request.getParameter("whichOne");				
				List<MessageVO> list = (ArrayList<MessageVO>)request.getSession().getAttribute("inviteList");
				
				
				if(list.isEmpty()) {					
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
					failure.forward(request, response);
					return;
				}
				
				for(MessageVO messageVO: list) {
					if(messageVO.getMemmsg_ent().equals(whichOne)) {
				/*-------------修改抽者的FriendManageVO----------------*/
						friendManageSvc.updateFriendManage(whichOne, messageVO.getMember_id(), new Date(System.currentTimeMillis()), 2);
				/*-------------修改邀請通知的狀態----------------*/
						messageSvc.updateMessage(messageVO.getMessage_id(), messageVO.getMember_id(), messageVO.getMember_id2(), new Date(System.currentTimeMillis()), "已取消學伴邀約", 1);
						RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listInviteMessage.jsp");
						successView.forward(request, response);
						return;
					}
				}
				
				
				
			}catch (Exception e) {
				
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
			
			
		}
		
		if("myFriend".equals(action)) {
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");	
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				FriendManageService friendManageSvc = new FriendManageService();			
				List<MemberVO> allFriendProfile = new ArrayList<MemberVO>();
				
				List<FriendManageVO> allFriendList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				
				
				if(!(allFriendList.isEmpty())) {
					
					for(FriendManageVO friendManageVO : allFriendList) {
						if(friendManageVO.getFriend_status() == 1) {
							MemberVO MemberProfile = (MemberVO) friendManageSvc.getOnePro(friendManageVO.getFriend_member_fid());
							allFriendProfile.add(MemberProfile);
						}
					}
					
					request.getSession().setAttribute("allFriendList", allFriendList);
					request.getSession().setAttribute("allFriendProfile", allFriendProfile);
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					successView.forward(request, response);
					return;
				}else {
					
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;				
				}
				
			}catch (Exception e) {
				
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
		}
		
		if("searchMyFriend".equals(action)) {
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");	
			
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);	
			
			try {
				
				Integer SearchType_sex = Integer.parseInt(request.getParameter("SearchType-sex"));
				String SearchType_lan = request.getParameter("SearchType-lan");
				
				FriendChooseService friendChooseSvc = new FriendChooseService();
				
				
				FriendManageService friendManageSvc = new FriendManageService();			
				List<MemberVO> allFriendProfile = new ArrayList<MemberVO>();
				
				List<FriendManageVO> allFriendList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
				
				for(FriendManageVO friendManageVO : allFriendList) {
					if(friendManageVO.getFriend_status() == 1) {
						MemberVO MemberProfile = (MemberVO) friendManageSvc.getOnePro(friendManageVO.getFriend_member_fid());
						allFriendProfile.add(MemberProfile);
					}
				}
				
				JSONArray result_friend = null;
				
				
				if(!(allFriendProfile.isEmpty())) {
					result_friend = new JSONArray();
					for(MemberVO mem: allFriendProfile) {
						JSONObject newMember = new JSONObject();
						newMember.put("member_id", mem.getMember_id());
						newMember.put("mem_nick", mem.getMem_nick());
						if(SearchType_sex != -1 && !("-1".equals(SearchType_lan))) {
							List<FriendChooseVO> list = friendChooseSvc.getMember(SearchType_lan, SearchType_sex);
							for(FriendChooseVO friendChooseVO:list) {
								if(mem.getMember_id().equals(friendChooseVO.getCondition_member_id())) {
									result_friend.put(newMember);
								}
							}
							System.out.println("no1= " + result_friend);
						}else if(SearchType_sex == -1 && !("-1".equals(SearchType_lan))){
							boolean isSame = true;
							List<FriendChooseVO> list = friendChooseSvc.getAll();
							for(FriendChooseVO friendChooseVO:list) {
								if(mem.getMember_id().equals(friendChooseVO.getCondition_member_id())) {
									if(SearchType_lan.equals(friendChooseVO.getCondition_language_id())) {
										if(result_friend.length() > 0) {
											for(int i = 0; i < result_friend.length();i++) {
												JSONObject m = (JSONObject)result_friend.get(i);
												if(m.get("member_id").equals(mem.getMember_id())) {
													break;
												}else {
													isSame = false;
												}
											}
											
											if(!isSame) {
												result_friend.put(newMember);
												break;
											}
										}else {
											result_friend.put(newMember);
											break;
										}
									}
								}
							}
							System.out.println("no2= " + result_friend);
						}else if (SearchType_sex != -1 && "-1".equals(SearchType_lan)){
							boolean isSame = true;
							List<FriendChooseVO> list = friendChooseSvc.getAll();
							for(FriendChooseVO friendChooseVO:list) {
								if(mem.getMember_id().equals(friendChooseVO.getCondition_member_id())) {
									if(SearchType_sex.equals(friendChooseVO.getCondition_sex())) {
										if(result_friend.length() > 0) {
											for(int i = 0; i < result_friend.length();i++) {
												JSONObject m = (JSONObject)result_friend.get(i);
												if(m.get("member_id").equals(mem.getMember_id())) {
													break;
												}else {
													isSame = false;
												}
											}
											
											if(!isSame) {
												result_friend.put(newMember);
												break;
											}
											
										}else {
											result_friend.put(newMember);
											break;
										}
									}
								}
							}
							System.out.println("no3= " + result_friend);
						}else{
							result_friend.put(newMember);
							System.out.println("no4= " + result_friend);
						}
						
					}
					
					
						
					
					
					System.out.println("result_friend= " + result_friend.toString());
					
					PrintWriter out = response.getWriter();
					out.write(result_friend.toString());
					out.flush();
					out.close();
				

					
					
				}else{	
					System.out.println("玩了");
					RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
					failure.forward(request, response);
					return;
				}
					
				
				
					
				
			}catch(Exception e) {				
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
		}
		
		if("cancelFriend".equals(action)) {
			
			memberVO = (MemberVO) request.getSession().getAttribute("memberVO");	
				
			
			/*-------------錯誤訊息----------------*/	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);	
			
			try {
				List<FriendManageVO> allFriendList = (List<FriendManageVO>)request.getSession().getAttribute("allFriendList");
				List<MemberVO> allFriendProfile = (List<MemberVO>)request.getSession().getAttribute("allFriendProfile");
				String who = request.getParameter("who");
				
			/*-------------修改雙方的學伴好友狀態----------------*/	
				FriendManageService friendManageSvc = new FriendManageService();
				FriendManageVO friendManageVO_m = friendManageSvc.updateFriendManage(memberVO.getMember_id(), who, new Date(System.currentTimeMillis()), 2);
				FriendManageVO friendManageVO_mf = friendManageSvc.updateFriendManage(who, memberVO.getMember_id(), new Date(System.currentTimeMillis()), 2);

			
				for(FriendManageVO mm: allFriendList) {
					if(who.equals(mm.getFriend_member_fid())) {
						allFriendList.remove(mm);
					}
				}
				
				for(MemberVO mm: allFriendProfile) {
					if(who.equals(mm.getMember_id())) {
						allFriendProfile.remove(mm);
					}
				}
				
				request.getSession().setAttribute("allFriendList",allFriendList);
				request.getSession().setAttribute("allFriendProfile",allFriendProfile);

				
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				successView.forward(request, response);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;
			}
		}
		
		
		
	}

}
