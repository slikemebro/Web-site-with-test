package com.ua.glebkorobov.backend.authentication;

import com.ua.glebkorobov.backend.config.JwtService;
import com.ua.glebkorobov.backend.entity.Role;
import com.ua.glebkorobov.backend.entity.User;
import com.ua.glebkorobov.backend.entity.UserProfile;
import com.ua.glebkorobov.backend.repository.UserProfileRepository;
import com.ua.glebkorobov.backend.repository.UserRepository;
import com.ua.glebkorobov.backend.util.IdGenerationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        // Создание и сохранение UserProfile с установкой связи с пользователем
        var userProfile = new UserProfile();
        userProfile.setId(user.getId());
        // Генерация уникального 7-значного accountId и проверка на уникальность
        long accountId;
        do {
            accountId = IdGenerationUtils.generateAccountId();
        } while (userProfileRepository.existsByAccountId(accountId));

        userProfile.setAccountId(accountId);
        userProfileRepository.save(userProfile);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}