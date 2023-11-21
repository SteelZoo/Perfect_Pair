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
	public Boolean addQuiz(Quiz quiz) {
		if (qDao.getQuizByGroup(quiz.getgId()).size() < 10) {
			qDao.insert(quiz);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean removeQuiz(Integer id) {
		if(qDao.delete(id) > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Quiz> getQuizByGroup(Integer gId) {
		return qDao.getQuizByGroup(gId);
	}

}
