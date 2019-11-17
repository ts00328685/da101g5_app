package com.teacher_card_table.model;

import java.sql.Date;

public class Teacher_card_tableVO implements java.io.Serializable {
	
	
	private String card_id;
	private String teacher_card_group_id;
	
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getTeacher_card_group_id() {
		return teacher_card_group_id;
	}
	public void setTeacher_card_group_id(String teacher_card_group_id) {
		this.teacher_card_group_id = teacher_card_group_id;
	}

	
	
}
