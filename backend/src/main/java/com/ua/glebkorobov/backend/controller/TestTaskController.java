package com.ua.glebkorobov.backend.controller;

import com.ua.glebkorobov.backend.dto.TestTaskDto;
import com.ua.glebkorobov.backend.entity.TestTaskEntity;
import com.ua.glebkorobov.backend.service.TestTaskService;
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
@RequestMapping("/api/test-tasks")
@RequiredArgsConstructor
public class TestTaskController {

    private final TestTaskService testTaskService;


    @GetMapping("/{id}")
    public ResponseEntity<TestTaskEntity> getTestTaskById(@PathVariable("id") long id) {
        return new ResponseEntity<>(testTaskService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<TestTaskEntity>> getAllTestTasks() {
        return new ResponseEntity<>(testTaskService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TestTaskEntity> saveTestTask(@RequestBody TestTaskDto testTaskDto) {
        return new ResponseEntity<>(testTaskService.save(testTaskDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TestTaskEntity> addQuestion(@PathVariable("id") long id, @RequestParam long questionId) {
        return new ResponseEntity<>(testTaskService.addQuestion(id, questionId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TestTaskEntity> deleteTestTask(@PathVariable("id") long id){
        return new ResponseEntity<>(testTaskService.deleteTestTask(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/{question-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TestTaskEntity> deleteQuestionFromTestTask(@PathVariable("id") long id,
                                                                     @PathVariable("question-id") long questionId){
        return new ResponseEntity<>(testTaskService.deleteQuestionFromTestTask(id, questionId), HttpStatus.OK);
    }

}
