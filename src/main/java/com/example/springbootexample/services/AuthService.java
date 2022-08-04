package com.example.springbootexample.services;

import com.example.springbootexample.models.ApiRepository;
import com.example.springbootexample.models.User;
import com.example.springbootexample.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public ApiRepository createUser(User user) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            userRepository.saveUser(user.getUsername(),user.getPassword());
            apiRepository.setSuccess(true);
            apiRepository.setData(null);
            apiRepository.setMessage("Create user success");
        } catch (Exception error) {
            apiRepository.setMessage(error.getMessage());
        }
        return apiRepository;
    }

}
