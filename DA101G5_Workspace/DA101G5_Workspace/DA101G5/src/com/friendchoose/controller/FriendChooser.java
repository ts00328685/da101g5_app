package com.friendchoose.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friendchoose.model.FriendChooseService;
import com.friendchoose.model.FriendChooseVO;
import com.member.model.MemberVO;
import com.friendmanage.model.FriendManageService;
import com.friendmanage.model.FriendManageVO;

public class FriendChooser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		/*-------------錯誤訊息----------------*/	
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		try {
					
			
			FriendChooseVO friendChooseVO = (FriendChooseVO) request.getAttribute("friendChooseVO");
			MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		
		/*-------------判斷是否重刷，有則無效，將直接轉到manageFriend.jsp(保留抽選結果)----------------*/		
			if(memberVO == null || memberVO.getFriend_choose() == 1 || friendChooseVO == null) {
				errorMsgs.add("您已抽過囉!");				
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/listManageFriend.jsp");
				failure.forward(request, response);
				return;									
			}
			
		/*-------------查詢符合條件的會員----------------*/
			FriendChooseService friendChooseSvc = new FriendChooseService();
			List<FriendChooseVO> list = friendChooseSvc.getMember(friendChooseVO.getCondition_language_id(),friendChooseVO.getCondition_sex());
			
			if (list.isEmpty()) {
				request.setAttribute("list", list);
				errorMsgs.add("再選一次吧!!!");
				RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/chooseFriend.jsp");
				failure.forward(request, response);
				return;
			}	
		/*-------------開始抽籤----------------*/
			
			FriendChooseVO chooseMember = new FriendChooseVO();
			FriendManageService friendManageSvc = new FriendManageService();
			FriendManageVO friendManageVO = new FriendManageVO();
			Boolean again = true;
			int count = 0;
			try {
				while(again) {
					count++;
					int number = (int) (Math.random() * list.size());
					chooseMember = list.get(number);
//					System.out.println(chooseMember.getCondition_member_id());
					
					if(count == 5) {
						errorMsgs.add("快來招募更多人來成為學伴吧!!");
						request.setAttribute("friendChooseVO", friendChooseVO);
						RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/chooseFriend.jsp");
						failure.forward(request, response);
						return;
					}
					
		/*-------------不能抽到自己----------------*/
					if(chooseMember.getCondition_member_id().equals(memberVO.getMember_id())) {
						continue;
					}
					
		/*-------------被抽到的人必須擁有學伴資格----------------*/
					if((friendChooseSvc.getOnePro(chooseMember.getCondition_member_id())).getFriend_appli() == 0) {
						
						continue;
					}
					
					
					
					if (chooseMember == null) {
						break;
					}
					
		/*-------------檢查是否抽到重複----------------*/							
					friendManageSvc = new FriendManageService();
					List<FriendManageVO> manageList = friendManageSvc.getOneAllFriends(memberVO.getMember_id());
					
					
					
					if (!(manageList.isEmpty())) {
						
						Boolean isRepeat = true;							
						for(int i = 0; i < manageList.size(); i++) {
							if (manageList.get(i).getFriend_member_fid().equals(chooseMember.getCondition_member_id())) {
								isRepeat = false;
								
							}
							
							if(isRepeat.equals(false) && (i == (manageList.size()-1))) {
								errorMsgs.add("快來招募更多人來成為學伴吧!!");
								request.setAttribute("friendChooseVO", friendChooseVO);
								RequestDispatcher failure = request.getRequestDispatcher("/front-end/friend/chooseFriend.jsp");
								failure.forward(request, response);
								return;
							}
							
						}
						
						if(isRepeat) {
							friendManageVO = friendManageSvc.addFriendManage(memberVO.getMember_id(), chooseMember.getCondition_member_id(), new Date(System.currentTimeMillis()));
							break;
						}
						
					}else {
						friendManageVO = friendManageSvc.addFriendManage(memberVO.getMember_id(), chooseMember.getCondition_member_id(), new Date(System.currentTimeMillis()));
						break;
					}							
				}//while的終點
				
		/*-------------查詢被抽中的會員&包裝其資料----------------*/							
				MemberVO memberPro = friendChooseSvc.getOnePro(chooseMember.getCondition_member_id());
				request.setAttribute("memberPro", memberPro);
				
		/*-------------更改抽籤狀態----------------*/
				memberVO.setFriend_choose(1);
				friendChooseSvc.updateMemberPro(memberVO);
				
		/*-------------包裝friendManageVO----------------*/		
				request.getSession().setAttribute("friendManageVO", friendManageVO);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/friend/listFriend.jsp");
				successView.forward(request, response);
				return;									
			}catch(Exception e) {
				
				e.printStackTrace();
			}	
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
