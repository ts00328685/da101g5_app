package com.Teacher_course_list.model;

import java.util.List;

public interface Teacher_course_listDAO_interface {
	
	public void insert(Teacher_course_listVO teacher_course_listVO);
    public void update(Teacher_course_listVO teacher_course_listVO);
    public void delete(String teacher_course_list);
    public Teacher_course_listVO findByPrimaryKey(String teacher_course_list);
    public List<Teacher_course_listVO> getAll();
    public List<Teacher_course_listVOApp> getAllOneTeacherCourseList(String teacher_id);

}
