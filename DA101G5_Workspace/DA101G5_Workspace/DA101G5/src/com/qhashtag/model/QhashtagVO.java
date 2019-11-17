package com.qhashtag.model;

import java.io.Serializable;

public class QhashtagVO implements Serializable {
	private String hashtag_id;
	private String que_id;
	
	public QhashtagVO() {
		super();
	}

	public QhashtagVO(String hashtag_id, String que_id) {
		super();
		this.hashtag_id = hashtag_id;
		this.que_id = que_id;
	}

	public String getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(String hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public String getQue_id() {
		return que_id;
	}

	public void setQue_id(String que_id) {
		this.que_id = que_id;
	}
	
	
}
