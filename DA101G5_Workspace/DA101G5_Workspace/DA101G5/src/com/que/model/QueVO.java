package com.que.model;

import java.sql.Timestamp;

public class QueVO implements java.io.Serializable{
	private String que_id;
	private String member_id;
	private Timestamp quetime;
	private Integer quethumb;
	private String quetitle;
	private Integer watchcount;
	private Integer rescount;
	private String quecontent;
	private byte[] quefile;
	private Integer questatus;
	private String filetype;
	
	public QueVO() {
		super();
	}

	public QueVO(String que_id, String member_id, Timestamp quetime, Integer quethumb, String quetitle,
			Integer watchcount, Integer rescount, String quecontent, byte[] quefile, Integer questatus,String filetype) {
		super();
		this.que_id = que_id;
		this.member_id = member_id;
		this.quetime = quetime;
		this.quethumb = quethumb;
		this.quetitle = quetitle;
		this.watchcount = watchcount;
		this.rescount = rescount;
		this.quecontent = quecontent;
		this.quefile = quefile;
		this.questatus = questatus;
		this.filetype = filetype;
	}

	public String getQue_id() {
		return que_id;
	}

	public void setQue_id(String que_id) {
		this.que_id = que_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getQuetime() {
		return quetime;
	}

	public void setQuetime(Timestamp quetime) {
		this.quetime = quetime;
	}

	public Integer getQuethumb() {
		return quethumb;
	}

	public void setQuethumb(Integer quethumb) {
		this.quethumb = quethumb;
	}

	public String getQuetitle() {
		return quetitle;
	}

	public void setQuetitle(String quetitle) {
		this.quetitle = quetitle;
	}

	public Integer getWatchcount() {
		return watchcount;
	}

	public void setWatchcount(Integer watchcount) {
		this.watchcount = watchcount;
	}

	public Integer getRescount() {
		return rescount;
	}

	public void setRescount(Integer rescount) {
		this.rescount = rescount;
	}

	public String getQuecontent() {
		return quecontent;
	}

	public void setQuecontent(String quecontent) {
		this.quecontent = quecontent;
	}

	public byte[] getQuefile() {
		return quefile;
	}

	public void setQuefile(byte[] quefile) {
		this.quefile = quefile;
	}

	public Integer getQuestatus() {
		return questatus;
	}

	public void setQuestatus(Integer questatus) {
		this.questatus = questatus;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
	
}
