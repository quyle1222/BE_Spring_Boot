package com.example.springbootexample.controllers;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
        try {
            byte[] image = uploadService.getImage(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            return null;
        }
    }
}
