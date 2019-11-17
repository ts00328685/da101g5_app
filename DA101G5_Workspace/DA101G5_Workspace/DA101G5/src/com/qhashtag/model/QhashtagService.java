package com.qhashtag.model;

import java.util.List;

public class QhashtagService {
	private QhashtagDAO qhashtagDAO ;
	
	public QhashtagService() {
		qhashtagDAO = new QhashtagDAO();
	}
	
	public void addQhashtag(String hashtag_id, String que_id) {
		QhashtagVO qhashtagVO = new QhashtagVO();
		qhashtagVO.setHashtag_id(hashtag_id);
		qhashtagVO.setQue_id(que_id);
		qhashtagDAO.insert(qhashtagVO);
	}
	
	public List<QhashtagVO> findQhashtagByQue(String que_id){
		List<QhashtagVO> qhashtaglist = qhashtagDAO.findByQue(que_id);
		return qhashtaglist;
	}
	
	public List<QhashtagVO> findQhashtagByHashtag(String hashtag_id){
		List<QhashtagVO> qhashtaglist = qhashtagDAO.findByHashtag(hashtag_id);
		return qhashtaglist;
	}
	
	public List<QhashtagVO> getAllQhashtag(){
		List<QhashtagVO> qhashtaglist = qhashtagDAO.gatAll();
		return qhashtaglist;
	}
	
}
