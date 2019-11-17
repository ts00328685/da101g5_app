package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.friendchoose.model.FriendChooseService;
import com.friendchoose.model.FriendChooseVO;
import com.member.model.MailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member.model.sock2air;	


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded";
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("member_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(member_id);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_member.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/

				

				/***************************2.開始查詢資料****************************************/
				MemberVO memberVO = (MemberVO)req.getSession().getAttribute("memberVO");
				
				FriendChooseService friendChooseSvc =  new FriendChooseService();
				FriendChooseVO fcvo = new FriendChooseVO();
				List<FriendChooseVO> friendChooseList = friendChooseSvc.getAll();
				List<FriendChooseVO> new_friendChooseList = friendChooseList.stream()
															.filter(c -> c.getCondition_member_id().equals(memberVO.getMember_id()))
															.collect(Collectors.toList());
				
								
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);
				req.setAttribute("new_friendChooseList", new_friendChooseList);
				String requestURL = req.getParameter("requestURL");// 資料庫取出的memberVO物件,存入req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			} finally {
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				HttpSession session=req.getSession();
				MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
				
				String member_id = req.getParameter("member_id");

				String mem_name = req.getParameter("mem_name");
				
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_nick = new String(req.getParameter("mem_nick").trim());
				
				String mem_email = new String(req.getParameter("mem_email").trim());
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}
				java.sql.Date mem_birthday = null;
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出生日期!");
				}
				
				String mem_mobile = new String(req.getParameter("mem_mobile").trim());
				if (mem_mobile == null || mem_mobile.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				String mem_city = new String(req.getParameter("mem_city").trim());
				if (mem_city == null || mem_city.trim().length() == 0) {
					errorMsgs.add("城市請勿空白");
				}
			
				String mem_country = new String(req.getParameter("mem_country").trim());
				if (mem_country == null || mem_country.trim().length() == 0) {
					errorMsgs.add("國家請勿空白");
				}
				String mem_profile = new String(req.getParameter("mem_profile").trim());
				if (mem_profile == null || mem_profile.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}
				
				
				
//				Part part = req.getPart("mem_pic");
//				InputStream in = part.getInputStream();
//				byte[] mem_pic = new byte[in.available()];
//				in.read(mem_pic);
//				in.close();
		
				Part part1 = req.getPart("friend_pic");
				InputStream is = part1.getInputStream();
				byte[] friend_pic = new byte[is.available()];
				is.read(friend_pic);
				is.close();
				
				String mem_sex = req.getParameter("mem_sex");
				Double mem_point = null;
				
				String friend_profile = req.getParameter("friend_profile");			
				Double couponqty = null;
				
				
				Integer mem_status = new Integer(req.getParameter("mem_status").trim());
				Integer friend_choose = new Integer(req.getParameter("friend_choose").trim());
				Integer friend_appli = new Integer(req.getParameter("friend_appli").trim());
				
				java.sql.Date mem_createtime = null;
				try {
					mem_createtime = java.sql.Date.valueOf(req.getParameter("mem_createtime").trim());
				} catch (IllegalArgumentException e) {
					mem_createtime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出生日期!");
				}
				

				String mem_pwd = req.getParameter("mem_pwd").trim();
				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				Part part = req.getPart("mem_pic");
				String filename = getFileNameFromPart(part);
				byte[] mem_pic=null;
				boolean hasimg = true;
				
				
				memberVO.setMember_id(member_id);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_pwd(mem_pwd);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_nick(mem_nick);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_city(mem_city);
				memberVO.setMem_country(mem_country);
				memberVO.setMem_profile(mem_profile);
				memberVO.setMem_mobile(mem_mobile);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_status(mem_status);
				memberVO.setMem_pic(friend_pic);
				memberVO.setFriend_profile(friend_profile);
				memberVO.setFriend_choose(friend_choose);
				memberVO.setFriend_appli(friend_appli);
				memberVO.setMem_point(mem_point); 
				memberVO.setCouponqty(couponqty);
				memberVO.setMem_createtime(mem_createtime);
				memberVO.setMem_pic(mem_pic);
				
				if(filename== null || part.getContentType()==null) {
					hasimg = false;					
				} else {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);				
					memberVO.setMem_pic(mem_pic);							
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
//				if(hasimg)
					memberVO = memberSvc.updateMember(member_id, mem_name,mem_pwd, mem_nick, mem_email, mem_birthday, mem_mobile, mem_city, mem_country, mem_sex, mem_status, friend_pic, friend_profile, friend_choose, friend_appli, mem_point, couponqty, mem_profile, mem_createtime, mem_pic  
//				);else
//					memberVO = memberSvc.updateMember2(member_id, mem_name,mem_pwd, mem_nick, mem_email, mem_birthday, mem_mobile, mem_city, mem_country, mem_profile, mem_pic  
						
						);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
       if ("update2".equals(action)) { // 來自(front)update_member_input.jsp的請求
			
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session=req.getSession();
			MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
			
			
			
			try {
//				HttpSession session=req.getSession();
//				MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
				
				
				String member_id = memberVO.getMember_id();
				String mem_name = req.getParameter("mem_name");
				
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.put("mem_name","會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mem_name","會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
					
				String mem_nick = new String(req.getParameter("mem_nick").trim());
				if (mem_nick == null || mem_nick.trim().length() == 0) {
					errorMsgs.put("mem_nick","暱稱請勿空白");
				}
				
				String mem_email = new String(req.getParameter("mem_email").trim());
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.put("mem_email","信箱請勿空白");
				}
				java.sql.Date mem_birthday = null;
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("mem_birthday","請輸入出生日期!");
				}
				
				String mem_mobile = new String(req.getParameter("mem_mobile").trim());
				if (mem_mobile == null || mem_mobile.trim().length() == 0) {
					errorMsgs.put("mem_mobile","電話請勿空白");
				}
				String mem_city = new String(req.getParameter("mem_city").trim());
				if (mem_city == null || mem_city.trim().length() == 0) {
					errorMsgs.put("mem_city","城市請勿空白");
				}
			
				String mem_country = new String(req.getParameter("mem_country").trim());
				if (mem_country == null || mem_country.trim().length() == 0) {
					errorMsgs.put("mem_country","國家請勿空白");
				}
				String mem_profile = new String(req.getParameter("mem_profile").trim());
				if (mem_profile == null || mem_profile.trim().length() == 0) {
					errorMsgs.put("mem_profile","簡介請勿空白");
				}
				
				String mem_sex = new String(req.getParameter("mem_sex"));
				
				
					
				
				/*圖片處理*/				
				byte[] friend_pic = null;
				byte[] mem_pic = null;
				String encodedTextM = null;
				String encodedTextF = null;
				
				String encodedM = req.getParameter("mem_picStr");				
				if("".equals(encodedM)||encodedM == null) {
					Part part = req.getPart("mem_pic");
					if(part.getSize() != 0) {
						InputStream in = part.getInputStream();
						mem_pic = new byte[in.available()];
						in.read(mem_pic);
						in.close();
						memberVO.setMem_pic(mem_pic);
						
						Base64.Encoder encoder = Base64.getEncoder();
						encodedTextM = encoder.encodeToString(mem_pic);
					}else {
						memberVO.setMem_pic(memberVO.getMem_pic());
					}
				}else {
					Base64.Decoder decoder = Base64.getDecoder();
					byte[] bufferM = decoder.decode(encodedM);
					memberVO.setMem_pic(bufferM);
				}
				
				
				String encodedF = req.getParameter("friend_picStr");
				if("".equals(encodedF) || encodedF == null) {
					Part partF = req.getPart("friend_pic");
					
					if(partF.getSize() != 0) {
						InputStream in = partF.getInputStream();
						friend_pic = new byte[in.available()];
						in.read(friend_pic);
						in.close();
						memberVO.setFriend_pic(friend_pic);
						
						Base64.Encoder encoder = Base64.getEncoder();
						encodedTextF = encoder.encodeToString(friend_pic);
					}else {
						memberVO.setFriend_pic(memberVO.getFriend_pic());
					}
				}else {
					Base64.Decoder decoder = Base64.getDecoder();
					byte[] bufferF = decoder.decode(encodedF);
					memberVO.setFriend_pic(bufferF);
				}
				
				
				/*學伴簡介*/
				String friend_profile = req.getParameter("friend_profile").trim();
				if(friend_profile.length() == 0 || friend_profile == null) {
					memberVO.setFriend_profile(null);
				}else {
					memberVO.setFriend_profile(friend_profile);
				}
				
				
				FriendChooseService friendChooseSvc =  new FriendChooseService();
				FriendChooseVO friendChooseVO = null;
				
				
				/*語言選擇*/
				String[] lans = req.getParameterValues("condition_language_id");
				

				
				
			
				
				
				
				/*包裝-membervo*/
			    memberVO.setMember_id(member_id);
			    memberVO.setMem_pwd(memberVO.getMem_pwd());
			    memberVO.setMem_name(mem_name);
			    memberVO.setMem_nick(mem_nick);
			    memberVO.setMem_email(mem_email);
			    memberVO.setMem_birthday(mem_birthday);
			    memberVO.setMem_mobile(mem_mobile);
			    memberVO.setMem_city(mem_city);
			    memberVO.setMem_country(mem_country);
			    memberVO.setMem_profile(mem_profile);			    
			    memberVO.setMem_status(memberVO.getMem_status());
			    memberVO.setFriend_choose(memberVO.getFriend_choose());
			    memberVO.setMem_createtime(memberVO.getMem_createtime());
			    memberVO.setCouponqty(memberVO.getCouponqty());
			    memberVO.setMem_point(memberVO.getMem_point());
			    memberVO.setMem_sex(mem_sex);
			    
			  
			    
			    /*學伴資格判斷*/
			    MemberService memberSvc1 = new MemberService();
			    Integer appli = memberSvc1.getOneMember(memberVO.getMember_id()).getFriend_appli();
			    Integer choose = memberSvc1.getOneMember(memberVO.getMember_id()).getFriend_choose();
			    if(memberVO.getFriend_pic() != null && friend_profile.length() != 0 && memberVO.getMem_status() != 0  && lans != null) {
			    	appli = 1;
			    	choose = 0;
			    	memberVO.setFriend_appli(appli);
			    	memberVO.setFriend_choose(choose);
			    }else {
			    	appli = 0;
			    	choose = 0;
			    	memberVO.setFriend_appli(appli);
			    	memberVO.setFriend_choose(choose);
			    }
			    

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("encodedTextF", encodedTextF);
					req.setAttribute("encodedTextM", encodedTextM);
					req.setAttribute("lans", lans);
					req.setAttribute("memberVO", memberVO);// 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				
				
				
				/***************************2.開始修改資料*****************************************/
				/*修改member資料*/
				memberSvc1.updateMember(memberVO);
				
				/*修改chooser的資料*/
				List<FriendChooseVO> list = friendChooseSvc.getAll();
				List<FriendChooseVO> isMemberList = null;
				
				isMemberList = list.stream().filter(fc -> fc.getCondition_member_id().equals(memberVO.getMember_id())).collect(Collectors.toList());
				
				if(isMemberList.size() == 0) { //如果這個人不在chooser中
					
						if(lans != null) { //有勾選任何選項
							for(int i = 0; i < lans.length;i++) {						
								
								if("男".equals(memberVO.getMem_sex())) {							
									friendChooseSvc.addFriendChoose(lans[i], memberVO.getMember_id(), 0);
								}else {
									friendChooseSvc.addFriendChoose(lans[i], memberVO.getMember_id(), 1);
								}						
							}					
						}else { //沒有勾選任何選項
							req.setAttribute("memberVO", memberVO);
							req.setAttribute("friendChooseVO", friendChooseVO); // 資料庫update成功後,正確的的memberVO物件,存入req
							String url = "/front-end/member/listOneMember.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
							successView.forward(req, res);
							return;//中斷程式
						}						
					
				}else {
						/*原先資料刪除*/
						for(FriendChooseVO friendChooseVO2:isMemberList) {
							friendChooseSvc.deleteFriendChoose(friendChooseVO2.getCondition_id());												
						}
						
						/*增加新資料*/
						if(lans != null) {
							for(int i = 0; i < lans.length;i++) {				
								
								if("男".equals(memberVO.getMem_sex())) {							
									friendChooseSvc.addFriendChoose(lans[i], memberVO.getMember_id(), 0);
								}else {
									friendChooseSvc.addFriendChoose(lans[i], memberVO.getMember_id(), 1);
								}
								
							}						
						}else {
							req.setAttribute("memberVO", memberVO);
							req.setAttribute("friendChooseVO", friendChooseVO); // 資料庫update成功後,正確的的memberVO物件,存入req
							String url = "/front-end/member/listOneMember.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
							successView.forward(req, res);
							return;//中斷程式
						}
				}
				
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO);
				req.setAttribute("friendChooseVO", friendChooseVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} 
			catch (Exception e) {
				errorMsgs.put("all","修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
				

       if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			try {	
				
//				if (mem_ac == null || mem_ac.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				}
				
				String mem_name = req.getParameter("mem_name");
				
//				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				String mem_ac = req.getParameter("mem_ac").trim();
				
				String mem_email = req.getParameter("mem_email").trim();
//				if (mem_email == null || mem_email.trim().length() == 0) {
//					errorMsgs.add("信箱請勿空白");
//				}
				
				String mem_pwd = req.getParameter("mem_pwd").trim();
//				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
//					errorMsgs.add("密碼請勿空白");
//				}
				String mem_nick = req.getParameter("mem_nick").trim();
				String mem_sex = req.getParameter("mem_sex").trim();
				
				
				String mem_city = req.getParameter("mem_city").trim();
				String mem_country = req.getParameter("mem_country").trim();
				
				
				
				
				String mem_mobile = req.getParameter("mem_mobile").trim();
				
				
				java.sql.Date mem_birthday = null;
				
				mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday"));
				
				
				java.sql.Date mem_createtime = null;
				mem_createtime = new java.sql.Date(System.currentTimeMillis());
				
				
				String mem_profile = req.getParameter("mem_profile").trim();
				
				req.setCharacterEncoding("UTF-8"); // 處理中文檔名
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				System.out.println("ContentType="+req.getContentType()); // 測試用

				String realPath = getServletContext().getRealPath(saveDirectory);
				System.out.println("realPath="+realPath); // 測試用
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists())
					 fsaveDirectory.mkdirs();
				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				out.write("<h2> Total parts : " + parts.size() + "</h2>");

			
				
				
				Part part = req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
				
//				Integer mem_status = new Integer(req.getParameter("mem_status").trim());
				
				MemberVO memberVO = new MemberVO();
				
				
				memberVO.setMem_name(mem_name);
//				memberVO.setMem_ac(mem_ac);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_pwd(mem_pwd);
				memberVO.setMem_nick(mem_nick);
			
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_city(mem_city);
				memberVO.setMem_country(mem_country);
				memberVO.setMem_mobile(mem_mobile);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_createtime(mem_createtime);
				memberVO.setMem_pic(mem_pic);
				memberVO.setMem_profile(mem_profile);
				
				
				
				
//				memberVO.setMem_status(mem_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_name, mem_email, mem_pwd, mem_nick, mem_sex,mem_city, mem_country,mem_mobile, mem_birthday, mem_createtime,  mem_pic, 1, mem_profile);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);		
				
				  String to = req.getParameter("mem_email");
//				  String mem = req.getParameter("mem_name");
				  
			      String subject = "親愛的會員您好，懶咕雞家教學習網感謝您的註冊。";
//			      String ac = req.getParameter("mem_ac");
			      String url2 = "https://10.120.39.20:8443"+req.getContextPath()+"/index.jsp";
			      String ch_name = req.getParameter("mem_name");
			      String passRandom = req.getParameter("mem_pwd");
			      String member_id = memberVO.getMember_id();
			      String messageText = "哈囉! " + ch_name + "\n"+ " 請謹記此帳號: " + member_id + "\n" + " 密碼 :" + passRandom  + "(已經啟用)" + "\n" + url2; 
			      
			      System.out.println("Servlet新增完後的memberid= " + member_id);
			      
			      Send se = new Send();
		    	  String[] tel ={mem_mobile};
		    	  String message = "親愛的" + ch_name + "會員你好，感謝你註冊懶咕雞家教學習網。";
		    	  se.sendMessage(tel , message);	
		    	    
			              
			      MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/register.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String member_id = new String(req.getParameter("member_id"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(member_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		}
		
		// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			System.out.println("header=" + header); // 測試用
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			System.out.println("filename=" + filename); // 測試用
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		
	}
		public class Send {

			  public void sendMessage(String[] tel , String message)  {

			  try {
			      String server  = "203.66.172.131"; //Socket to Air Gateway IP
			      int port	     = 8000;             //Socket to Air Gateway Port

			      String user    = "85559671"; //帳號
			      String passwd  = "2irioiai"; //密碼
			      String messageBig5 = new String(message.getBytes(),"UTF-8"); //簡訊內容

			      //----建立連線 and 檢查帳號密碼是否錯誤
			      sock2air mysms = new sock2air();
			      int ret_code = mysms.create_conn(server,port,user,passwd) ;
			      if( ret_code == 0 ) {
			           System.out.println("帳號密碼Login OK!");
			      } else {
			      	   System.out.println("帳號密碼Login Fail!");
			           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
			           //結束連線
			           mysms.close_conn();
			           return ;
			      }

			      //傳送文字簡訊
			      //如需同時傳送多筆簡訊，請多次呼叫send_message()即可。
			      for(int i=0 ; i<tel.length ; i++){  
			        ret_code=mysms.send_message(tel[i],messageBig5);
			        if( ret_code == 0 ) {
			           System.out.println("簡訊已送到簡訊中心!");
			           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
			        } else {
			           System.out.println("簡訊傳送發生錯誤!");
			           System.out.print("ret_code="+ret_code+",");
			           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
			           //結束連線
			           mysms.close_conn();
			           return ;
			        }
			      }

			      //結束連線
			      mysms.close_conn();

			  }catch (Exception e)  {

			      System.out.println("I/O Exception : " + e);
			   }
			 }
		}
}


