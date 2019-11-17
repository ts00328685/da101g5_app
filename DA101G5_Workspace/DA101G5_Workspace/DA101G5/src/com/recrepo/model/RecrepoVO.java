package com.recrepo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecrepoVO implements Serializable {
	
	private String recrepo_id;
	private String recruit_id;
	private Timestamp repodate;
	private String repocontent;
	private Integer repostatus;
	
	public RecrepoVO() {
		super();
	}

	public RecrepoVO(String recrepo_id, String recruit_id, Timestamp repodate, String repocontent, Integer repostatus) {
		super();
		this.recrepo_id = recrepo_id;
		this.recruit_id = recruit_id;
		this.repodate = repodate;
		this.repocontent = repocontent;
		this.repostatus = repostatus;
	}

	public String getRecrepo_id() {
		return recrepo_id;
	}

	public void setRecrepo_id(String recrepo_id) {
		this.recrepo_id = recrepo_id;
	}

	public String getRecruit_id() {
		return recruit_id;
	}

	public void setRecruit_id(String recruit_id) {
		this.recruit_id = recruit_id;
	}

	public Timestamp getRepodate() {
		return repodate;
	}

	public void setRepodate(Timestamp repodate) {
		this.repodate = repodate;
	}

	public String getRepocontent() {
		return repocontent;
	}

	public void setRepocontent(String repocontent) {
		this.repocontent = repocontent;
	}

	public Integer getRepostatus() {
		return repostatus;
	}

	public void setRepostatus(Integer repostatus) {
		this.repostatus = repostatus;
	}
	
	
	 
	
}
