package com.favorque.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FavorqueVO implements Serializable {
	private String que_id;
	private String member_id;
	private Timestamp addtime;
	
	public FavorqueVO() {
		super();
	}

	public FavorqueVO(String que_id, String member_id, Timestamp addtime) {
		super();
		this.que_id = que_id;
		this.member_id = member_id;
		this.addtime = addtime;
	}

	public String getQue_id() {
		return que_id;
	}

	public void setQue_id(String que_id) {
		this.que_id = que_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	
	
}
