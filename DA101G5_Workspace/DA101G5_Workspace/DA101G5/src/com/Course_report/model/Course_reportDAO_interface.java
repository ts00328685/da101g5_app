package com.Course_report.model;


import java.util.List;

public interface Course_reportDAO_interface {
	 public void insert(Course_reportVO course_reportVO);
     public void update(Course_reportVO course_reportVO);
     public void delete(String course_report_id);
     public Course_reportVO findByPrimaryKey(String course_report_id);
     public List<Course_reportVO> getAll();

}
