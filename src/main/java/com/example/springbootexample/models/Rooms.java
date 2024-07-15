package com.example.springbootexample.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Rooms {
    @Id
    @Column(name = "room_id")
    UUID roomID;

    @Column(name = "connected_count")
    Integer connectedCount = 0;

    @Column(name = "create_at")
    private Timestamp create_at = new Timestamp(System.currentTimeMillis());

    @Column(name = "update_at")
    private Timestamp update_at = new Timestamp(System.currentTimeMillis());
}
