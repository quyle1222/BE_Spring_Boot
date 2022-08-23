package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.CreateRoomDTO;
import com.example.springbootexample.dto.TokenDTO;
import com.example.springbootexample.services.LiveStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livestream")
public class LiveStreamController {
    @Autowired
    LiveStreamService liveStreamService;

    @GetMapping("/generateManagementToken")
    private ApiRepository generateManagementToken() {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = liveStreamService.generateManagementToken();
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }

    @PostMapping("/generateToken")
    private ApiRepository generateToken(@RequestBody TokenDTO data) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = liveStreamService.generateToken(data);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }

    @PostMapping("createRoom")
    private ApiRepository createRoom(@RequestBody CreateRoomDTO data){
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = liveStreamService.createRoom(data);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
