package com.Time_order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Time_orderVO implements java.io.Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course_order_id == null) ? 0 : course_order_id.hashCode());
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
		Time_orderVO other = (Time_orderVO) obj;
		if (course_order_id == null) {
			if (other.course_order_id != null)
				return false;
		} else if (!course_order_id.equals(other.course_order_id))
			return false;
		if (teacher_id == null) {
			if (other.teacher_id != null)
				return false;
		} else if (!teacher_id.equals(other.teacher_id))
			return false;
		return true;
	}
	private String time_order_id;
	private String teacher_id;
	private String course_order_id;
	private String language_id;
	private String sort_course_id;
	private Integer c_state;
	private Integer c_judge;
	private Timestamp start_time;
	private Timestamp end_time;
	 
	
	public Time_orderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTime_order_id() {
		return time_order_id;
	}
	public void setTime_order_id(String time_order_id) {
		this.time_order_id = time_order_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getCourse_order_id() {
		return course_order_id;
	}
	public void setCourse_order_id(String course_order_id) {
		this.course_order_id = course_order_id;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getSort_course_id() {
		return sort_course_id;
	}
	public void setSort_course_id(String sort_course_id) {
		this.sort_course_id = sort_course_id;
	}
	public int getC_state() {
		return c_state;
	}
	public void setC_state(int c_state) {
		this.c_state = c_state;
	}
	public int getC_judge() {
		return c_judge;
	}
	public void setC_judge(int c_judge) {
		this.c_judge = c_judge;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	

}
