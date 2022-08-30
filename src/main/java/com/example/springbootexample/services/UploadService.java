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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

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
            apiRepository.setData(imageEntity);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;

    }

    public byte[] getImage(int id) {
        try {
            Optional<Image> image = imageRepository.findById(Long.valueOf(id));
            if (image.isEmpty() == false) {
                String urlImage = image.get().getUrl();
                URL url = new URL(urlImage);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5 * 1000);
                InputStream inStream = conn.getInputStream();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[10240];
                int len = 0;
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                inStream.close();
                byte[] btImg = outStream.toByteArray();
                return btImg;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
