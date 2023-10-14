package com.ua.glebkorobov.backend.repository;

import com.ua.glebkorobov.backend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByAccountId(Long accountId);
    boolean existsByAccountId(long accountId);
}
