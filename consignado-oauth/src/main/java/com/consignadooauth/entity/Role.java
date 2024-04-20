package com.consignadooauth.entity;

import java.io.Serializable;

public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String roleName;
	
	public Role() {

	}
	
	public Role(long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
