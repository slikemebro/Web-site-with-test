package com.ua.glebkorobov.backend.controller;

import com.ua.glebkorobov.backend.entity.UserProfile;
import com.ua.glebkorobov.backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user-profiles")
@RestController
public class UserProfileController {

    public final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<UserProfile> getUserProfileByAccountId(@PathVariable Long accountId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByAccountId(accountId);
        return userProfile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long accountId,
                                                         @RequestBody UserProfile userProfile) {
        Optional<UserProfile> existingUserProfile = userProfileRepository.findByAccountId(accountId);
        if (existingUserProfile.isPresent()) {
            UserProfile userProfileToUpdate = existingUserProfile.get();

            // Обновление данных userProfileToUpdate на основе пришедшего userProfile
            UserProfile updatedUserProfile = userProfileRepository.save(userProfileToUpdate);
            return ResponseEntity.ok(updatedUserProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
