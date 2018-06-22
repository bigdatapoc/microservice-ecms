package com.ecms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Event {
	@Id
	private Integer event_id;
	private String event_name;
	
	
	public Event() {
		super();
	}


	public Event(Integer event_id, String event_name) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
	}


	public Integer getEvent_id() {
		return event_id;
	}


	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}


	public String getEvent_name() {
		return event_name;
	}


	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}


	@Override
	public String toString() {
		return "Event [event_id=" + event_id + ", event_name=" + event_name + "]";
	}
	
	

	

}
