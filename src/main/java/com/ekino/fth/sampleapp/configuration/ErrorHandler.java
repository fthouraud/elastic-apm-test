package com.ekino.fth.sampleapp.configuration;

import com.ekino.fth.sampleapp.util.GeneratedRandomException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> anyException(GeneratedRandomException ex) {
        log.error("That was unexpected!", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Error(HttpStatus.INTERNAL_SERVER_ERROR, "error.unexpected", ex.getMessage()));
    }

    @ExceptionHandler(GeneratedRandomException.class)
    public ResponseEntity<Error> randomException(GeneratedRandomException ex) {
        log.error("That's bad luck!", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new Error(HttpStatus.UNPROCESSABLE_ENTITY, "error.bad-luck", ex.getMessage()));
    }

    @Getter
    @RequiredArgsConstructor
    private static class Error {
        private final HttpStatus status;
        private final String code;
        private final String description;
    }

}
