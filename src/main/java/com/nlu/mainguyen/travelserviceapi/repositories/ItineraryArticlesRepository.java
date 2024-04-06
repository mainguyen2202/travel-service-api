package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;

public interface ItineraryArticlesRepository extends JpaRepository<ItineraryArticles, Long> {

  // @Query(value = "SELECT * FROM db_travels.itinerary_articles ia JOIN ItineraryArticles i ON ia.ItineraryArticles_id = i.id JOIN articles a ON ia.articles_id = a.id WHERE i.itineraries_id = ?1", nativeQuery = true)
  // List<ItineraryArticles> findAllByIdItinerary(long itineraries_id);

 
  @Query(value = " SELECT * FROM db_travels.itinerary_articles  where itineraries_id=?1", nativeQuery = true)
  List<ItineraryArticles> findAllByIdItinerary(long itineraries_id);
}