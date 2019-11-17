package com.chatroom.model;

import java.util.Set;

public class StateVO {
	private String Mtype;
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;

	public StateVO(String Mtype, String user, Set<String> users) {
		super();
		this.Mtype = Mtype;
		this.user = user;
		this.users = users;
	}

	public String getMtype() {
		return Mtype;
	}

	public void setMtype(String Mtype) {
		this.Mtype = Mtype;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}
}
