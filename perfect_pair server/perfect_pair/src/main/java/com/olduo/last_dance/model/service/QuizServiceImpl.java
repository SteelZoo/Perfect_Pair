package com.olduo.last_dance.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olduo.last_dance.model.dao.QuizDao;
import com.olduo.last_dance.model.dto.Quiz;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	QuizDao qDao;

	@Override
	public void addQuiz(Quiz quiz) {
		qDao.insert(quiz);
	}

	@Override
	public void removeQuiz(Integer id) {
		qDao.delete(id);
	}

	@Override
	public List<Quiz> getQuizByGroup(Integer gId) {
		return qDao.getQuizByGroup(gId);
	}

}
