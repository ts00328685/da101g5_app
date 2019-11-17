package com.qhashtag.model;

import java.util.List;

public interface QhashtagDAO_interface {
	public void insert(QhashtagVO qhashtagVO);
	public List<QhashtagVO> findByQue(String que_id);
	public List<QhashtagVO> findByHashtag(String hashtag_id);
	public List<QhashtagVO> gatAll();
}
