package com.ua.glebkorobov.backend.controller;

import com.ua.glebkorobov.backend.dto.AnswerDto;
import com.ua.glebkorobov.backend.entity.AnswerEntity;
import com.ua.glebkorobov.backend.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<AnswerEntity> getAnswerById(@PathVariable long id){
        return new ResponseEntity<>(answerService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<AnswerEntity>> getAllAnswers(){
        return new ResponseEntity<>(answerService.findAll(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<AnswerEntity> saveAnswer(@RequestBody AnswerDto answerDto){
        return new ResponseEntity<>(answerService.save(answerDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerEntity> deleteAnswer(@PathVariable long id){
        return new ResponseEntity<>(answerService.deleteAnswer(id), HttpStatus.NO_CONTENT);
    }
}
