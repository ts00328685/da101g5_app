package com.thumbrecordres.model;

import java.util.List;

import com.thumbrecordres.model.ThumbrecordresDAO;
import com.thumbrecordres.model.ThumbrecordresVO;

public class ThumbrecordresService {
	private ThumbrecordresDAO dao;
	
	public ThumbrecordresService() {
		dao = new ThumbrecordresDAO();
	}
	
	public void addThumbrecordres(String member_id, String res_id) {
		dao.insert(member_id, res_id);
	}
	
	public List<ThumbrecordresVO> findByMemberId(String member_id){
		List<ThumbrecordresVO> thumbrecordreslist = dao.findByMemberId(member_id);
		return thumbrecordreslist;
	}
	
	public boolean searchThumbrecordres(String member_id, String res_id) {
		boolean tf ;
		tf = dao.search(member_id, res_id);
		return tf;
	}
	
	public void deleteThumbrecordres(String member_id, String res_id) {
		dao.delete(member_id, res_id);
	}
}
