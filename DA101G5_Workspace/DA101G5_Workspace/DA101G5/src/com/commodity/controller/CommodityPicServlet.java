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

public class CommodityPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		
		  stream = response.getOutputStream();
		  
		  CommodityService commoditySvc = new CommodityService();
		  CommodityVO commodityVO = commoditySvc.getOneCommodity(request.getParameter("com_id"));
		 
		  try {
				if(commodityVO != null) {
					byte[] com = commodityVO.getCom_pic();
					stream.write(com);
//					
				}
//		  commodityVO = commoditySvc.getOneCommodity(request.getParameter("com_id"));
//		  InputStream is = new ByteArrayInputStream(commodityVO.getCom_pic());
//		  //set response headers
//		  response.setContentType("image/png"); 
//
////		  response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//
//		  response.setContentLength(is.available());
//
//		  buf = new BufferedInputStream(is);
//		  int readBytes = 0;
//		  //read from the file; write to the ServletOutputStream
//		  while ((readBytes = buf.read()) != -1)
//			  
//		    stream.write(readBytes);
		  
		} catch (Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("front-end/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			stream.write(b);
			in.close();
		} finally {
		  if (stream != null) {
			  stream.close();
		  }
		}
		
		    
		  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
