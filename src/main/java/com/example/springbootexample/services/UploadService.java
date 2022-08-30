package com.example.springbootexample.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.CloudinaryUpload;
import com.example.springbootexample.models.Image;
import com.example.springbootexample.repositorys.ImageRepository;
import com.example.springbootexample.utils.EnvironmentSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UploadService {
    @Autowired
    private Environment env;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private EnvironmentSystem environmentSystem;

    public ApiRepository uploadImage(MultipartFile file) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", environmentSystem.getCloud_name(),
                    "api_key", environmentSystem.getApi_key(),
                    "api_secret", environmentSystem.getApi_secret()));
            ObjectMapper mapper = new ObjectMapper();
            Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            CloudinaryUpload data = mapper.convertValue(upload, CloudinaryUpload.class);
            Image imageEntity = new Image();
            imageEntity.setSecure_url(data.getSecure_url());
            imageEntity.setUrl(data.getUrl());
            imageEntity.setResource_type(data.getResource_type());
            imageRepository.save(imageEntity);
            apiRepository.setSuccess(true);
            apiRepository.setMessage(environmentSystem.getApi_key());
            apiRepository.setData(imageEntity);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
