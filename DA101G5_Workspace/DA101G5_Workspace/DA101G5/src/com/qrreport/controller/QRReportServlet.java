package com.qrreport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qrreport.model.QrrepoService;
import com.que.model.QueService;
import com.que.model.QueVO;
import com.res.model.ResService;
import com.res.model.ResVO;

public class QRReportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").trim();
		
		/*********處理檢舉(修改檢舉狀態+修改問題回應顯示狀態)--後台******************************/
		if("qrreportconfirm".equals(action)) {
			//1.問題的處理  修改檢舉單狀態+問題顯示狀態
			if(req.getParameter("que_id").trim().length()!=0) {
				//接收參數
				String que_id = req.getParameter("que_id").trim();
				Integer questatus = Integer.parseInt(req.getParameter("questatus"));
				String qrrepo_id = req.getParameter("qrrepo_id").trim();
				Integer qrrepostatus = Integer.parseInt(req.getParameter("qrrepostatus"));
				
				//資料修改
				QrrepoService qrrepoSvc = new QrrepoService();
				QueService queSvc = new QueService();
				qrrepoSvc.updateQrrepo(qrrepo_id, qrrepostatus);
				if(questatus==1) {
					queSvc.onshelfQue(que_id);
				}else {
					queSvc.hideQue(que_id);
				}
				
				//回檢舉區首頁
				resp.sendRedirect(req.getContextPath()+"/back-end/qrreport/QRReportIndex.jsp");
				return;
			}
			
			//回應的處理 修改檢舉單狀態+回應顯示狀態
			if(req.getParameter("res_id").trim().length()!=0) {
				//接收參數
				String res_id = req.getParameter("res_id").trim();
				Integer resstatus = Integer.parseInt(req.getParameter("resstatus"));
				String qrrepo_id = req.getParameter("qrrepo_id").trim();
				Integer qrrepostatus = Integer.parseInt(req.getParameter("qrrepostatus"));
				
				//資料修改
				QrrepoService qrrepoSvc = new QrrepoService();
				ResService resSvc = new ResService();
				qrrepoSvc.updateQrrepo(qrrepo_id, qrrepostatus);
				if(resstatus==1) {
					resSvc.onshelfRes(res_id);
				}else {
					resSvc.hideRes(res_id);
				}
				
				//回檢舉區首頁
				resp.sendRedirect(req.getContextPath()+"/back-end/qrreport/QRReportIndex.jsp");
				return;
			}
		}
/**************檢舉區listone***************************************************/		
		if("qrreportlistone".equals(action)) {
			String que_id = null;
			String res_id = null;
			//問題
			if(req.getParameter("que_id").trim().length()!=0) {
				que_id = req.getParameter("que_id").trim();
				QueService queSvc = new QueService();
				QueVO queVO = queSvc.findQueByPK(que_id);
				if(queVO==null) {
					resp.sendRedirect(req.getContextPath()+"/back-end/qrreport/QRReportIndex.jsp");
					return;
				}else {
					req.setAttribute("queVO", queVO);
					req.getRequestDispatcher("/back-end/qrreport/QRlistone.jsp").forward(req, resp);
				}
			}
			//回應
			if(req.getParameter("res_id").trim().length()!=0) {
				res_id = req.getParameter("res_id").trim();
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.findResByPK(res_id);
				if(resVO==null) {
					resp.sendRedirect(req.getContextPath()+"/back-end/qrreport/QRReportIndex.jsp");
					return;
				}else {
					req.setAttribute("resVO", resVO);
					req.getRequestDispatcher("/back-end/qrreport/QRlistone.jsp").forward(req, resp);
				}
			}
		}
	}

}
