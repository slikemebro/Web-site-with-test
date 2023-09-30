package com.ua.glebkorobov.backend.repository;

import com.ua.glebkorobov.backend.entity.TestTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestTaskRepository extends JpaRepository<TestTaskEntity, Long> {

    Optional<TestTaskEntity> findByNameOfTest(String name);
}
