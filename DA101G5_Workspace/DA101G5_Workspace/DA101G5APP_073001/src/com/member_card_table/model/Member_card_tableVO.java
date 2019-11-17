package com.member_card_table.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Member_card_tableVO implements java.io.Serializable {
	
	
	private String card_id;
	private String member_card_group_id;
	private Timestamp review_time;
	private Integer wrong_number;
	private Integer right_number;
	private Integer daily_answer;
	private Integer choice_card_group;
	
	
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getMember_card_group_id() {
		return member_card_group_id;
	}
	public void setMember_card_group_id(String member_card_group_id) {
		this.member_card_group_id = member_card_group_id;
	}
	public Timestamp getReview_time() {
		return review_time;
	}
	public void setReview_time(Timestamp timestamp) {
		this.review_time = timestamp;
	}
	public Integer getWrong_number() {
		return wrong_number;
	}
	public void setWrong_number(Integer wrong_number) {
		this.wrong_number = wrong_number;
	}
	public Integer getRight_number() {
		return right_number;
	}
	public void setRight_number(Integer right_number) {
		this.right_number = right_number;
	}
	public Integer getDaily_answer() {
		return daily_answer;
	}
	public void setDaily_answer(Integer daily_answer) {
		this.daily_answer = daily_answer;
	}
	public Integer getChoice_card_group() {
		return choice_card_group;
	}
	public void setChoice_card_group(Integer choice_card_group) {
		this.choice_card_group = choice_card_group;
	}
	

	
	
	
}
