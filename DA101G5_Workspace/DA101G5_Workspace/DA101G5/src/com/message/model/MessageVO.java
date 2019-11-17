package com.message.model;
import java.sql.Date;

public class MessageVO implements java.io.Serializable{
	private String message_id;
	private String member_id;
	private String member_id2;
	private Date memmsg_date;
	private String memmsg_ent;
	private Integer memmsg_state;
	
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_id2() {
		return member_id2;
	}
	public void setMember_id2(String member_id2) {
		this.member_id2 = member_id2;
	}
	public Date getMemmsg_date() {
		return memmsg_date;
	}
	public void setMemmsg_date(Date memmsg_date) {
		this.memmsg_date = memmsg_date;
	}
	public String getMemmsg_ent() {
		return memmsg_ent;
	}
	public void setMemmsg_ent(String memmsg_ent) {
		this.memmsg_ent = memmsg_ent;
	}
	public Integer getMemmsg_state() {
		return memmsg_state;
	}
	public void setMemmsg_state(Integer memmsg_state) {
		this.memmsg_state = memmsg_state;
	}
	
	
}
