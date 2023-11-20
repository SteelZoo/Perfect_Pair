package com.olduo.last_dance.model.dto;

public class Quiz {
	Integer id;
	Integer gId;
	String title;
	String question;
	
	public Quiz(){
		
	}

	public Quiz (Integer id, Integer gId, String title, String question) {
		this.id = id;
		this.gId = gId;
		this.title = title;
		this.question = question;
	}

	public Quiz (Integer gId, String title, String question) {
		this.gId = gId;
		this.title = title;
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", gId=" + gId + ", question=" + question + "]";
	}
}
