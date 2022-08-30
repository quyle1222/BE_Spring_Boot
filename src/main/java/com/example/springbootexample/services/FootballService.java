package com.example.springbootexample.services;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.utils.ApiConstant;
import com.example.springbootexample.utils.EnvironmentSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class FootballService {

    @Autowired
    EnvironmentSystem environmentSystem;

    public ApiRepository getListMatch(String club) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<?> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            String urlTemplate = UriComponentsBuilder.fromHttpUrl(ApiConstant.getListMatchClub)
                    .queryParam("q", club.isEmpty() == false && club.trim().isEmpty() != false ? club : "manchester united")
                    .queryParam("api_key", environmentSystem.getToken_football_api())
                    .queryParam("location", "vietnam")
                    .encode().toUriString();
            ResponseEntity<Object> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                apiRepository.setData(response.getBody());
                apiRepository.setSuccess(true);
            } else {
                apiRepository.setErrorCode(response.getStatusCode().name());
            }
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
