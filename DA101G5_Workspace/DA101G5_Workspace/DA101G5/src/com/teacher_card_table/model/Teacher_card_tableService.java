package com.teacher_card_table.model;

import java.util.List;

public class Teacher_card_tableService {

	private Teacher_card_tableDAO_interface dao;

	public Teacher_card_tableService() {
		dao = new Teacher_card_tableDAO();
	}

	public Teacher_card_tableVO addTeacher_card_table(String card_id, String teacher_card_group_id) {

		Teacher_card_tableVO teacher_card_tableVO = new Teacher_card_tableVO();

		teacher_card_tableVO.setCard_id(card_id);
		teacher_card_tableVO.setTeacher_card_group_id(teacher_card_group_id);
		dao.insert(teacher_card_tableVO);
		
		return teacher_card_tableVO;
	}

	public Teacher_card_tableVO updateTeacher_card_table(String card_id, String teacher_card_group_id) {

		Teacher_card_tableVO teacher_card_tableVO = new Teacher_card_tableVO();

		teacher_card_tableVO.setCard_id(card_id);
		teacher_card_tableVO.setTeacher_card_group_id(teacher_card_group_id);
		dao.update(teacher_card_tableVO);

		return teacher_card_tableVO;
	}

	public void deleteTeacher_card_table(String card_id, String teacher_card_group_id) {
		dao.delete(card_id, teacher_card_group_id);
	}

	public Teacher_card_tableVO getOneTeacher_card_table(String card_id, String teacher_card_group_id) {
		return dao.findByPrimaryKey(card_id, teacher_card_group_id);
	}

	public List<Teacher_card_tableVO> getAll() {
		return dao.getAll();
	}
}
