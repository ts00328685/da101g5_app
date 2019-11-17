package com.event.model;

import java.util.List;

public interface EventDAO_interface {
	public void insert(EventVO eventVO);
	public void update(EventVO eventVO);
	public EventVO findByPK(String event_id);
	public List<EventVO> getAll();
	public List<EventVO> getAllOnshelf();
//	public EventVO insertThenGet(EventVO eventVO);
}
