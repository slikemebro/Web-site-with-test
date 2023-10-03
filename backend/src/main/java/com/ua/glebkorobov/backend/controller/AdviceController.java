package com.ua.glebkorobov.backend.controller;

import com.ua.glebkorobov.backend.exception.NotFoundAnElement;
import com.ua.glebkorobov.backend.exception.NotFoundAnyElements;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundEntity(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundAnyElements.class)
    public ResponseEntity<String> handleNotFoundCollectionOfEntities(NotFoundAnyElements e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundAnElement.class)
    public ResponseEntity<String> handleNotFoundEntities(NotFoundAnElement e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
