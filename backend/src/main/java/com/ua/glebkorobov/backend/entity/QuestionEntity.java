package com.ua.glebkorobov.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@Table(name = "questions")
@NoArgsConstructor
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @Column(unique = true)
    private String question;

    private int points;

    @OneToMany
    @JoinColumn(name = "question_id")
    Collection<AnswerEntity> answers = new HashSet<>();

    public QuestionEntity(String question, int points) {
        this.question = question;
        this.points = points;
    }

    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
    }

    public void deleteAnswer(AnswerEntity answer) {
        answers.remove(answer);
    }

}
