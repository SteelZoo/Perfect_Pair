package com.olduo.last_dance.model.dto;

public class GroupUser {
	private Integer id;
	private String uId;
	private Integer gId;
	
	public GroupUser(Integer id, String uId, Integer gId) {
		this.id = id;
		this.uId = uId;
		this.gId = gId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	@Override
	public String toString() {
		return "GroupUser [id=" + id + ", uId=" + uId + ", gId=" + gId + "]";
	}
}
