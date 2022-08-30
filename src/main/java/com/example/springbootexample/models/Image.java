package com.example.springbootexample.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long image_id;

    @Column(name = "url")
    private String url;

    @Column(name = "secure_url")
    private String secure_url;

    @Column(name = "resource_type")
    private String resource_type;

    @Column(name = "create_at")
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());

    @Column(name = "update_at")
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecure_url() {
        return secure_url;
    }

    public void setSecure_url(String secure_url) {
        this.secure_url = secure_url;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }
}
