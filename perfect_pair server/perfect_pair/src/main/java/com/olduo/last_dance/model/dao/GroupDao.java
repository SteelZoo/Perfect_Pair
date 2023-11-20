package com.olduo.last_dance.model.dao;

import java.util.List;
import java.util.Map;

import com.olduo.last_dance.model.dto.Group;

public interface GroupDao {
	int insert(Group group);
	
	int delete(int id);
	
	List<Group> getGroupByUser(String userId);
	
	int insertUserToGroup(Map map);
	
	Group selectGroupByCode(String code);
	
	int isJoin(Map map);
}
