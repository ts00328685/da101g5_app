package com.Language.model;

public class LanguageVO implements java.io.Serializable{
	private String language_id;
	private String language;
	
	public LanguageVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
