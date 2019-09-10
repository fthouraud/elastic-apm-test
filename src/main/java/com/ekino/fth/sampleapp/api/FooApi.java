package com.ekino.fth.sampleapp.api;

import com.ekino.fth.sampleapp.dto.FooDto;
import com.ekino.fth.sampleapp.service.FooService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/foos")
@RequiredArgsConstructor
public class FooApi {

    private final FooService fooService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FooDto> getAllFoos() {
        log.info("Receiving an API call: getAllFoos");

        generateLatency();

        return fooService.getAllFoos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FooDto getFooById(@PathVariable UUID id) {
        log.info("Receiving an API call: getFooById");

        generateLatency();

        return fooService.getFooById(id);
    }

    private void generateLatency() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(1, 5001));
        } catch (InterruptedException e) {
            log.error("Error on thread sleep", e);
        }
    }

}
