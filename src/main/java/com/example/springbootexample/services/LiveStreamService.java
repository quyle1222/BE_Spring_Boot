package com.example.springbootexample.services;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.CreateRoomDTO;
import com.example.springbootexample.dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;
import java.util.*;

@Service
public class LiveStreamService {

    private String generateTokenPrivateJoinRoom(TokenDTO data) {
        String app_access_key = LiveStreamConfig.app_access_key;
        String app_secret = LiveStreamConfig.app_secret;
        Map<String, Object> payload = new HashMap<>();
        payload.put("access_key", app_access_key);
        payload.put("room_id", data.getRoom_id());
        payload.put("user_id", data.getUser_id());
        payload.put("role", data.getRole());
        payload.put("type", "app");
        payload.put("version", 2);
        return Jwts.builder().setClaims(payload).setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
                .setIssuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() - 60000)))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, app_secret.getBytes()).compact();
    }

    private String generateManagementTokenPrivate() {
        String app_access_key = LiveStreamConfig.app_access_key;
        String app_secret = LiveStreamConfig.app_secret;
        Map<String, Object> payload = new HashMap<>();
        payload.put("access_key", app_access_key);
        payload.put("type", "management");
        payload.put("version", 2);
        return Jwts.builder().setClaims(payload).setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
                .setIssuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() - 60000)))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, app_secret.getBytes()).compact();
    }

    public ApiRepository generateManagementToken() {
        ApiRepository apiRepository = new ApiRepository();
        try {
            String app_access_key = LiveStreamConfig.app_access_key;
            String app_secret = LiveStreamConfig.app_secret;
            Map<String, Object> payload = new HashMap<>();
            payload.put("access_key", app_access_key);
            payload.put("type", "management");
            payload.put("version", 2);
            String token = Jwts.builder().setClaims(payload).setId(UUID.randomUUID().toString())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
                    .setIssuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() - 60000)))
                    .setNotBefore(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256, app_secret.getBytes()).compact();
            apiRepository.setSuccess(true);
            apiRepository.setMessage("Generate token success");
            apiRepository.setData(token);
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }

    public ApiRepository generateToken(TokenDTO data) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            apiRepository.setSuccess(true);
            apiRepository.setMessage("Generate token success");
            apiRepository.setData(generateTokenPrivateJoinRoom(data));
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }

    public ApiRepository createRoom(CreateRoomDTO data) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            JSONObject request = new JSONObject();
            request.put("name", data.getName());
            request.put("description", data.getDescription());
            request.put("template", data.getTemplate());
            request.put("recording_info", data.getRecording_info());
            request.put("region", data.getRegion());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> response;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("Authorization", "Bearer " + generateManagementTokenPrivate());
            HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
            response = restTemplate.exchange(LiveStreamConfig.url_create_room, HttpMethod.POST, entity, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                apiRepository.setData(response.getBody());
                apiRepository.setSuccess(true);
                apiRepository.setMessage("Create room success");
            } else {
                apiRepository.setMessage("Create fail in 100ms");
                apiRepository.setErrorCode(response.getStatusCode().name());
            }
        } catch (Exception e) {
            apiRepository.setMessage(e.getMessage());
        }
        return apiRepository;
    }
}
