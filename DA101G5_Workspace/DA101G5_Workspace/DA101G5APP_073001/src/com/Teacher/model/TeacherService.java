package com.Teacher.model;

import java.util.List;



public class TeacherService {
	
	private TeacherDAO_interface dao;
	
	public TeacherService() {
		dao= new TeacherDAO();
	}
	
	public TeacherVO addTeacher(String member_id, String work_experience,
			String ed_background, String certification, String teacher_introduce,  Integer teacher_state
			, Integer appraisal_accum, Integer appraisal_count,Integer course_price,byte[]introduce_pic) {

		TeacherVO teacherVO = new TeacherVO();


				 teacherVO = new TeacherVO();

				 teacherVO.setMember_id(member_id);
				 teacherVO.setWork_experience(work_experience);
				 teacherVO.setEd_background(ed_background);
				 teacherVO.setCertification(certification);
				 teacherVO.setTeacher_introduce(teacher_introduce);
				 teacherVO.setTeacher_state(teacher_state);
				 teacherVO.setAppraisal_accum(appraisal_accum);
				 teacherVO.setAppraisal_count(appraisal_count);
				 teacherVO.setCourse_price(course_price);
				 teacherVO.setIntroduce_pic(introduce_pic);
		dao.insert(teacherVO);

		return teacherVO;
	}

	//預留給 Struts 2 用的
	public void addTeacher(TeacherVO teacherVO) {
		dao.insert(teacherVO);
	}
	
	public TeacherVO updateTeacher(String teacher_id, String member_id, String work_experience,
			String ed_background, String certification, String teacher_introduce,  Integer teacher_state
			, Integer appraisal_accum, Integer appraisal_count,Integer course_price,byte[]introduce_pic
			) {

		TeacherVO teacherVO = new TeacherVO();

		teacherVO.setTeacher_id(teacher_id);
		 teacherVO.setMember_id(member_id);
		 teacherVO.setWork_experience(work_experience);
		 teacherVO.setEd_background(ed_background);
		 teacherVO.setCertification(certification);
		 teacherVO.setTeacher_introduce(teacher_introduce);
		 teacherVO.setTeacher_state(teacher_state);
		 teacherVO.setAppraisal_accum(appraisal_accum);
		 teacherVO.setAppraisal_count(appraisal_count);
		 teacherVO.setCourse_price(course_price);
		 teacherVO.setIntroduce_pic(introduce_pic);
		dao.update(teacherVO);

		return dao.findByPrimaryKey(teacher_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTeacher(TeacherVO teacherVO) {
		dao.update(teacherVO);
	}

	public void deleteTeacher(String teacher_id) {
		dao.delete(teacher_id);
	}

	public TeacherVO getOneTeacher(String teacher_id) {
		return dao.findByPrimaryKey(teacher_id);
	}

	public List<TeacherVO> getAll() {
		return dao.getAll();
	}

}
