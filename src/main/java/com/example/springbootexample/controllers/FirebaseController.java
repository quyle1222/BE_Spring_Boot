package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class FirebaseController {

    @PostMapping("updateToken")
    public ApiRepository updateTokenUser (@RequestBody String token){
        return null;
    };
}
