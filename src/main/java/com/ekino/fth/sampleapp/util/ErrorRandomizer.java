package com.ekino.fth.sampleapp.util;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorRandomizer {

    @Value("${randomize.error-rate:5}")
    private int errorRate;

    private final Random random = new Random();

    /**
     * Randomly throw a {@link GeneratedRandomException}.
     */
    public void randomlyThrowException() {
        randomlyThrowException(GeneratedRandomException::new);
    }

    /**
     * Randomly throw a {@link GeneratedRandomException} with given message.
     */
    public void randomlyThrowException(String message) {
        randomlyThrowException(() -> new GeneratedRandomException(message));
    }

    /**
     * Based on the {@link #errorRate}, it throws an exception from the {@code exceptionSupplier}
     * if the random value is equal to 0.
     *
     * @param exceptionSupplier an exception supplier
     */
    public void randomlyThrowException(Supplier<RuntimeException> exceptionSupplier) {
        if (random.nextInt(errorRate) == 0) {
            throw exceptionSupplier.get();
        }
    }

}
