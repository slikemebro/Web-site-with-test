package com.ua.glebkorobov.backend.service;

import com.ua.glebkorobov.backend.dto.AnswerDto;
import com.ua.glebkorobov.backend.entity.AnswerEntity;
import com.ua.glebkorobov.backend.exception.NotFoundAnElement;
import com.ua.glebkorobov.backend.exception.NotFoundAnyElements;
import com.ua.glebkorobov.backend.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerEntity findById(long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Answer wasn't found"));
    }

    public Collection<AnswerEntity> findAll() {
        Collection<AnswerEntity> answerEntityCollection = answerRepository.findAll();
        if (answerEntityCollection.isEmpty()) {
            throw new NotFoundAnyElements("No answers were found");
        }
        return answerEntityCollection;
    }

    public AnswerEntity save(AnswerDto answerDto) {
        Optional<AnswerEntity> answerEntityOptional =
                answerRepository.findByAnswer(answerDto.getAnswer());
        return answerEntityOptional.orElseGet(() -> answerRepository.save(new AnswerEntity(answerDto.getAnswer(),
                answerDto.isCorrect())));
    }

    public AnswerEntity deleteAnswer(long id) {
        AnswerEntity answerEntity = answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundAnElement("Answer wasn't found"));
        answerRepository.delete(answerEntity);
        return answerEntity;
    }
}
