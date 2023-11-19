package com.olduo.last_dance.model.dto;

public class Answer {
	Integer id;
	Integer qId;
	String userId;
	String answer;
	
	public Answer() {}
	
	public Answer(Integer id, Integer qId, String userId, String answer) {
		this.id = id;
		this.qId = qId;
		this.userId = userId;
		this.answer = answer;
	}
	
	public Answer(Integer qId, String userId, String answer) {
		this.qId = qId;
		this.userId = userId;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getqId() {
		return qId;
	}

	public void setqId(Integer qId) {
		this.qId = qId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", qId=" + qId + ", userId=" + userId + ", answer=" + answer + "]";
	}
}
