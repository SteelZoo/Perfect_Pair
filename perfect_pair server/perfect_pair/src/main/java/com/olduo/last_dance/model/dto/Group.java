package com.olduo.last_dance.model.dto;

import java.util.List;

public class Group {
	private Integer id;
	private String title;
	private String description;
	private String code;
	
	private List<GroupUser> grouplist;

	public Group(Integer id, String title, String description, String code) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.code = code;
	}

	public Group(String title, String description, String code) {
		this.title = title;
		this.description = description;
		this.code = code;
	}

	public Group() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<GroupUser> getGrouplist() {
		return grouplist;
	}

	public void setGrouplist(List<GroupUser> grouplist) {
		this.grouplist = grouplist;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", title=" + title + ", description=" + description + ", code=" + code + "]";
	}
}
