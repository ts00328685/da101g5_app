package com.thumbrecordres.model;

public class ThumbrecordresVO implements java.io.Serializable {
	private String member_id;
	private String res_id;
	
	public ThumbrecordresVO() {
		super();
	}

	public ThumbrecordresVO(String member_id, String res_id) {
		super();
		this.member_id = member_id;
		this.res_id = res_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	
}
