package com.example.springbootexample.utils;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Component
public class EnvironmentSystem {

    @Value("${cloud_name}")
    private String cloud_name;
    @Value("${api_key}")
    private String api_key;
    @Value("${api_secret}")
    private String api_secret;
    @Value("${token_football_api}")
    private String token_football_api;
    @Value("${token_endpoint}")
    private String token_endpoint;
    @Value("${app_secret}")
    private String app_secret;
    @Value("${app_access_key}")
    private String app_access_key;
    @Value("${url_create_room}")
    private String url_create_room;


    public String getCloud_name() {
        return cloud_name;
    }

    public void setCloud_name(String cloud_name) {
        this.cloud_name = cloud_name;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getToken_football_api() {
        return token_football_api;
    }

    public void setToken_football_api(String token_football_api) {
        this.token_football_api = token_football_api;
    }

    public String getToken_endpoint() {
        return token_endpoint;
    }

    public void setToken_endpoint(String token_endpoint) {
        this.token_endpoint = token_endpoint;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getApp_access_key() {
        return app_access_key;
    }

    public void setApp_access_key(String app_access_key) {
        this.app_access_key = app_access_key;
    }

    public String getUrl_create_room() {
        return url_create_room;
    }

    public void setUrl_create_room(String url_create_room) {
        this.url_create_room = url_create_room;
    }


}
