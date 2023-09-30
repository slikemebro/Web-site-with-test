package com.ua.glebkorobov.backend.service;

import com.ua.glebkorobov.backend.dto.TestTaskDto;
import com.ua.glebkorobov.backend.entity.QuestionEntity;
import com.ua.glebkorobov.backend.entity.TestTaskEntity;
import com.ua.glebkorobov.backend.exception.NotFoundAnElement;
import com.ua.glebkorobov.backend.exception.NotFoundAnyElements;
import com.ua.glebkorobov.backend.repository.TestTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestTaskService {

    private final TestTaskRepository testTaskRepository;

    private final QuestionService questionService;


    public TestTaskEntity findById(long id) {
        return testTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Test Task wasn't found"));
    }

    public Collection<TestTaskEntity> findAll() {
        Collection<TestTaskEntity> testTaskEntityCollection = testTaskRepository.findAll();
        if (testTaskEntityCollection.isEmpty()){
            throw new NotFoundAnyElements("No test tasks were found");
        }
        return testTaskEntityCollection;
    }

    public TestTaskEntity save(TestTaskDto testTaskDto) {
        Optional<TestTaskEntity> testTaskEntityOptional =
                testTaskRepository.findByNameOfTest(testTaskDto.getNameOfTest());
        return testTaskEntityOptional.orElseGet(() -> testTaskRepository.save(new TestTaskEntity(testTaskDto.getNameOfTest(),
                testTaskDto.getTimeForComplete())));
    }

    public TestTaskEntity addQuestion(long id, long questionId) {
        TestTaskEntity testTaskEntity = testTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Test Task wasn't found"));
        QuestionEntity questionEntity = questionService.findById(questionId);
        testTaskEntity.addQuestion(questionEntity);
        return testTaskRepository.save(testTaskEntity);
    }

    public TestTaskEntity deleteTestTask(long id) {
        TestTaskEntity testTaskEntity = testTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Test Task wasn't found"));
        testTaskRepository.deleteById(id);
        return testTaskEntity;
    }

    public TestTaskEntity deleteQuestionFromTestTask(long id, long questionId) {
        TestTaskEntity testTaskEntity = testTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Test Task wasn't found"));
        QuestionEntity questionEntity = questionService.findById(questionId);
        testTaskEntity.deleteQuestion(questionEntity);
        return testTaskRepository.save(testTaskEntity);
    }
}
