package com.card.controller;

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

import com.card.model.CardService;
import com.card.model.CardVO;

/**
 * Servlet implementation class CardMP3Servlet
 */
@WebServlet("/cardmp3servlet")
public class CardMP3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
		  stream = response.getOutputStream();
		  
		  CardVO cardVO = new CardVO();
		  CardService cardSvc = new CardService();
		  cardVO = cardSvc.getOneCard(request.getParameter("card_id"));
		  InputStream is = new ByteArrayInputStream(cardVO.getVoice());
		  //set response headers
		  response.setContentType("audio/mpeg"); 

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
