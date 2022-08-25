package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping("/image")
    public ApiRepository uploadImage(@RequestParam(value = "file", required = true) MultipartFile file) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository = uploadService.uploadImage(file);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
