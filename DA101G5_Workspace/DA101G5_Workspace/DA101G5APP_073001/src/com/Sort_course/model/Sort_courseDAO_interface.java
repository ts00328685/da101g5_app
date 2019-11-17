package com.Sort_course.model;


import java.util.List;


public interface Sort_courseDAO_interface {
	
	public void insert(Sort_courseVO sort_courseVO);
    public void update(Sort_courseVO sort_courseVO);
    public void delete(String sort_course_id);
    public Sort_courseVO findByPrimaryKey(String sort_course_id);
    public List<Sort_courseVO> getAll();

}
