package com.Teacher_ad.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Teacher_adVO implements java.io.Serializable{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ad_state == null) ? 0 : ad_state.hashCode());
		result = prime * result + ((teacher_id == null) ? 0 : teacher_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher_adVO other = (Teacher_adVO) obj;
		if (ad_state == null) {
			if (other.ad_state != null)
				return false;
		} else if (!ad_state.equals(other.ad_state))
			return false;
		if (teacher_id == null) {
			if (other.teacher_id != null)
				return false;
		} else if (!teacher_id.equals(other.teacher_id))
			return false;
		return true;
	}
	private String teacher_ad_id;
	private String teacher_id;
	private Integer ad_time;
	private Timestamp ad_start;
	private Integer ad_state;
	
	public Teacher_adVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTeacher_ad_id() {
		return teacher_ad_id;
	}
	public void setTeacher_ad_id(String teacher_ad_id) {
		this.teacher_ad_id = teacher_ad_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getAd_time() {
		return ad_time;
	}
	public void setAd_time(int ad_time) {
		this.ad_time = ad_time;
	}
	public Timestamp getAd_start() {
		return ad_start;
	}
	public void setAd_start(Timestamp ad_start) {
		this.ad_start = ad_start;
	}
	public int getAd_state() {
		return ad_state;
	}
	public void setAd_state(int ad_state) {
		this.ad_state = ad_state;
	}
	
}
