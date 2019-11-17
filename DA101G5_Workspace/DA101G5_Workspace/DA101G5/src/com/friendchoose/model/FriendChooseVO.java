package com.friendchoose.model;

import java.io.Serializable;

public class FriendChooseVO implements Serializable{	
	private String condition_id;
	private String condition_language_id;
	private String condition_member_id;
	private Integer condition_sex;
	
	public FriendChooseVO() {}

	public String getCondition_id() {
		return condition_id;
	}

	public void setCondition_id(String condition_id) {
		this.condition_id = condition_id;
	}

	public String getCondition_language_id() {
		return condition_language_id;
	}

	public void setCondition_language_id(String condition_language_id) {
		this.condition_language_id = condition_language_id;
	}

	public String getCondition_member_id() {
		return condition_member_id;
	}

	public void setCondition_member_id(String condition_member_id) {
		this.condition_member_id = condition_member_id;
	}

	public Integer getCondition_sex() {
		return condition_sex;
	}

	public void setCondition_sex(Integer condition_sex) {
		this.condition_sex = condition_sex;
	}
	
	
	
	
}
