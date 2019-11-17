package com.thumbrecordque.model;

import java.util.List;

public interface ThumbrecordqueDAO_interface {
	
	public void insert(String member_id,String que_id);
	
	public List<ThumbrecordqueVO> findByMemberId(String member_id);
	
	public boolean search(String member_id,String que_id);
	
	public void delete(String member_id,String que_id);
}
