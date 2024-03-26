package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nlu.mainguyen.travelserviceapi.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query.native

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Users findByName(String name);

    @Query(value = "SELECT * FROM users WHERE username = ?1 ", nativeQuery = true)
    Users findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = ?1 ", nativeQuery = true)
    Users findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE (username = ?1 OR email = ?2)", nativeQuery = true)
    Users findOneByUsernameOrEmail(String username, String email);
}
