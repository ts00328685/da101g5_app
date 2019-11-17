package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.Teacher.model.TeacherVO;

public class TeacherFilter implements Filter{
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
	    req.setCharacterEncoding("UTF-8");
	    res.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object ob = (TeacherVO) session.getAttribute("teacherVO");
		TeacherVO teacher = null;
		
		if(ob instanceof TeacherVO) {
			 teacher=(TeacherVO) ob;
		}
		if (teacher!=null&&teacher.getTeacher_state() != 1) {
//			System.out.println(req.getRequestURI());
			String location=(String) session.getAttribute("location");
			session.setAttribute("location", req.getRequestURI());
//			res.sendRedirect(req.getContextPath() + "/index.jsp");
			
			session.setAttribute("isTeacher1", false);
			res.sendRedirect(location);
		} else {
			chain.doFilter(request, response);
		}
		
	}

}
