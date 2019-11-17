package com.teacher_card_group.model;

import java.util.List;

public class Teacher_card_groupService {

	private Teacher_card_groupDAO_interface dao;

	public Teacher_card_groupService() {
		dao = new Teacher_card_groupDAO();
	}

	public Teacher_card_groupVO addTeacher_card_group(String teacher_id, String teacher_card_group_name) {

		Teacher_card_groupVO teacher_card_groupVO = new Teacher_card_groupVO();

		teacher_card_groupVO.setTeacher_id(teacher_id);
		teacher_card_groupVO.setTeacher_card_group_name(teacher_card_group_name);
		dao.insert(teacher_card_groupVO);
		
		return teacher_card_groupVO;
	}

	public Teacher_card_groupVO updateTeacher_card_group(String teacher_card_group_id, String teacher_id, String teacher_card_group_name) {

		Teacher_card_groupVO teacher_card_groupVO = new Teacher_card_groupVO();

		teacher_card_groupVO.setTeacher_card_group_id(teacher_card_group_id);
		teacher_card_groupVO.setTeacher_id(teacher_id);
		teacher_card_groupVO.setTeacher_card_group_name(teacher_card_group_name);
		dao.update(teacher_card_groupVO);

		return teacher_card_groupVO;
	}

	public void deleteTeacher_card_group(String teacher_card_group_id) {
		dao.delete(teacher_card_group_id);
	}

	public Teacher_card_groupVO getOneTeacher_card_group(String teacher_card_group_id) {
		return dao.findByPrimaryKey(teacher_card_group_id);
	}

	public List<Teacher_card_groupVO> getAll() {
		return dao.getAll();
	}
}
