package com.ua.glebkorobov.backend.repository;

import com.ua.glebkorobov.backend.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findByQuestion(String question);
}
