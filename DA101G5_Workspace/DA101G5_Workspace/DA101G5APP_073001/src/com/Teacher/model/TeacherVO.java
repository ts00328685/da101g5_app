package com.Teacher.model;

public class TeacherVO implements java.io.Serializable{
	private String teacher_id;
	private String member_id;
	private String work_experience;
	private String ed_background;
	private String certification;
	private String teacher_introduce;
	private Integer teacher_state;
	private Integer appraisal_accum;
	private Integer appraisal_count;
	private Integer course_price;
	private byte[]introduce_pic;
	
	public TeacherVO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getWork_experience() {
		return work_experience;
	}
	public void setWork_experience(String work_experience) {
		this.work_experience = work_experience;
	}
	public String getEd_background() {
		return ed_background;
	}
	public void setEd_background(String ed_background) {
		this.ed_background = ed_background;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getTeacher_introduce() {
		return teacher_introduce;
	}
	public void setTeacher_introduce(String teacher_introduce) {
		this.teacher_introduce = teacher_introduce;
	}
	public Integer getTeacher_state() {
		return teacher_state;
	}
	public void setTeacher_state(Integer teacher_state) {
		this.teacher_state = teacher_state;
	}
	public Integer getAppraisal_accum() {
		return appraisal_accum;
	}
	public void setAppraisal_accum(Integer appraisal_accum) {
		this.appraisal_accum = appraisal_accum;
	}
	public Integer getAppraisal_count() {
		return appraisal_count;
	}
	public void setAppraisal_count(Integer appraisal_count) {
		this.appraisal_count = appraisal_count;
	}
	public Integer getCourse_price() {
		return course_price;
	}
	public void setCourse_price(Integer course_price) {
		this.course_price = course_price;
	}
	public byte[] getIntroduce_pic() {
		return introduce_pic;
	}
	public void setIntroduce_pic(byte[] introduce_pic) {
		this.introduce_pic = introduce_pic;
	}
	
	
	
}
