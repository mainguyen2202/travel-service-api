package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nlu.mainguyen.travelserviceapi.entities.Users;



@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


}

