package com.favorque.model;

import java.util.List;

public interface FavorqueDAO_interface {
	public void insert(FavorqueVO favorqueVO);
	public void delete(String member_id, String que_id);
	public List<FavorqueVO> getAll();
	public List<FavorqueVO> findByMemberId(String member_id);
	public boolean search(String member_id, String que_id);
}
