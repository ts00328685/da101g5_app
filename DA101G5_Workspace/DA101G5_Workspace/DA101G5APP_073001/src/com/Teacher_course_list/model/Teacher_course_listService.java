package com.Teacher_course_list.model;

import java.util.List;


public class Teacher_course_listService {
	
	private Teacher_course_listDAO_interface dao;
	
	public Teacher_course_listService() {
		
		dao=new Teacher_course_listJDBCDAO();
	}
	
	public Teacher_course_listVO addTeacher_course_list(String sort_course_id, String language_id,  String teacher_id,
			Integer course_appraisal_accum, Integer course_appraisal_count) {

		Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();

		teacher_course_listVO.setSort_course_id(sort_course_id);
		teacher_course_listVO.setLanguage_id(language_id);
		teacher_course_listVO.setTeacher_id(teacher_id);
		teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
		teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
		dao.insert(teacher_course_listVO);

		return teacher_course_listVO;
	}

	//預留給 Struts 2 用的
	public void addTeacher_course_list(Teacher_course_listVO teacher_course_listVO) {
		dao.insert(teacher_course_listVO);
	}
	
	public Teacher_course_listVO updateTeacher_course_list(String teacher_course_list_id, String sort_course_id, 
			String language_id,  String teacher_id,
			Integer course_appraisal_accum, Integer course_appraisal_count) {

		Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();

		teacher_course_listVO.setTeacher_course_list_id(teacher_course_list_id);
		teacher_course_listVO.setSort_course_id(sort_course_id);
		teacher_course_listVO.setLanguage_id(language_id);
		teacher_course_listVO.setTeacher_id(teacher_id);
		teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
		teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
		dao.update(teacher_course_listVO);

		return dao.findByPrimaryKey(teacher_course_list_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTeacher_course_list(Teacher_course_listVO teacher_course_listVO) {
		dao.update(teacher_course_listVO);
	}

	public void deleteTeacher_course_list(String teacher_course_list_id) {
		dao.delete(teacher_course_list_id);
	}

	public Teacher_course_listVO getOneTeacher_course_list(String teacher_course_list_id) {
		return dao.findByPrimaryKey(teacher_course_list_id);
	}

	public List<Teacher_course_listVO> getAll() {
		return dao.getAll();
	}

}
