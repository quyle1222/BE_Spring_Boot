package com.example.springbootexample.repositorys;

import com.example.springbootexample.models.Rooms;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, UUID> {
    @Override
    List<Rooms> findAllById(Iterable<UUID> uuids);

    @Override
    <S extends Rooms, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Modifying
    @Transactional
    @Query("UPDATE Rooms r SET r.connectedCount = r.connectedCount + 1 WHERE r.roomID = :roomId")
    void incrementConnectedCount(UUID roomId);

    @Override
    <S extends Rooms> S save(S entity);

    @Override
    Optional<Rooms> findById(UUID uuid);

    @Query("SELECT r FROM Rooms r WHERE r.roomID = :roomId")
    Optional<Rooms> findByRoomId(UUID roomId);
}