package com.thumbrecordque.model;

import java.util.List;

public class ThumbrecordqueService {
	private ThumbrecordqueDAO dao;
	
	public ThumbrecordqueService() {
		dao = new ThumbrecordqueDAO();
	}
	
	public void addThumbrecordque(String member_id, String que_id) {
		dao.insert(member_id, que_id);
	}
	
	public List<ThumbrecordqueVO> findByMemberId(String member_id){
		List<ThumbrecordqueVO> thumbrecordquelist = dao.findByMemberId(member_id);
		return thumbrecordquelist;
	}
	
	public boolean searchThumbrecordque(String member_id, String que_id) {
		boolean tf ;
		tf = dao.search(member_id, que_id);
		return tf;
	}
	
	public void deleteThumbrecordque(String member_id, String que_id) {
		dao.delete(member_id, que_id);
	}
}
