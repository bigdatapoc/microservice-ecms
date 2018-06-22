package com.ecms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class User {
	@Id
	private Integer user_id;
	private Integer event_id;
	private String name;
	private String email;
	public User() {
		super();
	}
	public User(Integer user_id, Integer event_id, String name, String email) {
		super();
		this.user_id = user_id;
		this.event_id = event_id;
		this.name = name;
		this.email = email;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", event_id=" + event_id + ", name=" + name + ", email=" + email + "]";
	}
	
}
