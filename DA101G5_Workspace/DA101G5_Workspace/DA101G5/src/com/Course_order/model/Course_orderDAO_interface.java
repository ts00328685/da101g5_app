package com.Course_order.model;


import java.util.List;

public interface Course_orderDAO_interface {
	
	public void insert(Course_orderVO course_orderVO);
	public void update(Course_orderVO course_orderVO);
    public void delete(String course_order_id);
    public Course_orderVO findByPrimaryKey(String course_order_id);
    public List<Course_orderVO> getAll();

}
