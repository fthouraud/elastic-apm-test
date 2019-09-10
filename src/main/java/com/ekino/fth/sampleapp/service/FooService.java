package com.ekino.fth.sampleapp.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import com.ekino.fth.sampleapp.domain.Foo;
import com.ekino.fth.sampleapp.dto.FooDto;
import com.ekino.fth.sampleapp.repository.FooRepository;
import com.ekino.fth.sampleapp.util.ErrorRandomizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FooService {

    private final FooRepository fooRepository;
    private final ErrorRandomizer errorRandomizer;

    public List<FooDto> getAllFoos() {
        errorRandomizer.randomlyThrowException("Oups! An error occured before getting all foos :o");

        List<FooDto> fooDtos = StreamSupport.stream(fooRepository.findAll().spliterator(), false)
                .map(fooToFooDto())
                .collect(Collectors.toList());

        log.debug(fooDtos.size() + " foo(s) found!");

        return fooDtos;
    }

    public FooDto getFooById(UUID id) {
        errorRandomizer.randomlyThrowException("Oups! Can't search for foo " + id + " right now :s");

        return fooRepository.findById(id)
                .map(fooToFooDto())
                .orElseThrow(() -> new EntityNotFoundException("There's no foo with id: " + id));
    }

    private Function<Foo, FooDto> fooToFooDto() {
        return foo -> FooDto.builder().id(foo.getId()).bar(foo.getBar()).build();
    }

}
