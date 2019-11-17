package com.example.da101g5app.course;

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
