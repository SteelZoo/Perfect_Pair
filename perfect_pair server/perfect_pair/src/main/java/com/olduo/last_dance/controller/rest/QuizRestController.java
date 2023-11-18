package com.olduo.last_dance.controller.rest;

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

import com.olduo.last_dance.model.dto.Quiz;
import com.olduo.last_dance.model.service.QuizService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/quiz")
@CrossOrigin("*")
public class QuizRestController {
	@Autowired
	QuizService qService;
	
	@PostMapping
    @Transactional
    @ApiOperation(value="Quiz를 추가한다. 성공하면 true를 리턴한다.", response = Boolean.class)
    public Boolean insert(@RequestBody Quiz quiz) {
        qService.addQuiz(quiz);
        return true;
    }
	
	@DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value="{id}에 해당하는 quiz를 삭제한다. 성공하면 true를 리턴한다.", response = Boolean.class)
    public Boolean delete(@PathVariable Integer id) {
        qService.removeQuiz(id);
        return true;
	}
	
	@GetMapping("/{gId}")
    @ApiOperation(value="gId에 해당하는 group의 quiz 목록을 반환한다.", response = List.class)
    public ResponseEntity<List<Quiz>> getGroupByUser(@PathVariable Integer gId){
        return new ResponseEntity<List<Quiz>>(qService.getQuizByGroup(gId), HttpStatus.OK);
    }
}