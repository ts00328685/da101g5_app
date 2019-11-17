package com.example.da101g5app.course;


public class Course_orderVOApp extends Course_orderVO implements java.io.Serializable{
//	private String course_order_id;
//	private String member_id;
//	private String teacher_id;
//	private Integer buy_time;
//	private Integer spend_point;
//	private Integer remain_hour;
//	private Integer re_appointment;
	
	private String teacher_name;
	
	
	public String getTeacher_name() {
		return teacher_name;
	}


	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}


	public Course_orderVOApp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
