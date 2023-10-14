package com.ua.glebkorobov.backend.util;

import com.ua.glebkorobov.backend.repository.UserProfileRepository;

import java.util.Random;

public class IdGenerationUtils {

    private IdGenerationUtils() {}

    public static long generateAccountId() {
        Random random = new Random();
        return random.nextInt(9000000) + 1000000; // Генерация случайного числа от 1000000 до 9999999
    }

}