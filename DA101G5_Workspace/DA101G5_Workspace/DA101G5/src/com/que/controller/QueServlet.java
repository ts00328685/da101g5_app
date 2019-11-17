package com.que.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.favorque.model.FavorqueService;
import com.favorque.model.FavorqueVO;
import com.hashtag.model.HashtagService;
import com.hashtag.model.HashtagVO;
import com.member.model.MemberVO;
import com.qhashtag.model.QhashtagService;
import com.qhashtag.model.QhashtagVO;
import com.qrreport.model.QrrepoService;
import com.que.model.QueService;
import com.que.model.QueVO;
import com.res.model.ResService;
import com.res.model.ResVO;
import com.thumbrecordque.model.ThumbrecordqueService;
import com.thumbrecordque.model.ThumbrecordqueVO;

@MultipartConfig()
public class QueServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/******************
		 * 新增發問--前台
		 ****************************************************/
		if ("addque".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 1.接收參數 錯誤處理
			String member_id = null;
			String quetitle = null;
			String quecontent = null;
			byte[] quefile = null;
			String filetype = null;
			String[] qhashtag = null;
			HttpSession session = req.getSession();

			if (req.getParameter("member_id").trim().length() == 0) {// 沒登入送回討論區首頁
				resp.sendRedirect(req.getContextPath()+"/front-end/Discuss/MemberLoginWarning.jsp");
				return;
			} else {
				member_id = req.getParameter("member_id");
			}

			if (req.getParameter("quetitle").trim().length() == 0) {// 標題處理
				errorMsgs.put("quetitle", "標題不可為空");
			} else {
				quetitle = req.getParameter("quetitle");
			}

			if (req.getParameter("quecontent").trim().length() == 0) {// 內文處理
				errorMsgs.put("quecontent", "內文不可為空");
			} else {
				quecontent = req.getParameter("quecontent");
			}
			
			if (req.getParameter("quecontent").trim().length() == 0 && req.getParameter("quetitle").trim().length() == 0) {// 內文處理
				errorMsgs.put("qct", "標題以及內文不可為空");
			}

			/**** 檔案處理(含檔名) ******/
			if (req.getPart("quefile").getSize() == 0) {
				quefile = new byte[0];
			} else {
				Part part = req.getPart("quefile");
				filetype = part.getContentType();
				Long filesize = part.getSize();
				// 檔案大小處理
				if (filesize > 5 * 1024 * 1024) {
					errorMsgs.put("quefile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或者檔案大小不可超過5MB");
				}
				// 檔案類型處理
				if (filetype.indexOf("webm") <= -1 && !filetype.equals("image/png") && !filetype.equals("image/jpeg")
						&& !filetype.equals("image/gif") && !filetype.equals("image/bmp")) {
					errorMsgs.put("quefile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或者檔案大小不可超過5MB");
				}

				InputStream is = part.getInputStream();
				quefile = new byte[is.available()];
				is.read(quefile);
				is.close();
			}

			if (req.getParameterValues("qhashtag") == null) {// hashtag處理
				qhashtag = new String[0];
			} else {
				qhashtag = req.getParameterValues("qhashtag");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher reqTruck = req.getRequestDispatcher("/front-end/Discuss/DiscussIndex.jsp");
				reqTruck.forward(req, resp);
				return;
			}

			// 2.存入DB
			QueService queSvc = new QueService();
			queSvc.addQue(member_id, quetitle, quecontent, quefile, qhashtag, filetype);

			// 3.重導回討論區首頁
			resp.sendRedirect(req.getContextPath() + "/front-end/Discuss/DiscussIndex.jsp");
		}
		/************** 進入討論串--前台 ***********************/
		if ("tothread".equals(action)) {
			// 接收參數
			String que_id = req.getParameter("que_id");
			HttpSession session = req.getSession();
			// DB查詢 問題+問題的hashtag 瀏覽人數+1
			QueService queSvc = new QueService();
			ResService resSvc = new ResService();
			QhashtagService qhashtagSvc = new QhashtagService();
			QueVO queVO = queSvc.findQueByPK(que_id);
			queSvc.updateQue(queVO.getQuethumb(), queVO.getWatchcount() + 1, queVO.getRescount(), que_id);
			List<ResVO> reslist = resSvc.findResByBelongto(que_id);
			List<QhashtagVO> qhashtaglist = qhashtagSvc.findQhashtagByQue(que_id);
			req.setAttribute("queVO", queVO);
			req.setAttribute("reslist", reslist);
			req.setAttribute("qhashtaglist", qhashtaglist);

			// DB查詢 問題所屬回應

			// 導向討論串
			RequestDispatcher reqTruck = req.getRequestDispatcher("/front-end/Discuss/DiscussThread.jsp");
			reqTruck.forward(req, resp);
		}

		/******* 接收最佳解答--前台 ************************************************/
		if ("selectBest".equals(action)) {
			// 1.接收參數 處理錯誤
			PrintWriter out = resp.getWriter();

			String res_id = req.getParameter("res_id");
			String belongto = req.getParameter("belongto");
			ResService resSvc = new ResService();
			List<ResVO> reslist = resSvc.findResByBelongto(belongto);

			for (int i = 0; i < reslist.size(); i++) {
				if (reslist.get(i).getBestres() == 1) {
					out.write("false");
					return;
				}
			}

			// 2.DB修改
			ResVO resVO = resSvc.findResByPK(res_id);
			resSvc.updateRes(res_id, resVO.getResthumb(), 1);

		}

		/******* 接收點讚--前台 ************************************************/
		if ("pressThumbque".equals(action)) {
			PrintWriter out = resp.getWriter();
			// 1.接收參數 處理錯誤
			String que_id = req.getParameter("que_id");
			String member_id = req.getParameter("member_id");

			// 2.DB查詢
			ThumbrecordqueService thumbrecordqueSvc = new ThumbrecordqueService();
			List<ThumbrecordqueVO> thumbrecordquelist = thumbrecordqueSvc.findByMemberId(member_id);
			if (thumbrecordquelist.size() == 0) {// 該member第一次點讚
				thumbrecordqueSvc.addThumbrecordque(member_id, que_id);
				QueService queSvc = new QueService();
				QueVO queVO = queSvc.findQueByPK(que_id);
				queSvc.updateQue((queVO.getQuethumb() + 1), queVO.getWatchcount(), queVO.getRescount(), que_id);// 該問題讚+1
				return;
			}
			for (int i = 0; i < thumbrecordquelist.size(); i++) {
				if (thumbrecordquelist.get(i).getQue_id().equals(que_id)) {//有點過這條問題讚
					thumbrecordqueSvc.deleteThumbrecordque(member_id, que_id);
					QueService queSvc = new QueService();
					QueVO queVO = queSvc.findQueByPK(que_id);
					queSvc.updateQue((queVO.getQuethumb() - 1), queVO.getWatchcount(), queVO.getRescount(), que_id);// 該問題讚-1
					out.write("false");
					return;
				}

			}
			thumbrecordqueSvc.addThumbrecordque(member_id, que_id);
			QueService queSvc = new QueService();
			QueVO queVO = queSvc.findQueByPK(que_id);
			queSvc.updateQue((queVO.getQuethumb() + 1), queVO.getWatchcount(), queVO.getRescount(), que_id);// 該問題讚+1

		}

		/******* 接收檢舉單--前台 ************************************************/
		if ("reportQue".equals(action)) {
			// 1.接收參數 處理錯誤
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String que_id = req.getParameter("que_id");
			String qrrepocontent = null;

			if (req.getParameter("qrrepocontent").trim().length() == 0) {
				errorMsgs.put("qrrepocontent", "檢舉理由不可為空");
			} else {
				qrrepocontent = req.getParameter("qrrepocontent");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher reqTruck = req.getRequestDispatcher("/front-end/Discuss/QueReportForm.jsp");
				reqTruck.forward(req, resp);
				return;
			}

			// 2.送入資料庫
			QrrepoService qrrepoSvc = new QrrepoService();
			qrrepoSvc.addQrrepo(que_id, null, qrrepocontent);

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

		/********************* 編輯問題--前台 **********************************/
		if ("editque".equals(action)) {// 從討論串來
			// 接收參數
			String que_id = req.getParameter("que_id");

			// DB查詢
			QueService queSvc = new QueService();
			QueVO queVO = queSvc.findQueByPK(que_id);
			HashtagService hashtagSvc = new HashtagService();
			QhashtagService qhashtagSvc = new QhashtagService();
			List<HashtagVO> hashtaglist = hashtagSvc.getAllHashtag();// 全hashtag
			List<QhashtagVO> qhashtaglist = qhashtagSvc.findQhashtagByQue(que_id);// que的hashtag
			req.setAttribute("queVO", queVO);
			req.setAttribute("hashtaglist", hashtaglist);
			req.setAttribute("qhashtaglist", qhashtaglist);

			// gotoQREdit
			req.getRequestDispatcher("/front-end/Discuss/QREdit.jsp").forward(req, resp);
		}

		if ("editqueconfirm".equals(action)) {// 從QREdit來的修改請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數 不能修改
			String que_id = req.getParameter("que_id");

			// 接收請求參數 可修改的(quefile+filetype newquefile+newquefiletype
			// String[value=hashtag_id]hashtagArray ) Pain train
			String filetype = null;
			byte[] quefile = null;

			/********* 檔案處理 *************/
			/* 新空舊有 */
			if (req.getParameter("quefile").trim().length() != 0 && req.getPart("newquefile").getSize() == 0) {
				QueService queSvc = new QueService();
				quefile = queSvc.findQueByPK(que_id).getQuefile();
				filetype = req.getParameter("filetype");
			}

			/* 新有 */
			if (req.getPart("newquefile").getSize() != 0) {
				Part part = req.getPart("newquefile");
				filetype = part.getContentType();// 拿新檔類型
				InputStream is = part.getInputStream();
				quefile = new byte[is.available()];
				is.read(quefile);
				is.close();
			}

			/* 新空舊空 */
			if (req.getParameter("quefile").trim().length() == 0 && req.getPart("newquefile").getSize() == 0) {
				quefile = new byte[0];
			}

			if (quefile != null && quefile.length > 1024 * 1024 * 5) {
				errorMsgs.put("quefile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不可超過5MB");
			}

			if (filetype != null && !filetype.equals("audio/webm") && !filetype.equals("video/webm")
					&& !filetype.equals("image/png") && !filetype.equals("image/jpeg") && !filetype.equals("image/gif")
					&& !filetype.equals("image/bmp")) {
				errorMsgs.put("quefile", "不合法的檔案類型  僅允許jpeg,jpg,gif,png,bmp,webm類型的檔案 或檔案大小不可超過5MB");
			}

			/********** hashtag處理 *****************************/
			String[] hashtagArray = new String[0];
			if (req.getParameterValues("hashtagArray") != null) {
				hashtagArray = req.getParameterValues("hashtagArray");
			}

			if (!errorMsgs.isEmpty()) {
				QueVO queVO = new QueService().findQueByPK(que_id);
				req.setAttribute("queVO", queVO);
				RequestDispatcher requestTruck = req.getRequestDispatcher("/front-end/Discuss/QREdit.jsp");
				requestTruck.forward(req, resp);
				return;
			}

			// 開始修改資料 
			QueService queSvc = new QueService();
			queSvc.editQue(quefile, filetype, que_id, hashtagArray);

			// 修改完成 轉交forwardto.jsp
			resp.sendRedirect(req.getContextPath()+"/front-end/Discuss/forwardto.jsp?que_id=" + que_id + "&action=tothread");
			return;

		}
		
/*********************搜尋關鍵字***********************************************************/
		if("search".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String keyword = null;
			String[] keywords = null;
			//1.接收參數
				keyword = req.getParameter("keyword");
				if(keyword.trim().length()==0) {
					errorMsgs.put("keyword","請勿輸入空白鍵當作關鍵字");
				}else {
					keywords = keyword.trim().split("\\s+");
				}
				
				if(!errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/front-end/Discuss/DiscussIndex.jsp").forward(req,resp);
					return;
				}
			
			//2.資料比對
			QueService queSvc = new QueService();
			ResService resSvc = new ResService();
			List<QueVO> quelist = queSvc.getAllQue();
			List<ResVO> reslist = resSvc.getAllRes();
			
			//que標題+內文查詢 符合條件放入set
			Set<String> queset = new LinkedHashSet<String>();
			for(int i =0 ; i<quelist.size();i++) {
				for(String key:keywords) {
					String contentAppendTitle = quelist.get(i).getQuetitle()+quelist.get(i).getQuecontent();
					if(contentAppendTitle.contains(key)) {
						queset.add(quelist.get(i).getQue_id());
					}
				}
			}

			//res標題+內文查詢  符合條件放入set
			Set<String> resset = new LinkedHashSet<String>();
			for(int i =0 ; i<reslist.size();i++) {
				for(String key:keywords) {
					String str = reslist.get(i).getRescontent();
					if(str.contains(key)) {
						resset.add(reslist.get(i).getBelongto());
					}
				}
			}
			//set合併重複處理 
			queset.addAll(resset);
			
			//queVO的id與set內id比對 相同放VO入新set
			Set<QueVO> quelistResult = new LinkedHashSet<QueVO>();
			to:
			for(int i = 0;i<quelist.size(); i++) {
				for(String que_id:queset) {
					if(quelist.get(i).getQue_id().equals(que_id) && quelist.get(i).getQuestatus()!=0) {//沒被隱藏才加入
						quelistResult.add(quelist.get(i));
						continue to;
					}
				}
			}
			
			//返回set到serachingresult
			req.setAttribute("quelist", quelistResult);
			req.getRequestDispatcher("/front-end/Discuss/SearchingResult.jsp").forward(req,resp);
		}
/*******************標籤搜尋***************************************/
		if("tagsearch".equals(action)) {
			//接收參數
			String hashtag_id = req.getParameter("hashtag_id");
			
			//比對資料
			Set<String> que_idset = new LinkedHashSet<String>();
			QhashtagService qhashtagSvc = new QhashtagService();
			List<QhashtagVO> qhashtaglist = qhashtagSvc.getAllQhashtag();
			for(int i = 0;i<qhashtaglist.size();i++) {
				if(qhashtaglist.get(i).getHashtag_id().equals(hashtag_id)) {
					que_idset.add(qhashtaglist.get(i).getQue_id());
				}
			}
			
			//set內的id 與volist內vo的id做比對 相同的放入結果集
			Set<QueVO> quelistResult = new LinkedHashSet<QueVO>();
			QueService queSvc = new QueService();
			List<QueVO> quelist = queSvc.getAllQue();
			to:
			for(int i =0;i<quelist.size();i++) {
				for(String que_id:que_idset) {
					if(quelist.get(i).getQue_id().equals(que_id) && quelist.get(i).getQuestatus()!=0) {//沒被隱藏的才加入
						quelistResult.add(quelist.get(i));
						continue to;
					}
				}
			}
			
			//送到查詢結果頁
			req.setAttribute("quelist", quelistResult);		
			req.getRequestDispatcher("/front-end/Discuss/SearchingResult.jsp").forward(req,resp);
		}
	}

}
