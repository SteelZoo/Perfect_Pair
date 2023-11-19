package com.olduo.last_dance.model.dao;

import java.util.List;
import java.util.Map;

import com.olduo.last_dance.model.dto.Answer;

public interface AnswerDao {
	int insert(Answer answer);
	
	int update(Answer answer);
	
	Map selectAnswer(Map map);
	
	List<Answer> getAnswerByQuiz(Integer qId);
}
