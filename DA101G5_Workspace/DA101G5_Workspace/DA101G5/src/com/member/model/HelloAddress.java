package com.member.model;
//package servlet_examples;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloAddress extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
   	req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

    String name = req.getParameter("name");
    String zone1 = req.getParameter("zone1");
    String zone2 = req.getParameter("zone2");
    String zipcode = req.getParameter("zipcode");
    String address = req.getParameter("address");
    address = zone1+" "+zone2+" 郵遞區號"+zipcode+" "+address;
    out.println("<HTML>");
    out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
    out.println("<BODY>");
    out.println("Hello, 你好: " + name);
    out.println("<br>");
    out.println("Hello, 國家: " + address);
    
   
    out.println("</BODY></HTML>");
  }
}
