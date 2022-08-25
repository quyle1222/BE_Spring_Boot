package com.example.springbootexample.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.utils.CloudinaryConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UploadService {

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", CloudinaryConfig.cloud_name,
            "api_key", CloudinaryConfig.api_key,
            "api_secret", CloudinaryConfig.api_secret));

    public ApiRepository uploadImage(MultipartFile file) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            apiRepository.setSuccess(true);
            apiRepository.setData(upload);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
