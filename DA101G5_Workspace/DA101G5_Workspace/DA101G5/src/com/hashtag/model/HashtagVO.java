package com.hashtag.model;

import java.io.Serializable;

public class HashtagVO implements Serializable {
	private String hashtag_id;
	private String hashtag;
	
	public HashtagVO() {
		super();
	}

	public HashtagVO(String hashtag_id, String hashtag) {
		super();
		this.hashtag_id = hashtag_id;
		this.hashtag = hashtag;
	}

	public String getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(String hashtsg_id) {
		this.hashtag_id = hashtsg_id;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	

}
