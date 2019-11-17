package com.example.da101g5app.teacher;

public class TeacherCardVO extends TeacherVO implements java.io.Serializable{
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

    private String mem_name;
    private String mem_country;
    private String language;
    private String mem_nick;

    public String getMem_nick() {
        return mem_nick;
    }

    public void setMem_nick(String mem_nick) {
        this.mem_nick = mem_nick;
    }

    public String getMem_country() {
        return mem_country;
    }

    public void setMem_country(String mem_country) {
        this.mem_country = mem_country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public TeacherCardVO() {
        super();
        // TODO Auto-generated constructor stub
    }
}