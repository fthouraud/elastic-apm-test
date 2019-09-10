package com.ekino.fth.sampleapp.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RandomUsersApiResponseDto {

    @Delegate
    List<RandomUserResultsDto> results;

}
