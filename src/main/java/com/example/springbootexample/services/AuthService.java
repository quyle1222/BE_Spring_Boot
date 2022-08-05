package com.example.springbootexample.services;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.UserDTO;
import com.example.springbootexample.jwt.WebSecurityConfig;
import com.example.springbootexample.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommonService commonService;

    @Autowired
    WebSecurityConfig webSecurityConfig;
    public ApiRepository createUser(UserDTO user) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            userRepository.saveUser(user.getUsername(),webSecurityConfig.passwordEncoder().encode(user.getPassword()).toString());
            apiRepository.setSuccess(true);
            apiRepository.setData(null);
            apiRepository.setMessage("Create user success");
        } catch (Exception error) {
            apiRepository.setMessage(error.getMessage());
        }
        return apiRepository;
    }
}
