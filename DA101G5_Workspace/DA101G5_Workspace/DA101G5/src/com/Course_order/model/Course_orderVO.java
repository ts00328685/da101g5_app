package com.Course_order.model;


public class Course_orderVO implements java.io.Serializable{
	private String course_order_id;
	private String member_id;
	private String teacher_id;
	private Integer buy_time;
	private Integer spend_point;
	private Integer remain_hour;
	private Integer re_appointment;
	
	
	public Course_orderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
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
		Course_orderVO other = (Course_orderVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		if (teacher_id == null) {
			if (other.teacher_id != null)
				return false;
		} else if (!teacher_id.equals(other.teacher_id))
			return false;
		return true;
	}
	public String getCourse_order_id() {
		return course_order_id;
	}
	public void setCourse_order_id(String course_order_id) {
		this.course_order_id = course_order_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(int buy_time) {
		this.buy_time = buy_time;
	}
	public int getSpend_point() {
		return spend_point;
	}
	public void setSpend_point(int spend_point) {
		this.spend_point = spend_point;
	}
	public int getRemain_hour() {
		return remain_hour;
	}
	public void setRemain_hour(int remain_hour) {
		this.remain_hour = remain_hour;
	}
	public int getRe_appointment() {
		return re_appointment;
	}
	public void setRe_appointment(int re_appointment) {
		this.re_appointment = re_appointment;
	}

}
