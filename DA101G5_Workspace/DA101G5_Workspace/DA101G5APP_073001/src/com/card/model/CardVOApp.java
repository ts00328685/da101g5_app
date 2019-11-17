package com.card.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CardVOApp extends CardVO implements java.io.Serializable {
	
//	private String card_id;
//	private String teacher_id;
//	private String word;
//	private String phonetic_symbol;
//	private byte[] voice;
//	private String native_explain;
//	private String example;
	
	private String member_card_group_id;
	private Timestamp review_time;
	private Integer wrong_number;
	private Integer right_number;
	private Integer daily_answer;
	private Integer choice_card_group;
	
	public String getMember_card_group_id() {
		return member_card_group_id;
	}
	public void setMember_card_group_id(String member_card_group_id) {
		this.member_card_group_id = member_card_group_id;
	}
	public Timestamp getReview_time() {
		return review_time;
	}
	public void setReview_time(Timestamp review_time) {
		this.review_time = review_time;
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
