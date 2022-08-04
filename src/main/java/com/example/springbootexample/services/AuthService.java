package com.example.springbootexample.services;

import com.example.springbootexample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootexample.dto.UserDTO;
import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.repositorys.UserRepository;

@Service
public class AuthService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommonService commonService;

    public ApiRepository createUser(UserDTO user) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            UserDTO data = commonService.convertDTOUser(new User());
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
