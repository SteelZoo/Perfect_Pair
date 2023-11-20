package com.olduo.last_dance.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.olduo.last_dance.model.dto.Group;
import com.olduo.last_dance.model.dto.User;

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
    int removeGroup(Integer id);
    
    /**
     * userId에 해당하는 Group의 목록을 반환한다.
     * @param userId
     * @return 
     */
    List<Group> getGroupByUser(String userId);
    
    /**
     * code에 해당하는 Group에 userId에 해당하는 User를 등록한다.
     * @param map -> code, userId
     */
    Boolean insertUserToGroup(Map map);
    
    /**
     * code에 해당하는 Group 정보를 반환한다.
     * 
     * @param code
     * @return
     * 조회된 User 정보를 반환한다.
     */
    Group selectGroupByCode(String code);
    
    /**
     * map에 담긴 uId와 gId로 user가 이미 Group에 등록되었는지 확인한다.
     * 
     * @param map -> uId, gId
     * @return
     */
    int isJoin(Map map);
}