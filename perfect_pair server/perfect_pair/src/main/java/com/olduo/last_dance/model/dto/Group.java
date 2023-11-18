package com.olduo.last_dance.model.dto;

public class Group {
	private Integer id;
	private String creator;
	private String title;
	private String description;
	private String code;

	public Group(Integer id, String creator, String title, String description, String code) {
		this.id = id;
		this.creator = creator;
		this.title = title;
		this.description = description;
		this.code = code;
	}

	public Group(String creator, String title, String description, String code) {
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.code = code;
	}

	public Group() {
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

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

	@Override
	public String toString() {
		return "Group [id=" + id + ", creator=" + creator + ", title=" + title + ", description=" + description
				+ ", code=" + code + "]";
	}
}
