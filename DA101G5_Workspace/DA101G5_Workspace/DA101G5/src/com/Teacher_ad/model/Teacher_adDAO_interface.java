package com.Teacher_ad.model;


import java.util.List;


public interface Teacher_adDAO_interface {
	
	public void insert(Teacher_adVO teacher_adVO);
    public void update(Teacher_adVO teacher_adVO);
    public void delete(String teacher_ad_id);
    public Teacher_adVO findByPrimaryKey(String teacher_ad_id);
    public List<Teacher_adVO> getAll();

}
