package com.card.model;

import java.sql.Date;

public class CardVO implements java.io.Serializable {
	private String card_id;
	private String teacher_id;
	private String word;
	private String phonetic_symbol;
	private byte[] voice;
	private String native_explain;
	private String example;
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPhonetic_symbol() {
		return phonetic_symbol;
	}
	public void setPhonetic_symbol(String phonetic_symbol) {
		this.phonetic_symbol = phonetic_symbol;
	}
	public byte[] getVoice() {
		return voice;
	}
	public void setVoice(byte[] voice) {
		this.voice = voice;
	}
	public String getNative_explain() {
		return native_explain;
	}
	public void setNative_explain(String native_explain) {
		this.native_explain = native_explain;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}

	
}
