package com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Teacher.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.main.tool.ImageUtil;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;


/**
 * Servlet implementation class Member_card_gorupServletApp
 */
@WebServlet("/MemberServletApp")
public class MemberServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		TeacherDAO dao = new TeacherDAO();
		Gson gson = new Gson();
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = new MemberDAO();
		
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
//		System.out.println("input: " + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		String action = jsonObject.get("action").getAsString();
		System.out.println(action);
		
		if ("getOne".equals(action)) {
			String member_id = jsonObject.get("member_id").getAsString();
			memberVO = memberDAO.findByPrimaryKey(member_id);
			memberVO.setMem_birthday(null);
			memberVO.setMem_createtime(null);
			memberVO.setFriend_pic(null);
			writeText(res, gson.toJson(memberVO));
//			System.out.println(gson.toJson(tclist));
			System.out.println("get one member success");
			
		} else if ("getImage".equals(action)) {
			String member_id = jsonObject.get("pk").getAsString();
			System.out.println("getImage: " + member_id);
			memberVO = memberDAO.findByPrimaryKey(member_id);
			OutputStream os = res.getOutputStream();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memberVO.getMem_pic();
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
				os.write(image);
				System.out.println("wrinting out image for " + member_id);
			}
			

		} if ("uploadImage".equals(action)) {
			String member_id = jsonObject.get("member_id").getAsString();
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			// java.util.Base64 (Java 8 supports)
			byte[] image = Base64.getMimeDecoder().decode(imageBase64);
			memberDAO.updatePic(member_id, image);
			System.out.println("pic upload success");
//			writeText(res, "success");
			
		} else  {
//			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText.substring(0, outText.length()/20));
//		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		List<TeacherVO> cardVOList = dao.getAll();
		writeText(res, new Gson().toJson(cardVOList));
	}
}
