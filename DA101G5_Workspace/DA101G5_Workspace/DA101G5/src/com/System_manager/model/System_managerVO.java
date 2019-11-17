package com.System_manager.model;

public class System_managerVO implements java.io.Serializable {
	private String sm_id;
	private String sm_name;
	private String sm_ac;
	private String sm_pass;
	public System_managerVO() {
		super();
		
	}
	public System_managerVO(String sm_id, String sm_name, String sm_ac, String sm_pass) {
		super();
		this.sm_id = sm_id;
		this.sm_name = sm_name;
		this.sm_ac = sm_ac;
		this.sm_pass = sm_pass;
	}
	public String getSm_id() {
		return sm_id;
	}
	public void setSm_id(String sm_id) {
		this.sm_id = sm_id;
	}
	public String getSm_name() {
		return sm_name;
	}
	public void setSm_name(String sm_name) {
		this.sm_name = sm_name;
	}
	public String getSm_ac() {
		return sm_ac;
	}
	public void setSm_ac(String sm_ac) {
		this.sm_ac = sm_ac;
	}
	public String getSm_pass() {
		return sm_pass;
	}
	public void setSm_pass(String sm_pass) {
		this.sm_pass = sm_pass;
	}
	 
	
}


