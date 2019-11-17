package com.friendchoose.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friendchoose.model.FriendChooseService;
import com.member.model.MemberVO;

public class WriteFriendPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/jpeg");
		
		String member_id = request.getParameter("member_id");
		ServletOutputStream out = response.getOutputStream();
		
		
		FriendChooseService friendChooseSvc = new FriendChooseService();
		MemberVO memberVO = friendChooseSvc.getOnePro(member_id);
		
		try {
			if(memberVO != null) {
				byte[] memberPic = memberVO.getFriend_pic();
				out.write(memberPic);
				out.close();
			}else {
				System.out.println("memberVO是null");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("front-end/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
		
		
		
	
	
	
	}

}
