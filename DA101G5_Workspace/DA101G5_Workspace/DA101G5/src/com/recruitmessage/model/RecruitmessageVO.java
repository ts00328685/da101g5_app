package com.recruitmessage.model;

import java.sql.Timestamp;

public class RecruitmessageVO implements java.io.Serializable {
	private Integer message_no;
	private String sender_id;
	private String reciver_id;
	private String message;
	private Timestamp sendtime;
	
	public RecruitmessageVO() {
		super();
	}

	public Integer getMessage_no() {
		return message_no;
	}

	public void setMessage_no(Integer message_no) {
		this.message_no = message_no;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getReciver_id() {
		return reciver_id;
	}

	public void setReciver_id(String reciver_id) {
		this.reciver_id = reciver_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSendtime() {
		return sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}
	
	
	
	
}
