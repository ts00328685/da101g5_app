package com.Teacher.model;

import java.util.List;

public interface TeacherDAO_interface {
	
	public void insert(TeacherVO teacherVO);
    public void update(TeacherVO teacherVO);
    public void updateState(TeacherVO teacherVO);
    public void delete(String teacher_id);
    public TeacherVO findByPrimaryKey(String teacher_id);
    public List<TeacherVO> getAll();

}
