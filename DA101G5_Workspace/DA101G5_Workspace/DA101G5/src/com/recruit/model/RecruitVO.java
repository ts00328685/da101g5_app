package com.recruit.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecruitVO implements Serializable {
	private String recruit_id;
	private String member_id;
	private Timestamp startdate;
	private Timestamp enddate;
	private String rcontent;
	private String rtitle;
	private Integer recstatus;
	
	public RecruitVO() {
		super();
	}

	public RecruitVO(String recruit_id, String member_id, Timestamp startdate, Timestamp enddate, String rcontent,
			String rtitle, Integer recstatus) {
		super();
		this.recruit_id = recruit_id;
		this.member_id = member_id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.rcontent = rcontent;
		this.rtitle = rtitle;
		this.recstatus = recstatus;
	}

	public String getRecruit_id() {
		return recruit_id;
	}

	public void setRecruit_id(String recruit_id) {
		this.recruit_id = recruit_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	public Integer getRecstatus() {
		return recstatus;
	}

	public void setRecstatus(Integer recstatus) {
		this.recstatus = recstatus;
	}
	
	
}
