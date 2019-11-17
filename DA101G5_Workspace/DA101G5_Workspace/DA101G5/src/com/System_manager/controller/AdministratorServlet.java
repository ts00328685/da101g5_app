package com.System_manager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.System_manager.model.System_managerService;
import com.System_manager.model.System_managerVO;

@WebServlet("/AdministratorServlet")
public class AdministratorServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected System_managerVO isAdministrator(String SM_AC,String SM_PASS ) {
		System_managerService smSvc = new System_managerService();
		System_managerVO managerVO = smSvc.findSystem_managerByAccount(SM_AC);
		if(managerVO!=null && SM_PASS.equals(managerVO.getSm_pass())) {
			return managerVO;
		}
		return null;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
/***********************登入***********************************************************/		
		if("Adminlogin".equals(action)) {//來自管理員登入頁
			//接收參數 錯誤處理
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String SM_AC = null;
			String SM_PASS = null;
			
			if(req.getParameter("SM_AC").trim().length()==0) {//帳號處理
				errorMsgs.put("account","帳號請勿空白");
			}else {
				SM_AC = req.getParameter("SM_AC");
			}
			
			if(req.getParameter("SM_PASS").trim().length()==0) {//密碼處理
				errorMsgs.put("password","密碼請勿空白");
			}else {
				SM_PASS = req.getParameter("SM_PASS");
			}
			
			
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("/SMLogin.jsp").forward(req,resp);
				return;
			}
			
			//驗證帳密
			if(isAdministrator(SM_AC,SM_PASS)==null) {
				errorMsgs.put("warning","您並非管理員");
				req.getRequestDispatcher("/SMLogin.jsp").forward(req,resp);//非管理員
				return;
			}else {
				System_managerVO system_managerVO = isAdministrator(SM_AC,SM_PASS);
				HttpSession session = req.getSession();
				session.setAttribute("system_managerVO", system_managerVO);
				resp.sendRedirect(req.getContextPath()+"/back-end/home.jsp");
				return;
			}
		}
		/***********************登出***********************************************************/	
		if("Adminlogout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(req.getContextPath()+"/back-end/home.jsp");
			return;
		}
	}

}
