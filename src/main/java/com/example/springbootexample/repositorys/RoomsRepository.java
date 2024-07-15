package com.example.springbootexample.repositorys;

import com.example.springbootexample.models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, String> {

    @Override
    List<Rooms> findAllById(Iterable<String> uuids);

    @Override
    Optional<Rooms> findById(String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE Rooms r SET r.connectedCount = r.connectedCount + 1 WHERE r.roomID = :roomId")
    void incrementConnectedCount(String roomId);

    @Override
    <S extends Rooms> S save(S entity);
}