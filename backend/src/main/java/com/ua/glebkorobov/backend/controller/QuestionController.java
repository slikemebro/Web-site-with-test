package com.ua.glebkorobov.backend.controller;

import com.ua.glebkorobov.backend.dto.QuestionDto;
import com.ua.glebkorobov.backend.entity.QuestionEntity;
import com.ua.glebkorobov.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionEntity> getQuestionById(@PathVariable("id") long id) {
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<QuestionEntity>> getAllQuestions() {
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> saveQuestion(@RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.save(questionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> addAnswer(@PathVariable("id") long id,
                                                    @RequestParam long answerId) {
        return new ResponseEntity<>(questionService.addAnswer(id, answerId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> deleteQuestion(@PathVariable("id") long id) {
        return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/{answer-id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionEntity> deleteAnswerFromQuestion(@PathVariable("id") long id,
                                                                   @PathVariable("answer-id") long answerId) {
        return new ResponseEntity<>(questionService.deleteAnswerFromQuestion(id, answerId), HttpStatus.OK);
    }
}
