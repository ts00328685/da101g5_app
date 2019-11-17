package com.event.model;

import java.sql.Timestamp;
import java.util.List;

public class EventService {
	
	private EventDAO eventDAO;
	
	public EventService() {
		eventDAO = new EventDAO();
	}
	
	public void addEvent(String evtitle, Timestamp evstartdate, Timestamp evenddate, String evcontent, byte[] evpic,String filetype) {
		EventVO eventVO = new EventVO();
		eventVO.setEvcontent(evcontent);
		eventVO.setEvendate(evenddate);
		eventVO.setEvstartdate(evstartdate);
		eventVO.setEvtitle(evtitle);
		eventVO.setEvpic(evpic);
		eventVO.setFiletype(filetype);
		eventDAO.insert(eventVO);
	}
	
	public void updateEvent(String event_id,String evtitle, Timestamp evstartdate, Timestamp evenddate, String evcontent, byte[] evpic,Integer evstatus,String filetype) {
		EventVO eventVO = new EventVO();
		eventVO.setEvcontent(evcontent);
		eventVO.setEvendate(evenddate);
		eventVO.setEvstartdate(evstartdate);
		eventVO.setEvtitle(evtitle);
		eventVO.setEvpic(evpic);
		eventVO.setEvstatus(evstatus);
		eventVO.setEvent_id(event_id);
		eventVO.setFiletype(filetype);
		eventDAO.update(eventVO);
	}
	
	public EventVO findEventByPK(String event_id) {
		EventVO	eventVO = eventDAO.findByPK(event_id);
		return eventVO;
	}
	
	public List<EventVO> getAllEvent(){
		List<EventVO> eventlist = eventDAO.getAll();
		return eventlist;
	}
	
	public List<EventVO> getAllEventOnshelf(){
		List<EventVO> eventlist = eventDAO.getAllOnshelf();
		return eventlist;
	}
}
