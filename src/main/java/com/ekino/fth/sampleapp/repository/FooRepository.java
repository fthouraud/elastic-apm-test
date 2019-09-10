package com.ekino.fth.sampleapp.repository;

import java.util.UUID;

import com.ekino.fth.sampleapp.domain.Foo;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Foo, UUID> {
}
