package com.nlu.mainguyen.travelserviceapi.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);
}
