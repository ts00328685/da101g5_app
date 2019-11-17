package com.Time_order.model;

import java.util.List;



public class Time_orderService {
	
	private Time_orderDAO_interface dao;
	
	public Time_orderService() {
		dao=new Time_orderJDBCDAO();
		
	}
	
	

	public Time_orderVO addTime_order( String teacher_id, String course_order_id,
			 String language_id, String sort_course_id,Integer c_state, Integer c_judge,
			 java.sql.Timestamp start_time,java.sql.Timestamp end_time) {

		Time_orderVO time_orderVO = new Time_orderVO();

		time_orderVO.setTeacher_id(teacher_id);
		time_orderVO.setCourse_order_id(course_order_id);
	    time_orderVO.setLanguage_id(language_id);
		time_orderVO.setSort_course_id(sort_course_id);
		time_orderVO.setC_state(c_state);
		time_orderVO.setC_judge(c_judge);
		time_orderVO.setStart_time(start_time);
		time_orderVO.setEnd_time(end_time);
		
		dao.insert(time_orderVO);

		return time_orderVO;
	}

	//預留給 Struts 2 用的
	public void addTime_order(Time_orderVO time_orderVO) {
		dao.insert(time_orderVO);
	}
	
	public Time_orderVO updateTime_order(String time_order_id, String teacher_id, String course_order_id,
			 String language_id, String sort_course_id,Integer c_state, Integer c_judge,
			 java.sql.Timestamp start_time,java.sql.Timestamp end_time) {

		Time_orderVO time_orderVO = new Time_orderVO();

		time_orderVO.setTime_order_id(time_order_id);
		time_orderVO.setTeacher_id(teacher_id);
		time_orderVO.setCourse_order_id(course_order_id);
	    time_orderVO.setLanguage_id(language_id);
		time_orderVO.setSort_course_id(sort_course_id);
		time_orderVO.setC_state(c_state);
		time_orderVO.setC_judge(c_judge);
		time_orderVO.setStart_time(start_time);
		time_orderVO.setEnd_time(end_time);
		dao.update(time_orderVO);

		return dao.findByPrimaryKey(time_order_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTime_order(Time_orderVO time_orderVO) {
		dao.update(time_orderVO);
	}

	public void deleteTime_order(String time_order_id) {
		dao.delete(time_order_id);
	}

	public Time_orderVO getOneTime_order(String time_order_id) {
		return dao.findByPrimaryKey(time_order_id);
	}

	public List<Time_orderVO> getAll() {
		return dao.getAll();
	}
}
