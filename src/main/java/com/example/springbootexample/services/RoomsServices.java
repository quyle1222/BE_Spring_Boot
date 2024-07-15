package com.example.springbootexample.services;

import com.example.springbootexample.models.Rooms;
import com.example.springbootexample.repositorys.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomsServices {
    @Autowired
    private RoomsRepository roomsRepository;

    public void incrementConnectedCount(String roomId) {
        roomsRepository.incrementConnectedCount(roomId);
    }

    public void saveOrUpdateRoom(Rooms room) {
        roomsRepository.save(room);
    }

    public Optional<Rooms> findRoomById(String roomId) {
        return roomsRepository.findById(roomId);
    }
}
