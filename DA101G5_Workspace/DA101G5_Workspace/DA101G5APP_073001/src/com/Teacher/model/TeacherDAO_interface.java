package com.Teacher.model;

import java.util.List;

public interface TeacherDAO_interface {
	
	public void insert(TeacherVO teacherVO);
    public void update(TeacherVO teacherVO);
    public void updatePic(String teacher_id, byte[] introduce_pic);
    public void delete(String teacher_id);
    public byte[] getImage(String teacher_id);
    public TeacherVO findByPrimaryKey(String teacher_id);
    public List<TeacherVO> getAll();
    public List<TeacherCardVO> getAllTeacherCard();
    public TeacherCardVO getOneTeacherCard(String teacher_id);
    public List<MyTeacherCardVO> getMyTeacherCard(String member_id);

}
