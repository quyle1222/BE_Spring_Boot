package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.TokenDTO;
import com.example.springbootexample.dto.UserDTO;
import com.example.springbootexample.jwt.JwtTokenProvider;
import com.example.springbootexample.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ApiRepository authenticateUser(@RequestBody UserDTO loginRequest) {
        ApiRepository repository = new ApiRepository();
        try {
            repository = authService.login(loginRequest);
        } catch (Exception error) {
            repository.setMessage(error.getMessage());
        }
        return repository;
    }

    @PostMapping("/createUser")
    private ApiRepository createUser(@RequestBody UserDTO user) {
        ApiRepository repository = new ApiRepository();
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null || user.getUsername().trim().isEmpty() ||user.getPassword().trim().isEmpty()) {
                repository.setMessage("User name and password is require");
                repository.setSuccess(false);
                return repository;
            }
            repository = authService.createUser(user);
        } catch (Exception error) {
            repository.setMessage(error.getMessage());
        }
        return repository;
    }
}
