package com.ekino.fth.sampleapp.util;

/**
 * Should be thrown when a random error is expected.
 */
public class GeneratedRandomException extends RuntimeException {
    GeneratedRandomException() {
        this("This error has been randomly thrown.");
    }

    GeneratedRandomException(String message) {
        super(message);
    }
}
