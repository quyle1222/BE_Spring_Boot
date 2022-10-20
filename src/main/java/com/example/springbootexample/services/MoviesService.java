package com.example.springbootexample.services;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.utils.EnvironmentSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class MoviesService {
    @Autowired
    EnvironmentSystem environmentSystem;

    public ApiRepository getListMoviesTrending() {
        ApiRepository apiRepository = new ApiRepository();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<?> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            String urlTemplate = UriComponentsBuilder.fromHttpUrl(environmentSystem.getUrl_movies_trending())
                    .queryParam("api_key", "a2028d4b6570e5e3c07cbb6b43d39aa2")
                    .queryParam("location", "vietnam")
                    .queryParam("language", "vi-VI")
                    .queryParam("sort_by", "popularity.desc")
                    .queryParam("include_adult", false)
                    .queryParam("include_video", false)
                    .queryParam("page", 1)
                    .queryParam("with_watch_monetization_types", "flatrate")
                    .encode().toUriString();
            ResponseEntity<Object> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                apiRepository.setData(response.getBody());
                apiRepository.setSuccess(true);
                apiRepository.setMessage("Success");
            } else {
                apiRepository.setMessage("Fail");
                apiRepository.setErrorCode(response.getStatusCode().name());
            }
        } catch (Exception error) {
            apiRepository.setMessage(error.getMessage());
        }
        return apiRepository;
    }
}
