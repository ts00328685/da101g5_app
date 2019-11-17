package com.example.da101g5app.teacher;

public class Teacher_course_listVOApp extends Teacher_course_listVO implements java.io.Serializable{
	
//	private String teacher_course_list_id;
//	private String sort_course_id;
//	private String language_id;
//	private String teacher_id;
//	private Integer course_appraisal_accum;
//	private Integer course_appraisal_count;

	private String course_name;
	private String language;
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setTeacher_course_list_id(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
