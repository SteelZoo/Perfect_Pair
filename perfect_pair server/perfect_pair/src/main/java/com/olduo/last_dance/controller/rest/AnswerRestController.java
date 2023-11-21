package com.olduo.last_dance.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olduo.last_dance.model.dto.Answer;
import com.olduo.last_dance.model.service.AnswerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/answer")
@CrossOrigin("*")
public class AnswerRestController {
	
	@Autowired
	AnswerService aService;

	@PostMapping
	@Transactional
	@ApiOperation(value = "user의 answer를 추가한다. 성공하면 true를 리턴한다.", response = Boolean.class)
	public Boolean insert(@RequestBody Answer answer) {
		aService.addAnswer(answer);
		return true;
	}
	
	@PutMapping
	@ApiOperation(value = "answer를 수정한다.성공하면 true를 리턴한다.", response = Boolean.class)
	public Boolean update(@RequestBody Answer answer) {
		if (aService.updateAnswer(answer) > 0) {
			return true;
		}
		
		return false;
	}
	
	@GetMapping("/{qId}/{userId}")
	@ApiOperation(value="Answer 객체에서 사용자의 answer를 반환한다.", response = List.class)
	public Map selectAnswer(@PathVariable Integer qId, @PathVariable String userId) {
		HashMap map = new HashMap<>();
		map.put("qId", qId);
		map.put("userId", userId);
		return aService.selectAnswer(map);
	}
	
	@GetMapping("/{qId}")
    @ApiOperation(value="quiz의 전체 answer 목록을 반환한다.", response = List.class)
    public ResponseEntity<List<Map>> getAnswerByQuiz(@PathVariable Integer qId){
        return new ResponseEntity<List<Map>>(aService.getAnswerByQuiz(qId), HttpStatus.OK);
    }
}
