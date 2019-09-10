package com.ekino.fth.sampleapp.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FooDto {

    private UUID id;
    private String bar;

}
