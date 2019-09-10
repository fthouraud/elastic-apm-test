package com.ekino.fth.sampleapp.dto;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    UUID id;
    String firstName;
    String lastName;
    String email;
    String phone;

}
