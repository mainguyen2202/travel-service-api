package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;

public interface ArticlesReponsitory extends JpaRepository<Articles, Long> {

    
} 
