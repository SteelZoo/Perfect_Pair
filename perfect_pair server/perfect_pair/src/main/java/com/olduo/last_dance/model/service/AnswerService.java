package com.olduo.last_dance.model.service;

import java.util.List;
import java.util.Map;

import com.olduo.last_dance.model.dto.Answer;

public interface AnswerService {
	/**
     * user의 answer를 등록한다.
     * @param answer
     */
	Boolean addAnswer(Answer answer);
    
    /**
     * answer에 해당하는 Answer을 수정한다.
     * @param answer
     */
    int updateAnswer(Answer answer);
    
    /**
     * qId와 uId에 해당하는 Answer 객체에서 사용자의 answer를 반환한다.
     * @param qId, uId
     * @return answer
     */
    Map selectAnswer(Map map);
    
    /**
     * qId에 해당하는 Answer의 목록을 반환한다.
     * @param userId
     * @return
     */
    List<Map> getAnswerByQuiz(Integer qId);
}
