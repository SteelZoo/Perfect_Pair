package com.olduo.last_dance.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olduo.last_dance.model.dao.AnswerDao;
import com.olduo.last_dance.model.dto.Answer;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDao aDao;

	@Override
	public void addAnswer(Answer answer) {
		aDao.insert(answer);
	}

	@Override
	public int updateAnswer(Answer answer) {
		return aDao.update(answer);
	}

	@Override
	public Map selectAnswer(Map map) {
		return aDao.selectAnswer(map);
	}

	@Override
	public List<Map> getAnswerByQuiz(Integer qId) {
		return aDao.getAnswerByQuiz(qId);
	}
}
