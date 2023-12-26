package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    
}