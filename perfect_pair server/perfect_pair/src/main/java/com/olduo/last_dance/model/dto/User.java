package com.olduo.last_dance.model.dto;

public class User {
	private String userId;
	private String name;
	private String pass;
	
	public User(String userId, String name, String pass) {
		this.userId = userId;
		this.name = name;
		this.pass = pass;
	}
	
	public User() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", pass=" + pass + "]";
	}
}