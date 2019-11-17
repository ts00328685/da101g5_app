package com.teacher_card_group.model;

import java.sql.Date;

public class Teacher_card_groupVO implements java.io.Serializable {
	
	private String teacher_card_group_id;
	private String teacher_id;
	private String teacher_card_group_name;
	public String getTeacher_card_group_id() {
		return teacher_card_group_id;
	}
	public void setTeacher_card_group_id(String teacher_card_group_id) {
		this.teacher_card_group_id = teacher_card_group_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_card_group_name() {
		return teacher_card_group_name;
	}
	public void setTeacher_card_group_name(String teacher_card_group_name) {
		this.teacher_card_group_name = teacher_card_group_name;
	}

	
	
}
