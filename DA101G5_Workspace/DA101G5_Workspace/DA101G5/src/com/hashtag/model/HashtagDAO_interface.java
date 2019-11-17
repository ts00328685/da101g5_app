package com.hashtag.model;

import java.util.List;

public interface HashtagDAO_interface {
	public void insert(HashtagVO hashtagVO);
	public void delete(String hashtag_id);
	public HashtagVO findByPK(String hashtag_id);
	public List<HashtagVO> getAll();
}
