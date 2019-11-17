package com.commodity.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;

/**
 * Servlet implementation class CommodityMP3Servlet
 */
@WebServlet("/commoditypicservlet")
public class CommodityPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
		  stream = response.getOutputStream();
		  
		  CommodityVO commodityVO = new CommodityVO();
		  CommodityService commoditySvc = new CommodityService();
		  commodityVO = commoditySvc.getOneCommodity(request.getParameter("com_id"));
		  InputStream is = new ByteArrayInputStream(commodityVO.getCom_pic());
		  //set response headers
		  response.setContentType("image/png"); 

//		  response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

		  response.setContentLength(is.available());

		  buf = new BufferedInputStream(is);
		  int readBytes = 0;
		  //read from the file; write to the ServletOutputStream
		  while ((readBytes = buf.read()) != -1)
			  
		    stream.write(readBytes);
		  
		} catch (IOException ioe) {
		  throw new ServletException(ioe.getMessage());
		} finally {
		  if (stream != null)
		    stream.close();
		  if (buf != null)
		    buf.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
