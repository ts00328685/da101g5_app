package com.Course_report.model;


public class Course_reportVO implements java.io.Serializable ,Comparable<Course_reportVO>{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course_report_id == null) ? 0 : course_report_id.hashCode());
		result = prime * result + ((report_state == null) ? 0 : report_state.hashCode());
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
		Course_reportVO other = (Course_reportVO) obj;
		if (course_report_id == null) {
			if (other.course_report_id != null)
				return false;
		} else if (!course_report_id.equals(other.course_report_id))
			return false;
		if (report_state == null) {
			if (other.report_state != null)
				return false;
		} else if (!report_state.equals(other.report_state))
			return false;
		return true;
	}
	private String course_report_id; 
	private String teacher_id;
	private String member_id;
	private String report_text;
	private Integer report_state;
	
	public Course_reportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCourse_report_id() {
		return course_report_id;
	}
	public void setCourse_report_id(String course_report_id) {
		this.course_report_id = course_report_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReport_text() {
		return report_text;
	}
	public void setReport_text(String report_text) {
		this.report_text = report_text;
	}
	public int getReport_state() {
		return report_state;
	}
	public void setReport_state(int report_state) {
		this.report_state = report_state;
	}
	@Override
	public int compareTo(Course_reportVO o) {
		// TODO Auto-generated method stub
		 if(this.getCourse_report_id().equals(o.getCourse_report_id()) ){
	            return 1;
	        } 
	        if(!this.getCourse_report_id().equals(o.getCourse_report_id())){
	            return -1;
	        }
		return (-1)*(this.getReport_state() - o.getReport_state());
	}
}
