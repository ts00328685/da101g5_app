package com.thumbrecordres.model;

import java.util.List;

import com.thumbrecordres.model.ThumbrecordresVO;

public interface ThumbrecordresDAO_interface {
	
	public void insert(String member_id,String res_id);
	
	public List<ThumbrecordresVO> findByMemberId(String member_id);
	
	public boolean search(String member_id,String res_id);
	
	public void delete(String member_id,String res_id); 
}
