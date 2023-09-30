package com.ua.glebkorobov.backend.exception;

public class NotFoundAnyElements extends RuntimeException {
    public NotFoundAnyElements(String message) {
        super(message);
    }
}
