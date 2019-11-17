package com.example.da101g5app.teacher;

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


    public void setRemain_hour(Integer remain_hour) {
        this.remain_hour = remain_hour;
    }

    public MyTeacherCardVO() {
        super();
        // TODO Auto-generated constructor stub
    }

}