package com.Time_order.model;

import java.util.List;


public interface Time_orderDAO_interface {
	public String insert(Time_orderVO time_orderVO);
    public void update(Time_orderVO time_orderVO);
    public void delete(String time_order_id);
    public Time_orderVO findByPrimaryKey(String time_order_id);
    public List<Time_orderVO> getAll();
    public List<Time_orderVOApp> getAllJoinCourseOrder(String member_id);
    
    public void updateTOState(String course_order_id, String time_order_id, String total_hour, String c_state, String c_judge);

}
