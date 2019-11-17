package com.Course_order.model;

import java.util.ArrayList;
import java.util.List;


public class Course_orderService {
	
	private Course_orderDAO_interface dao;
	
	public Course_orderService() {
		dao=new Course_orderDAO();
	}
	
	public Course_orderVO addCourse_order(String member_id,String teacher_id,Integer buy_time,Integer spend_point
			,Integer remain_hour,Integer re_appointment) {
		
		Course_orderVO course_orderVO=new Course_orderVO();
		
		course_orderVO.setMember_id(member_id);
		course_orderVO.setTeacher_id(teacher_id);
		course_orderVO.setBuy_time(buy_time);
		course_orderVO.setSpend_point(spend_point);
		course_orderVO.setRemain_hour(remain_hour);
		course_orderVO.setRe_appointment(re_appointment);
		dao.insert(course_orderVO);
		
		return course_orderVO;
	}
	
	//預留給 Struts 2 用的
	public void addCourse_order(Course_orderVO course_orderVO) {
		dao.insert(course_orderVO);
	}
	
	public Course_orderVO updateCourse_order(String course_order_id, String member_id, String teacher_id,
			Integer buy_time, Integer spend_point, Integer remain_hour, Integer re_appointment) {

		Course_orderVO course_orderVO2 = new Course_orderVO();

		course_orderVO2.setCourse_order_id(course_order_id);
		course_orderVO2.setMember_id(member_id);
		course_orderVO2.setTeacher_id(teacher_id);
		course_orderVO2.setBuy_time(buy_time);
		course_orderVO2.setSpend_point(spend_point);
		course_orderVO2.setRemain_hour(remain_hour);
		course_orderVO2.setRe_appointment(re_appointment);
		dao.update(course_orderVO2);

		return dao.findByPrimaryKey(course_order_id);
	}
	
	
	//預留給 Struts 2 用的
		public void updateCourse_order(Course_orderVO course_orderVO) {
			dao.update(course_orderVO);
		}

		public void deleteCourse_order(String course_order_id) {
			dao.delete(course_order_id);
		}

		public Course_orderVO getOneCourse_order(String course_order_id) {
			return dao.findByPrimaryKey(course_order_id);
		}

		public List<Course_orderVO> getAll() {
			return dao.getAll();
		}
		
		public List<Course_orderVO>getMemAll(String member_id){
			List<Course_orderVO>list=new ArrayList();
			List<Course_orderVO>li=getAll();
			int i=0;
			for(Course_orderVO course_orderVO:li) {
				if(course_orderVO.getMember_id().equals(member_id)) {
					list.add(i,course_orderVO);
					i++;
				}
			}
			return list;
		}
		
		public List<Course_orderVO>getTeacherAll(String teacher_id){
			List<Course_orderVO>list=new ArrayList();
			List<Course_orderVO>li=getAll();
			int i=0;
			for(Course_orderVO course_orderVO:li) {				
				if(course_orderVO.getTeacher_id().equals(teacher_id)) {
					list.add(i,course_orderVO);
					i++;
				}
			}
			return list;
		}
	

}
