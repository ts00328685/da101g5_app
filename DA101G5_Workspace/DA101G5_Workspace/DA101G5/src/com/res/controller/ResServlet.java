package com.res.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Teacher.model.TeacherService;
import com.qhashtag.model.QhashtagService;
import com.qhashtag.model.QhashtagVO;
import com.qrreport.model.QrrepoService;
import com.que.model.QueService;
import com.que.model.QueVO;
import com.res.model.ResService;
import com.res.model.ResVO;
import com.thumbrecordque.model.ThumbrecordqueService;
import com.thumbrecordque.model.ThumbrecordqueVO;
import com.thumbrecordres.model.ThumbrecordresService;
import com.thumbrecordres.model.ThumbrecordresVO;

@MultipartConfig()
public class ResServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/********** 回文--前台 **************************************/
		if ("response".equals(action)) {
			// 0.建立錯誤訊息陣列
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 1.接收請求參數+錯誤處理
			String quemember = req.getParameter("quemember");
			String member_id = null;
			String belongto = null;
			String rescontent = null;
			byte[] resfile = null;
			String filetype = null;

			TeacherService teacherSvc = new TeacherService();

			if (req.getParameter("member_id").trim().length() == 0) {// 會員處理
				errorMsgs.put("member_id", "欲回應請先登入會員 或僅在本站具有教師資格的會員才能回應");
			} else {
				member_id = req.getParameter("member_id");
			}

			if ((teacherSvc.getOneTeacherWithMem(member_id) == null
					|| teacherSvc.getOneTeacherWithMem(member_id).getTeacher_state() == 0)
					&& (quemember.equals(member_id) != true)) {// (非教師or審核中)&.非發問者
				errorMsgs.put("member_id", "欲回應請先登入會員 或僅在本站具有教師資格的會員才能回應");
			}

			belongto = req.getParameter("belongto");// 所屬問題

			if (req.getParameter("rescontent").trim().length() == 0) {// 內文處理
				errorMsgs.put("rescontent", "回應不可為空");
			} else {
				rescontent = req.getParameter("rescontent");
			}
			// 檔案處理
			if (req.getPart("resfile").getSize() == 0) {// 沒上傳檔案
				resfile = new byte[0];
			}

			if (req.getPart("resfile").getSize() != 0L) {// 有上傳檔案
				Part part = req.getPart("resfile");
				InputStream is = part.getInputStream();
				resfile = new byte[is.available()];
				is.read(resfile);
				is.close();
				filetype = part.getContentType();

				if (filetype != null && !filetype.equals("audio/webm") && !filetype.equals("video/webm")
						&& !filetype.equals("image/png") && !filetype.equals("image/jpeg")
						&& !filetype.equals("image/gif") && !filetype.equals("image/bmp")

				) {// 檔案類型篩選
					errorMsgs.put("resfile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案");
				}

				if (part.getSize() > 1024 * 1024 * 5) {
					errorMsgs.put("resfile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案");
				}
			}

			if (!errorMsgs.isEmpty()) {
				QueService queSvc = new QueService();
				QueVO queVO = queSvc.findQueByPK(belongto);
				ResService resSvc = new ResService();
				List<ResVO> reslist = resSvc.findResByBelongto(belongto);
				QhashtagService qhashtagSvc = new QhashtagService();
				List<QhashtagVO> qhashtaglist = qhashtagSvc.findQhashtagByQue(belongto);
				req.setAttribute("reslist", reslist);
				req.setAttribute("qhashtaglist", qhashtaglist);
				req.setAttribute("queVO", queVO);
				RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/Discuss/DiscussThread.jsp");
				requestTruck.forward(req, resp);
				return;
			}

			// 2.做VO 新增入DB
			ResService resSvc = new ResService();
			resSvc.addRes(member_id, rescontent, resfile, belongto, filetype);

			// 3.轉交請求 該問題回應數+1
			QueService queSvc = new QueService();
			QueVO queVO = queSvc.findQueByPK(belongto);
			queSvc.updateQue(queVO.getQuethumb(), queVO.getWatchcount(), queVO.getRescount() + 1, queVO.getQue_id());// 回應數+1
//			List<ResVO> reslist = resSvc.findResByBelongto(belongto);
//			QhashtagService qhashtagSvc = new QhashtagService();
//			List<QhashtagVO> qhashtaglist = qhashtagSvc.findQhashtagByQue(belongto);
//			req.setAttribute("reslist", reslist);
//			req.setAttribute("qhashtaglist", qhashtaglist);
//			req.setAttribute("queVO", queVO);
//			RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/Discuss/DiscussThread.jsp");
//			requestTruck.forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/front-end/Discuss/forwardto.jsp?que_id="+belongto+"&action=tothread");
			
		}
		/********** 接收RES檢舉--前台 **************************************/
		if ("reportRes".equals(action)) {
			// 1.接收參數 處理錯誤
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String que_id = req.getParameter("que_id");
			String res_id = req.getParameter("res_id");
			;
			String qrrepocontent = null;

			if (req.getParameter("qrrepocontent").trim().length() == 0) {
				errorMsgs.put("qrrepocontent", "檢舉理由不可為空");
			} else {
				qrrepocontent = req.getParameter("qrrepocontent");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher reqTruck = req.getRequestDispatcher("/front-end/Discuss/ResReportForm.jsp");
				reqTruck.forward(req, resp);
				return;
			}

			// 2.送入資料庫
			QrrepoService qrrepoSvc = new QrrepoService();
			qrrepoSvc.addQrrepo(null, res_id, qrrepocontent);

			// 3.回討論串
			QueVO queVO = new QueVO();
			ResService resSvc = new ResService();
			QhashtagService qhashtagSvc = new QhashtagService();
			queVO = new QueService().findQueByPK(que_id);
			List<ResVO> reslist = resSvc.findResByBelongto(que_id);
			List<QhashtagVO> qhashtaglist = qhashtagSvc.findQhashtagByQue(que_id);
			req.setAttribute("queVO", queVO);
			req.setAttribute("reslist", reslist);
			req.setAttribute("qhashtaglist", qhashtaglist);
			RequestDispatcher reqTruck = req.getRequestDispatcher("/front-end/Discuss/DiscussThread.jsp");
			reqTruck.forward(req, resp);
		}
		/********************* 編輯回應--前台 **********************************/
		if ("editres".equals(action)) {// 從討論串來
			// 接收參數
			String res_id = req.getParameter("res_id");

			// DB查詢
			ResService resSvc = new ResService();
			ResVO resVO = resSvc.findResByPK(res_id);
			req.setAttribute("resVO", resVO);

			// gotoQREdit
			req.getRequestDispatcher("/front-end/Discuss/QREdit.jsp").forward(req, resp);
		}

		if ("editresconfirm".equals(action)) {// 從QREdit來的修改請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數 不能修改
			String res_id = req.getParameter("res_id");

			// 接收請求參數 可修改的(quefile+filetype newquefile+newquefiletype
			// String[value=hashtag_id]hashtagArray ) Pain train
			String filetype = null;
			byte[] resfile = null;

			/********* 檔案處理 *************/
			/* 新空舊有 */
			if (req.getParameter("resfile").trim().length() != 0 && req.getPart("newresfile").getSize() == 0) {
				ResService resSvc = new ResService();
				resfile = resSvc.findResByPK(res_id).getResfile();
				filetype = req.getParameter("filetype");
			}

			/* 新有 */
			if (req.getPart("newresfile").getSize() != 0) {
				Part part = req.getPart("newresfile");
				filetype = part.getContentType();// 拿新檔類型
				InputStream is = part.getInputStream();
				resfile = new byte[is.available()];
				is.read(resfile);
				is.close();
			}

			/* 新空舊空 */
			if (req.getParameter("resfile").trim().length() == 0 && req.getPart("newresfile").getSize() == 0) {
				resfile = new byte[0];
			}

			if (resfile != null && resfile.length > 1024 * 1024 * 5) {
				errorMsgs.put("resfile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不可超過5MB");
			}

			if (filetype != null && !filetype.equals("audio/webm") && !filetype.equals("video/webm")
					&& !filetype.equals("image/png") && !filetype.equals("image/jpeg") && !filetype.equals("image/gif")
					&& !filetype.equals("image/bmp")) {
				errorMsgs.put("resfile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不可超過5MB");
			}

			if (!errorMsgs.isEmpty()) {
				ResVO resVO = new ResService().findResByPK(res_id);
				req.setAttribute("resVO", resVO);
				RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/Discuss/QREdit.jsp");
				requestTruck.forward(req, resp);
				return;
			}

			// 開始修改資料 TODO
			ResService resSvc = new ResService();
			resSvc.editRes(res_id, resfile, filetype);

			// 修改完成 轉交forwardto.jsp
			resp.sendRedirect(req.getContextPath()+"/front-end/Discuss/forwardto.jsp?que_id="
					+ resSvc.findResByPK(res_id).getBelongto() + "&action=tothread");
			return;

		}

		/******* 接收點讚--前台 ************************************************/
		if ("pressThumbres".equals(action)) {
			PrintWriter out = resp.getWriter();
			// 1.接收參數 處理錯誤
			String res_id = req.getParameter("res_id");
			String member_id = req.getParameter("member_id");

			// 2.DB查詢
			ThumbrecordresService thumbrecordresSvc = new ThumbrecordresService();
			List<ThumbrecordresVO> thumbrecordreslist = thumbrecordresSvc.findByMemberId(member_id);
			if (thumbrecordreslist.size() == 0) {// 該member第一次點讚
				thumbrecordresSvc.addThumbrecordres(member_id, res_id);
				ResService resSvc = new ResService();
				ResVO resVO = resSvc.findResByPK(res_id);			
				resSvc.updateRes(res_id, (resVO.getResthumb() + 1), resVO.getBestres());// 該回應讚+1
				return;
			}
			for (int i = 0; i < thumbrecordreslist.size(); i++) {
				if (thumbrecordreslist.get(i).getRes_id().equals(res_id)) {//點過該條回應讚
					thumbrecordresSvc.deleteThumbrecordres(member_id, res_id);
					ResService resSvc = new ResService();
					ResVO resVO = resSvc.findResByPK(res_id);			
					resSvc.updateRes(res_id, (resVO.getResthumb() -1), resVO.getBestres());// 該回應讚-1
					out.write("false");
					return;
				}

			}
			thumbrecordresSvc.addThumbrecordres(member_id, res_id);
			ResService resSvc = new ResService();
			ResVO resVO = resSvc.findResByPK(res_id);			
			resSvc.updateRes(res_id, (resVO.getResthumb() + 1), resVO.getBestres());// 該回應讚+1
		}
	}

}
