package com.olduo.last_dance.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olduo.last_dance.controller.rest.UserRestController;
import com.olduo.last_dance.model.dao.UserDao;
import com.olduo.last_dance.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private static UserServiceImpl instance = new UserServiceImpl();

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	private UserServiceImpl() {

	}

	@Autowired
	private UserDao userDao;

	@Override
	public void join(User user) {
		userDao.insert(user);
	}

	@Override
	public User login(String userId, String pass) {
		User user = userDao.select(userId);
		if (user != null && user.getPass().equals(pass)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean isUsedId(String userId) {
		return userDao.select(userId) != null;
	}

	@Override
	public void leave(String userId) {
        userDao.delete(userId);

	}

	@Override
	public User selectUser(String userId) {
		User user = userDao.select(userId);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
}