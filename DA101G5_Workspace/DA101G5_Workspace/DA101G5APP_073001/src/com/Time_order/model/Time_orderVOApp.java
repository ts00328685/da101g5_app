package com.Time_order.model;

import java.util.Date;

public class Time_orderVOApp extends Time_orderVO implements java.io.Serializable{
//	private String time_order_id;
//	private String teacher_id;
//	private String course_order_id;
//	private String language_id;
//	private String sort_course_id;
//	private Integer c_state;
//	private Integer c_judge;
//	private Date start_time;
//	private Date end_time;
	
	private String teacher_name;
	private String language;
	private String course;
	private String student_name;
	private String student_member_id;
	
	
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_member_id() {
		return student_member_id;
	}
	public void setStudent_member_id(String student_member_id) {
		this.student_member_id = student_member_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	

}
