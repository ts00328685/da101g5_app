package com.Course_order.model;


import java.util.List;

public interface Course_orderDAO_interface {
	
	public void insert(Course_orderVO course_orderVO);
	public void insertApp(Course_orderVO course_orderVO);
	public void update(Course_orderVO course_orderVO);
    public void delete(String course_order_id);
    public Course_orderVO findByPrimaryKey(String course_order_id);
    public List<Course_orderVO> getAll();
    
    public List<Course_orderVO> getMyCourseOrders(String member_id);
    
    public List<Course_orderVOApp> getMyCourseOrderRecords(String member_id);
    
    public List<Course_orderVOApp> getMyOneTeacherCourse(String member_id, String teacher_id);
    
    

}
