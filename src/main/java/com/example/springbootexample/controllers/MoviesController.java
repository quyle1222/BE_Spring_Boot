package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MoviesService service;

    @GetMapping("/listTrending")
    ApiRepository getListMoviesTrending() {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = service.getListMoviesTrending();
        } catch (Exception error) {
            apiRepository.setMessage(error.getMessage());
        }
        return apiRepository;
    }
}
