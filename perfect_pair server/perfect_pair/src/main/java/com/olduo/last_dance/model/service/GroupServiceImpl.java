package com.olduo.last_dance.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olduo.last_dance.model.dao.GroupDao;
import com.olduo.last_dance.model.dto.Group;
import com.olduo.last_dance.model.dto.GroupUser;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
    GroupDao gDao;

	@Override
	public void addGroup(Group group, String userId) {
		group.setId(null);
		
		gDao.insert(group);
		GroupUser groupuser = group.getGrouplist();
		gu.setgId(group.getId());
		gu.setuId(userId);
	}

	@Override
	public void removeGroup(Integer id) {
		gDao.delete(id);
	}

	@Override
	public List<Group> getGroupByUser(String userId) {
		return gDao.selectByUser(userId);
	}
}
