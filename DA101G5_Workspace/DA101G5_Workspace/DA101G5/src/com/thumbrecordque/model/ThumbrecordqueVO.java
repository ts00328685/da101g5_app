package com.thumbrecordque.model;

public class ThumbrecordqueVO implements java.io.Serializable {
	private String member_id;
	private String que_id;
	
	public ThumbrecordqueVO() {
		super();
	}

	public ThumbrecordqueVO(String member_id, String que_id) {
		super();
		this.member_id = member_id;
		this.que_id = que_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getQue_id() {
		return que_id;
	}

	public void setQue_id(String que_id) {
		this.que_id = que_id;
	}
	
	
}
