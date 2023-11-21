package com.olduo.last_dance.model.service;

import java.util.List;

import com.olduo.last_dance.model.dto.Quiz;

public interface QuizService {
	/**
     * Quiz를 등록한다.
     * @param Quiz
     */
	Boolean addQuiz(Quiz quiz);
    
    /**
     * id에 해당하는 Quiz을 삭제한다.
     * @param id
     */
	Boolean removeQuiz(Integer id);
    
    /**
     * userId에 해당하는 Group의 목록을 반환한다.
     * @param userId
     * @return
     */
    List<Quiz> getQuizByGroup(Integer gId);
}
