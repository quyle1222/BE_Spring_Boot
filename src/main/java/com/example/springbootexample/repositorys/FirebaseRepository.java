package com.example.springbootexample.repositorys;

import com.example.springbootexample.models.Employee;
import com.example.springbootexample.models.Firebase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseRepository extends JpaRepository<Firebase, Long> {
}
