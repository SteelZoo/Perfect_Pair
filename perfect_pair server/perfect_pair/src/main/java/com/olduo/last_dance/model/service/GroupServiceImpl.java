package com.olduo.last_dance.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olduo.last_dance.controller.rest.GroupRestController;
import com.olduo.last_dance.model.dao.GroupDao;
import com.olduo.last_dance.model.dao.GroupUserDao;
import com.olduo.last_dance.model.dto.Group;
import com.olduo.last_dance.model.dto.GroupUser;
import com.olduo.last_dance.util.SHA256;

@Service
public class GroupServiceImpl implements GroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(GroupRestController.class);
	
	@Autowired
    GroupDao gDao;
	
	@Autowired
    GroupUserDao guDao;

	@Transactional
	@Override
	public void addGroup(Group group, String userId) throws NoSuchAlgorithmException {
		SHA256 sha256 = new SHA256();
		
		String newCode = sha256.encrypt(group.getTitle() + group.getCreator() + System.currentTimeMillis());
		
		group.setCode(newCode);
		
		gDao.insert(group);
				
		GroupUser groupUser = new GroupUser(userId, group.getId());
		
		guDao.insert(groupUser);
	}

	@Override
	public void removeGroup(Integer id) {
		gDao.delete(id);
	}

	@Override
	public List<Group> getGroupByUser(String userId) {
		return gDao.getGroupByUser(userId);
	}

	@Override
	public void insertUserToGroup(String code, String userId) {
		gDao.insertUserToGroup(code, userId);
	}
}
