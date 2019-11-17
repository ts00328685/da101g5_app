package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {
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

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String member_id = req.getParameter("member_id");
		String mem_pwd = req.getParameter("mem_pwd");

		// 【檢查該帳號 , 密碼是否有效】
		MemberVO memberVO = allowUser(member_id, mem_pwd);

		if ((memberVO = allowUser(member_id, mem_pwd)) == null) { // 【帳號 , 密碼無效時】
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>你的帳號 , 密碼無效!<BR>");
			out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/index.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			HttpSession session = req.getSession();
			session.setAttribute("memberVO", memberVO); // *工作1: 才在session內做已經登入過的標識

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			res.sendRedirect(req.getContextPath() + "/index.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}
	}
}