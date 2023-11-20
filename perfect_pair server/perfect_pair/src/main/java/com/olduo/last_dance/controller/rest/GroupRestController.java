package com.olduo.last_dance.controller.rest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
import com.olduo.last_dance.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/group")
@CrossOrigin("*")
public class GroupRestController {
	@Autowired
	GroupService gService;

	@Autowired
	UserService uService;

	@PostMapping("/{userId}")
	@Transactional
	@ApiOperation(value = "group을 생성(추가)한다. 성공하면 true를 리턴한다.", response = Boolean.class)
	public Boolean insert(@RequestBody Group group, @PathVariable String userId) throws NoSuchAlgorithmException {
		gService.addGroup(group, userId);
		return true;
	}

	@PostMapping("/{code}/{userId}")
	@Transactional
	@ApiOperation(value = "group에 사용자를 등록(초대)한다. 성공하면 true를 리턴한다.", response = Boolean.class)
	public Boolean insertUserToGroup(@PathVariable String code, @PathVariable String userId) {
		HashMap map = new HashMap<>();
		map.put("uId", userId);
		map.put("code", code);
		
		return gService.insertUserToGroup(map);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "{id}에 해당하는 group을 삭제한다. 성공하면 true를 리턴한다.", response = Boolean.class)
	public Boolean delete(@PathVariable Integer id) {
		if (gService.removeGroup(id) == 1) {
			return true;
		}
		
		return false;
	}

	@GetMapping("/list/{userId}")
	@ApiOperation(value = "user가 속한 전체 group의 목록을 반환한다.", response = List.class)
	public ResponseEntity<List<Group>> getGroupByUser(@PathVariable String userId) {
		return new ResponseEntity<List<Group>>(gService.getGroupByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/{code}")
	@ApiOperation(value = "code에 해당하는 Group을 반환한다.", response = Group.class)
	public Group selectGroupByCode(@PathVariable String code) {
		return gService.selectGroupByCode(code);
	}
}
