package com.Time_order.model;

import java.util.List;


public interface Time_orderDAO_interface {
	public void insert(Time_orderVO time_orderVO);
    public void update(Time_orderVO time_orderVO);
    public void delete(String time_order_id);
    public Time_orderVO findByPrimaryKey(String time_order_id);
    public List<Time_orderVO> getAll();

}
