package com.olduo.last_dance.model.dao;

import java.util.List;

import com.olduo.last_dance.model.dto.Quiz;

public interface QuizDao {
	int insert(Quiz quiz);
	
	int delete(int id);
	
	List<Quiz> getQuizByGroup(Integer gId);
}
