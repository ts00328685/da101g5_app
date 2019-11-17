package com.Sort_course.model;

import java.util.List;


public class Sort_courseService {
	
	private Sort_courseDAO_interface dao;
	
	Sort_courseService(){
		
		dao=new Sort_courseJDBCDAO();
	}
	
	
	public Sort_courseVO addSort_course(String sort_course) {

		Sort_courseVO sort_courseVO = new Sort_courseVO();

		sort_courseVO.setSort_course(sort_course);
		dao.insert(sort_courseVO);

		return sort_courseVO;
	}

	//預留給 Struts 2 用的
	public void addSort_course(Sort_courseVO sort_courseVO) {
		dao.insert(sort_courseVO);
	}
	
	public Sort_courseVO updateSort_course(String sort_course_id,String sort_course) {

		Sort_courseVO sort_courseVO = new Sort_courseVO();

		sort_courseVO.setSort_course(sort_course);
		sort_courseVO.setSort_course_id(sort_course_id);
		dao.update(sort_courseVO);

		return dao.findByPrimaryKey(sort_course_id);
	}
	
	//預留給 Struts 2 用的
	public void updateSort_course(Sort_courseVO sort_courseVO) {
		dao.update(sort_courseVO);
	}

	public void deleteSort_course(String sort_course_id) {
		dao.delete(sort_course_id);
	}

	public Sort_courseVO getOneSort_course(String sort_course_id) {
		return dao.findByPrimaryKey(sort_course_id);
	}

	public List<Sort_courseVO> getAll() {
		return dao.getAll();
	}

}
