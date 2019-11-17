package com.example.da101g5app.teacher;

public class Teacher_course_listVO implements java.io.Serializable{

    private String teacher_course_list_id;
    private String sort_course_id;
    private String language_id;
    private String teacher_id;
    private Integer course_appraisal_accum;
    private Integer course_appraisal_count;

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