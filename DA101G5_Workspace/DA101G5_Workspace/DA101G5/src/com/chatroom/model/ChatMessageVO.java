package com.chatroom.model;

public class ChatMessageVO {
	private String Mtype;
	private String sender;
	private String receiver;
	private String message;

	public ChatMessageVO(String Mtype, String sender, String receiver, String message) {
		this.Mtype = Mtype;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMtype() {
		return Mtype;
	}

	public void setMtype(String type) {
		this.Mtype = Mtype;
	}
}
