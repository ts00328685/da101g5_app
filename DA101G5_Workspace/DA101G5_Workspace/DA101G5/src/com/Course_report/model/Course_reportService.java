package com.Course_report.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Course_reportService {
	
	private Course_reportDAO_interface dao;
	
	public Course_reportService() {
		dao=new Course_reportJDBCDAO();
	}
	
	
	public Course_reportVO addCourse_report(String teacher_id, String member_id, String report_text,
			Integer report_state) {

		Course_reportVO course_reportVO1 = new Course_reportVO();

		course_reportVO1.setTeacher_id(teacher_id);
		course_reportVO1.setMember_id(member_id);
		course_reportVO1.setReport_text(report_text);
		course_reportVO1.setReport_state(report_state);
		dao.insert(course_reportVO1);
		

		return course_reportVO1;
	}

	//預留給 Struts 2 用的
	public void addCourse_report(Course_reportVO course_reportVO) {
		dao.insert(course_reportVO);
	}
	
	public Course_reportVO updateCourse_report(String course_report_id, String teacher_id, String member_id, 
			String report_text,Integer report_state) {

		Course_reportVO course_reportVO = new Course_reportVO();

		course_reportVO.setCourse_report_id(course_report_id);
		course_reportVO.setTeacher_id(teacher_id);
		course_reportVO.setMember_id(member_id);
		course_reportVO.setReport_text(report_text);
		course_reportVO.setReport_state(report_state);
		dao.update(course_reportVO);

		return dao.findByPrimaryKey(course_report_id);
	}
	
	//預留給 Struts 2 用的
	public void updateCourse_report(Course_reportVO course_reportVO) {
		dao.update(course_reportVO);
	}

	public void deleteCourse_report(String course_report_id) {
		dao.delete(course_report_id);
	}

	public Course_reportVO getOneCourse_report(String course_report_id) {
		return dao.findByPrimaryKey(course_report_id);
	}

	public List<Course_reportVO> getAll() {
		return dao.getAll();
	}
	public List<Course_reportVO>orderAll(){
		LinkedList <Course_reportVO>orderAll=new LinkedList<Course_reportVO>();
		List <Course_reportVO>all=getAll();
		for(Course_reportVO course_reportVO:all) {
			if(course_reportVO.getReport_state()==0) {
				orderAll.addFirst(course_reportVO);
			}
			if(course_reportVO.getReport_state()==1) {
				
				orderAll.addLast(course_reportVO);		
			}	
		}
		for(Course_reportVO course_reportVO:all) {
			
			if(course_reportVO.getReport_state()==2) {
				orderAll.addLast(course_reportVO);
			}			
		}
		
		return orderAll;
		
		
	}

}
