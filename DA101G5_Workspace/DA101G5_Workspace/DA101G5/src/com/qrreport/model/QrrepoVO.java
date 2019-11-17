package com.qrreport.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class QrrepoVO implements Serializable {
	private String qrrepo_id;
	private String repoq_id;
	private String repor_id;
	private Timestamp qrrepotime;
	private String qrrepocontent;
	private Integer qrrepostatus;
	
	public QrrepoVO() {
		super();
	}

	public QrrepoVO(String qrrepo_id, String repoq_id, String repor_id, Timestamp qrrepotime, String qrrepocontent,
			Integer qrrepostatus) {
		super();
		this.qrrepo_id = qrrepo_id;
		this.repoq_id = repoq_id;
		this.repor_id = repor_id;
		this.qrrepotime = qrrepotime;
		this.qrrepocontent = qrrepocontent;
		this.qrrepostatus = qrrepostatus;
	}

	public String getQrrepo_id() {
		return qrrepo_id;
	}

	public void setQrrepo_id(String qrrepo_id) {
		this.qrrepo_id = qrrepo_id;
	}

	public String getRepoq_id() {
		return repoq_id;
	}

	public void setRepoq_id(String repoq_id) {
		this.repoq_id = repoq_id;
	}

	public String getRepor_id() {
		return repor_id;
	}

	public void setRepor_id(String repor_id) {
		this.repor_id = repor_id;
	}

	public Timestamp getQrrepotime() {
		return qrrepotime;
	}

	public void setQrrepotime(Timestamp qrrepotime) {
		this.qrrepotime = qrrepotime;
	}

	public String getQrrepocontent() {
		return qrrepocontent;
	}

	public void setQrrepocontent(String qrrepocontent) {
		this.qrrepocontent = qrrepocontent;
	}

	public Integer getQrrepostatus() {
		return qrrepostatus;
	}

	public void setQrrepostatus(Integer qrrepostatus) {
		this.qrrepostatus = qrrepostatus;
	}
	
	
}
