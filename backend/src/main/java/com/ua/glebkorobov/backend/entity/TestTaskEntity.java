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
@Table(name = "test_task")
@Data
@NoArgsConstructor
public class TestTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String nameOfTest;

    private int maxPoints;

    private int timeForComplete;

    @OneToMany
    @JoinColumn(name = "test_task_id")
    Collection<QuestionEntity> questions = new HashSet<>();

    public TestTaskEntity(String nameOfTest, int timeForComplete) {
        this.nameOfTest = nameOfTest;
        this.timeForComplete = timeForComplete;
    }

    public int getMaxPoints() {
        for (QuestionEntity question : questions) {
            maxPoints += question.getPoints();
        }
        return maxPoints;
    }

    public void addQuestion(QuestionEntity questionEntity) {
        questions.add(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        questions.remove(questionEntity);
    }
}
