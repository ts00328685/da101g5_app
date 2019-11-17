package com.res.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ResVO implements Serializable {
	
	private String res_id;
	private String member_id;
	private String rescontent;
	private Timestamp restime;
	private Integer resthumb;
	private byte[] resfile;
	private Integer resstatus;
	private Integer bestres;
	private String belongto;
	private String filetype;
	
	public ResVO() {
		super();
	}

	public ResVO(String res_id, String member_id, String rescontent, Timestamp restime, Integer resthumb,
			byte[] resfile, Integer resstatus, Integer bestres, String belongto,String filetype) {
		super();
		this.res_id = res_id;
		this.member_id = member_id;
		this.rescontent = rescontent;
		this.restime = restime;
		this.resthumb = resthumb;
		this.resfile = resfile;
		this.resstatus = resstatus;
		this.bestres = bestres;
		this.belongto = belongto;
		this.filetype = filetype;
	}

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getRescontent() {
		return rescontent;
	}

	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}

	public Timestamp getRestime() {
		return restime;
	}

	public void setRestime(Timestamp restime) {
		this.restime = restime;
	}

	public Integer getResthumb() {
		return resthumb;
	}

	public void setResthumb(Integer resthumb) {
		this.resthumb = resthumb;
	}

	public byte[] getResfile() {
		return resfile;
	}

	public void setResfile(byte[] resfile) {
		this.resfile = resfile;
	}

	public Integer getResstatus() {
		return resstatus;
	}

	public void setResstatus(Integer resstatus) {
		this.resstatus = resstatus;
	}

	public Integer getBestres() {
		return bestres;
	}

	public void setBestres(Integer bestres) {
		this.bestres = bestres;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
	
	
}
