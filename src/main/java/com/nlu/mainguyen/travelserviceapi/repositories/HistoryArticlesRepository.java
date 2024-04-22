package com.nlu.mainguyen.travelserviceapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;


public interface HistoryArticlesRepository extends JpaRepository<HisArticles, Long> {


    
}