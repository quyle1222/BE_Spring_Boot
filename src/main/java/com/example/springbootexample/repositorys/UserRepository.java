package com.example.springbootexample.repositorys;

import com.example.springbootexample.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user ( username, password ) VALUES ( :username , :password )" , nativeQuery = true)
    void saveUser (@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);
}
