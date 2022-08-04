package com.example.springbootexample.services;

import com.example.springbootexample.dto.UserDTO;
import com.example.springbootexample.models.User;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    public UserDTO convertDTOUser (User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userDTO.getUsername());
        userDTO.setPassword(userDTO.getPassword());
        return userDTO;
    }
}
