package com.example.springbootexample.dto;

public class CreateRoomDTO {
    String name;
    String description;
    String template;
    Object recording_info;
    String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Object getRecording_info() {
        return recording_info;
    }

    public void setRecording_info(Object recording_info) {
        this.recording_info = recording_info;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
