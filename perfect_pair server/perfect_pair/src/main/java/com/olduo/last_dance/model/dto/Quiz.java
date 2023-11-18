package com.olduo.last_dance.model.dto;

public class Quiz {
	Integer id;
	Integer gId;
	String question;
	
	public Quiz (Integer id, Integer gId, String question) {
		this.id = id;
		this.gId = gId;
		this.question = question;
	}

	public Quiz (Integer gId, String question) {
		this.gId = gId;
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
