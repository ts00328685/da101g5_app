package com.Teacher.model;

public class MyTeacherCardVO extends TeacherCardVO implements java.io.Serializable{
//	private String teacher_id;
//	private String member_id;
//	private String work_experience;
//	private String ed_background;
//	private String certification;
//	private String teacher_introduce;
//	private Integer teacher_state;
//	private Integer appraisal_accum;
//	private Integer appraisal_count;
//	private Integer course_price;
//	private byte[] introduce_pic;
	
//	private String mem_name;
//	private String mem_country;
//	private String language;
//	private String mem_nick;
	
	private Integer remain_hour;
	
	public Integer getRemain_hour() {
		return remain_hour;
	}


	@Override
	public String getTeacher_id() {
		// TODO Auto-generated method stub
		return super.getTeacher_id();
	}


	public void setRemain_hour(Integer remain_hour) {
		this.remain_hour = remain_hour;
	}

	public MyTeacherCardVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getTeacher_id() == null) ? 0 : getTeacher_id().hashCode());
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
		MyTeacherCardVO other = (MyTeacherCardVO) obj;
		if (getTeacher_id() == null) {
			if (other.getTeacher_id() != null)
				return false;
		} else if (!getTeacher_id().equals(other.getTeacher_id()))
			return false;
		return true;
	}
	
	
	
}
