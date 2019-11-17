package login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginhandlerapp")
public class LoginHandlerApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected MemberVO allowUser(String member_id, String mem_pwd) {

		MemberService ms = new MemberService();
		MemberVO memberVO = ms.getOneMember(member_id);
		if (memberVO != null && mem_pwd.equals(memberVO.getMem_pwd()))
			return memberVO;
		else
			return null;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(2222);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		String member_id = jsonObject.get("account").getAsString().toUpperCase();
		String mem_pwd = jsonObject.get("password").getAsString();
		
		MemberVO memberVO = allowUser(member_id, mem_pwd);
		
		
		if (memberVO == null) { // 【帳號 , 密碼無效時】
			
			out.print("failed");
			System.out.println("verification failed");
			
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			
			memberVO.setFriend_pic(null);
			
			try {
			JsonObject memJson = gson.fromJson(gson.toJson(memberVO), JsonObject.class);
//			memJson.addProperty("friend_pic", 
//					(memberVO.getFriend_pic()!=null) ? Base64.getEncoder().encodeToString(memberVO.getFriend_pic()) : "");
//			
			out.print(memJson.toString());
			System.out.println("verification ok");
			} catch (Exception e) {
				
				out.print("vogg");
				e.printStackTrace();
			}
		}
	}
}