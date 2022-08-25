package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.services.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football")
public class FootballController {
    @Autowired
    FootballService footballService;

    @GetMapping("/getListMatch")
    public ApiRepository getListMatch(@RequestParam("name") String club) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = footballService.getListMatch(club);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }

    ;
}
