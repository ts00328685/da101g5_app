package com.com_report.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Com_reportVO implements java.io.Serializable {

	private String com_report_id;
	private String com_id;
	private String r_des;
	private Timestamp trandate;
	private Integer r_status;
	
	public String getCom_report_id() {
		return com_report_id;
	}
	public void setCom_report_id(String com_report_id) {
		this.com_report_id = com_report_id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getR_des() {
		return r_des;
	}
	public void setR_des(String r_des) {
		this.r_des = r_des;
	}
	public Timestamp getTrandate() {
		return trandate;
	}
	public void setTrandate(Timestamp trandate) {
		this.trandate = trandate;
	}
	public Integer getR_status() {
		return r_status;
	}
	public void setR_status(Integer r_status) {
		this.r_status = r_status;
	}
	
	
	


}
