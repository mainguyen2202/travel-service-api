package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;


public interface HistoryArticlesRepository extends JpaRepository<HisArticles, Long> {

    @Query(value = "SELECT * FROM his_articles WHERE  articles_id = ?1", nativeQuery = true)
    Optional<HisArticles> findByArticlesId( long articles_id);

    @Query(value = "SELECT * FROM his_articles WHERE  articles_id = ?1", nativeQuery = true)
    List<HisArticles> findAllByArticlesId( long articles_id);

    
    @Query(value = "SELECT * FROM his_articles WHERE  articles_id = ?1", nativeQuery = true)
    HisArticles findByArticlesIdCount( long articles_id);
    
}