package com.ekino.fth.sampleapp.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Foo {

    @Id
    @GeneratedValue
    private UUID id;

    private String bar;

}
