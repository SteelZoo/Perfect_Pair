package com.olduo.last_dance.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olduo.last_dance.controller.rest.GroupRestController;
import com.olduo.last_dance.model.dao.GroupDao;
import com.olduo.last_dance.model.dao.GroupUserDao;
import com.olduo.last_dance.model.dao.UserDao;
import com.olduo.last_dance.model.dto.Group;
import com.olduo.last_dance.model.dto.GroupUser;
import com.olduo.last_dance.util.SHA256;

@Service
public class GroupServiceImpl implements GroupService {

	private static final Logger logger = LoggerFactory.getLogger(GroupRestController.class);

	@Autowired
	UserDao uDao;

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
	public int removeGroup(Integer id) {
		return gDao.delete(id);
	}

	@Override
	public List<Group> getGroupByUser(String userId) {
		return gDao.getGroupByUser(userId);
	}

	@Override
	public Boolean insertUserToGroup(Map map) {
		String code = map.get("code").toString();
		String uId = map.get("uId").toString();
		
		Group group = gDao.selectGroupByCode(code);

		if (group != null) {
			if (uDao.select(uId) != null && !group.getCreator().contentEquals(uId)) {
				HashMap newMap = new HashMap<>();
					newMap.put("uId", uId);
					newMap.put("gId", group.getId());
				
				if (gDao.isJoin(newMap) <= 0) {
					gDao.insertUserToGroup(newMap);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public Group selectGroupByCode(String code) {
		return gDao.selectGroupByCode(code);
	}

	@Override
	public int isJoin(Map map) {
		HashMap isJoinMap = new HashMap<>();
		String uId = isJoinMap.get("uId").toString();
		String gId = isJoinMap.get("gId").toString();
		
		return gDao.isJoin(isJoinMap);
	}
}
