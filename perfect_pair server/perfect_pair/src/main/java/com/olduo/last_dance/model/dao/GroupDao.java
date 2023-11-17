package com.olduo.last_dance.model.dao;

import java.util.List;

import com.olduo.last_dance.model.dto.Group;

public interface GroupDao {
	int insert(Group group);
	
	int delete(int id);
	
	List<Group> selectByUser(String userId);
}
