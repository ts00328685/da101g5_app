package com.event.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class EventVO implements Serializable {
	private String event_id;
	private String evtitle;
	private Timestamp evstartdate;
	private Timestamp evendate;
	private String evcontent;
	private byte[] evpic;
	private Integer evstatus;
	private String filetype;
	
	public EventVO() {
		super();
	}

	public EventVO(String event_id, String evtitle, Timestamp evstartdate, Timestamp evendate, String evcontent,
			byte[] evpic, Integer evstatus ,String filetype) {
		super();
		this.event_id = event_id;
		this.evtitle = evtitle;
		this.evstartdate = evstartdate;
		this.evendate = evendate;
		this.evcontent = evcontent;
		this.evpic = evpic;
		this.evstatus = evstatus;
		this.filetype = filetype;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvtitle() {
		return evtitle;
	}

	public void setEvtitle(String evtitle) {
		this.evtitle = evtitle;
	}

	public Timestamp getEvstartdate() {
		return evstartdate;
	}

	public void setEvstartdate(Timestamp evstartdate) {
		this.evstartdate = evstartdate;
	}

	public Timestamp getEvendate() {
		return evendate;
	}

	public void setEvendate(Timestamp evendate) {
		this.evendate = evendate;
	}

	public String getEvcontent() {
		return evcontent;
	}

	public void setEvcontent(String evcontent) {
		this.evcontent = evcontent;
	}

	public byte[] getEvpic() {
		return evpic;
	}

	public void setEvpic(byte[] evpic) {
		this.evpic = evpic;
	}

	public Integer getEvstatus() {
		return evstatus;
	}

	public void setEvstatus(Integer evstatus) {
		this.evstatus = evstatus;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
}
