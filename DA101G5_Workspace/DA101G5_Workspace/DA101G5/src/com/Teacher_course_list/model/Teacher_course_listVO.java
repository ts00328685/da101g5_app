package com.Teacher_course_list.model;

public class Teacher_course_listVO implements java.io.Serializable{
	
	private String teacher_course_list_id;
	private String sort_course_id;
	private String language_id;
	private String teacher_id;
	private Integer course_state;
	private Integer course_appraisal_accum;
	private Integer course_appraisal_count;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teacher_course_list_id == null) ? 0 : teacher_course_list_id.hashCode());
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
		Teacher_course_listVO other = (Teacher_course_listVO) obj;
		if (teacher_course_list_id == null) {
			if (other.teacher_course_list_id != null)
				return false;
		} else if (!teacher_course_list_id.equals(other.teacher_course_list_id))
			return false;
		return true;
	}
	public Integer getCourse_state() {
		return course_state;
	}
	public void setCourse_state(Integer course_state) {
		this.course_state = course_state;
	}
	
	
	public Teacher_course_listVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTeacher_course_list_id() {
		return teacher_course_list_id;
	}
	public void setTeacher_course_list_id(String teacher_course_list_id) {
		this.teacher_course_list_id = teacher_course_list_id;
	}
	public String getSort_course_id() {
		return sort_course_id;
	}
	public void setSort_course_id(String sort_course_id) {
		this.sort_course_id = sort_course_id;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public Integer getCourse_appraisal_accum() {
		return course_appraisal_accum;
	}
	public void setCourse_appraisal_accum(Integer course_appraisal_accum) {
		this.course_appraisal_accum = course_appraisal_accum;
	}
	public Integer getCourse_appraisal_count() {
		return course_appraisal_count;
	}
	public void setCourse_appraisal_count(Integer course_appraisal_count) {
		this.course_appraisal_count = course_appraisal_count;
	}

}
