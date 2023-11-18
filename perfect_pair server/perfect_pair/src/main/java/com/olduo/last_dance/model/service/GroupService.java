package com.olduo.last_dance.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.olduo.last_dance.model.dto.Group;

public interface GroupService {
	/**
     * Group를 등록한다.
     * @param group
	 * @throws NoSuchAlgorithmException 
     */
    void addGroup(Group group, String userId) throws NoSuchAlgorithmException;
    
    /**
     * id에 해당하는 Group을 삭제한다.
     * @param id
     */
    void removeGroup(Integer id);
    
    /**
     * userId에 해당하는 Group의 목록을 반환한다.
     * @param userId
     * @return
     */
    List<Group> getGroupByUser(String userId);
    
    /**
     * userId에 해당하는 User를, 받아온 code에 해당하는 Group에 등록한다.
     * @param code, userId
     */
    void insertUserToGroup(String code, String userId);
}
