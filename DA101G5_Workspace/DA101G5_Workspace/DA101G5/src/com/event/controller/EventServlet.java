package com.event.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.event.model.EventService;
import com.event.model.EventVO;
@MultipartConfig()
public class EventServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/*******新增活動--後台************************************************************/
		if("addevent".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//1.接收參數 + 錯誤處理
			String evtitle = null;
			String evcontent = null;
			byte[] evpic = null;
			String filetype = null;
			
			if(req.getPart("evpic").getSize()!=0) {
				Part part = req.getPart("evpic");
				filetype = part.getContentType();
				InputStream is = part.getInputStream();
				evpic = new byte[is.available()];
				is.read(evpic);
				is.close();
				/*檔案錯誤處理*/
				if (filetype!=null && !filetype.equals("audio/webm")  && !filetype.equals("video/webm") && !filetype.equals("image/png")
						&& !filetype.equals("image/jpeg") && !filetype.equals("image/gif") && !filetype.equals("image/bmp")
						) {
					errorMsgs.put("evpic","不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不得超過10MB");
				}
				
				if (part.getSize() >= 1024 * 1024 * 10) {// 10MB
					errorMsgs.put("evpic","不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不得超過10MB");
				}
			}
			
			/*標題錯誤*/
			if(req.getParameter("evtitle").trim().length()==0) {
				errorMsgs.put("evtitle","標題不可為空");
			}else {
				evtitle = req.getParameter("evtitle");
			}
			
			/*內容錯誤*/
			if(req.getParameter("evcontent").trim().length()==0) {
				errorMsgs.put("evcontent","內容不可為空");
			}else {
				evcontent = req.getParameter("evcontent");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/event/AddEvent.jsp");
				failureView.forward(req, resp);
				return;
			}
			
			//2.存入DB
			EventService eventSvc = new EventService();
			eventSvc.addEvent(evtitle, null, null, evcontent, evpic, filetype);;
			
			//3.回到活動區首頁
			resp.sendRedirect(req.getContextPath()+"/back-end/event/EventIndex.jsp");
		}
		
		/************修改活動--後台***********************************************/
		if("editevent".equals(action)) {//從後台首頁來的修改請求
			
			//1.接收參數
			String event_id = req.getParameter("event_id");
			
			
			//2.DB查詢
			EventService eventSvc = new EventService();
			EventVO eventVO = eventSvc.findEventByPK(event_id); 
			
			//3.轉送UpdateEvent.jsp
			req.setAttribute("eventVO", eventVO);
			RequestDispatcher reqTruck = req.getRequestDispatcher("/back-end/event/UpdateEvent.jsp");
			reqTruck.forward(req, resp);
		}
		
		if("updateeventconfirm".equals(action)) {//從UpdateEvent來的修改請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//1.接收參數 處理錯誤
			String event_id = null;
			String evtitle = null;
			String evcontent = null;
			byte[] evpic= null;
			String filetype = null;
			Integer evstatus = null;
			
			event_id = req.getParameter("event_id");
			
			if(req.getParameter("evtitle").trim().length()==0) {//標題錯誤處理
				errorMsgs.put("evtitle", "標題不可為空");
			}else {
				evtitle = req.getParameter("evtitle");
			}
			
			if(req.getParameter("evcontent").trim().length()==0) {//內文處理
				errorMsgs.put("evcontent", "內文不可為空");
			}else {
				evcontent = req.getParameter("evcontent");
			}
			
			//檔案處理
			//新空舊有
			if(req.getParameter("orievpic")!=null && req.getPart("newevpic").getSize()==0) {
				EventService eventSvc =  new EventService();
				EventVO eventVO = eventSvc.findEventByPK(event_id);
				evpic = eventVO.getEvpic();
				filetype = eventVO.getFiletype();
			}
			
			//新有
			if(req.getPart("newevpic").getSize()!=0) {
				Part part = req.getPart("newevpic");
				filetype = part.getContentType();
				
				if (filetype!=null && !filetype.equals("audio/webm")  && !filetype.equals("video/webm") && !filetype.equals("image/png")
						&& !filetype.equals("image/jpeg") && !filetype.equals("image/gif") && !filetype.equals("image/bmp")
						) {//檔案類型錯誤處理
					errorMsgs.put("evpic","不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不得超過10MB");
				}
				
				if (part.getSize() >= 1024 * 1024 * 10) {//檔案大小錯誤處理
					errorMsgs.put("evpic","不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不得超過10MB");
				}
				
				InputStream is = part.getInputStream();
				evpic = new byte[is.available()];
				is.read(evpic);
				is.close();
			}
			
			//新空舊空
			if(req.getPart("newevpic").getSize()==0 && req.getParameter("orievpic").trim().length()==0) {
				evpic = new byte[0] ;
			}
			
			evstatus = Integer.parseInt(req.getParameter("evstatus"));
			
			//2.送入DB修改
			EventService eventSvc = new EventService();
			eventSvc.updateEvent(event_id, evtitle, null, null, evcontent, evpic, evstatus, filetype);
			
			//3.回活動首頁(後台)
			resp.sendRedirect(req.getContextPath()+"/back-end/event/EventIndex.jsp");
		}
	}
	
}
