package com.ua.glebkorobov.backend.service;

import com.ua.glebkorobov.backend.dto.QuestionDto;
import com.ua.glebkorobov.backend.entity.AnswerEntity;
import com.ua.glebkorobov.backend.entity.QuestionEntity;
import com.ua.glebkorobov.backend.exception.NotFoundAnElement;
import com.ua.glebkorobov.backend.exception.NotFoundAnyElements;
import com.ua.glebkorobov.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerService answerService;

    public QuestionEntity findById(long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundAnElement("Question wasn't found"));
    }

    public Collection<QuestionEntity> findAll()  {
        Collection<QuestionEntity> questionEntityCollection = questionRepository.findAll();
        if (questionEntityCollection.isEmpty()){
            throw new NotFoundAnyElements("No questions were found");
        }
        return questionEntityCollection;
    }

    public QuestionEntity save(QuestionDto questionDto) {
        Optional<QuestionEntity> questionEntityOptional =
                questionRepository.findByQuestion(questionDto.getQuestion());
        return questionEntityOptional.orElseGet(() -> questionRepository
                .save(new QuestionEntity(questionDto.getQuestion(),
                questionDto.getPoints())));
    }

    public QuestionEntity addAnswer(long id, long answerId) {
        QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Question wasn't found"));
        AnswerEntity answerEntity = answerService.findById(answerId);
        questionEntity.addAnswer(answerEntity);
        return questionRepository.save(questionEntity);
    }

    public QuestionEntity deleteQuestion(long id) {
        QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Question wasn't found"));
        questionRepository.deleteById(id);
        return questionEntity;
    }

    public QuestionEntity deleteAnswerFromQuestion(long id, long answerId) {
        QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Question wasn't found"));
        AnswerEntity answerEntity = answerService.findById(answerId);
        questionEntity.deleteAnswer(answerEntity);
        return questionRepository.save(questionEntity);
    }
}
