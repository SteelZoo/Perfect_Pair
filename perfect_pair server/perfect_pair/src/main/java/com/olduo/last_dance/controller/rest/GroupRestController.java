package com.olduo.last_dance.controller.rest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olduo.last_dance.model.dto.Group;
import com.olduo.last_dance.model.service.GroupService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/group")
@CrossOrigin("*")
public class GroupRestController {
	@Autowired
    GroupService gService;
	
	@PostMapping("/{userId}")
    @Transactional
    @ApiOperation(value="그룹 객체를 추가한다. 성공하면 true를 리턴한다.", response = Boolean.class)
    public Boolean insert(@RequestBody Group group, @PathVariable String userId) throws NoSuchAlgorithmException {
        gService.addGroup(group, userId);
        return true;
    }
	
	@PostMapping("/{code}/{userId}")
    @Transactional
    @ApiOperation(value="그룹에 사용자를 등록한다. 성공하면 true를 리턴한다.", response = Boolean.class)
    public Boolean insertUserToGroup(@PathVariable String code, @PathVariable String userId) {
        gService.insertUserToGroup(code, userId);
        return true;
    }
	
	@DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value="{id}에 해당하는 그룹을 삭제한다. 성공하면 true를 리턴한다.", response = Boolean.class)
    public Boolean delete(@PathVariable Integer id) {
        gService.removeGroup(id);
        return true;
	}
	
	@GetMapping("/{userId}")
    @ApiOperation(value="사용자가 속한 전체 그룹의 목록을 반환한다.", response = List.class)
    public ResponseEntity<List<Group>> getGroupByUser(@PathVariable String userId){
        return new ResponseEntity<List<Group>>(gService.getGroupByUser(userId), HttpStatus.OK);
    }
}
