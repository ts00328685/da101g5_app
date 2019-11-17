package com.hashtag.model;

import java.util.ArrayList;
import java.util.List;

public class HashtagService {
	private HashtagDAO  hashtagDAO;
	
	public HashtagService() {
		hashtagDAO = new HashtagDAO();
	}
	
	public void addHashtag(String hashtag) {
		HashtagVO hashtagVO = new HashtagVO();
		hashtagVO.setHashtag(hashtag);
		hashtagDAO.insert(hashtagVO);
	}
	
	public void deleteHashtag(String hashtag_id) {
		hashtagDAO.delete(hashtag_id);
	}
	
	public HashtagVO findHashtagByPK(String hashtag_id) {
		HashtagVO hashtagVO = hashtagDAO.findByPK(hashtag_id);
		return hashtagVO;
	}
	
	public List<HashtagVO> getAllHashtag(){
		List<HashtagVO> hashtaglist = hashtagDAO.getAll();
		return hashtaglist;
	}
}
