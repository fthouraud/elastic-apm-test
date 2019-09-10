package com.ekino.fth.sampleapp.api;

import com.ekino.fth.sampleapp.dto.RandomUserResultsDto;
import com.ekino.fth.sampleapp.dto.RandomUsersApiResponseDto;
import com.ekino.fth.sampleapp.dto.UserDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserApi {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.api.host}")
    private String randomApiUrl;

    @GetMapping
    public List<UserDto> getRandomUsers() {
        return Optional.ofNullable(restTemplate.exchange(randomApiUrl, HttpMethod.GET, null, RandomUsersApiResponseDto.class))
                .map(HttpEntity::getBody)
                .map(results -> results.get(0))
                .map(RandomUserResultsDto::getUsers)
                .orElseGet(Collections::emptyList);
    }

}
