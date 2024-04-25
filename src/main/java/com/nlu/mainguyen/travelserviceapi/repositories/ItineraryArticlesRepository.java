package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;

public interface ItineraryArticlesRepository extends JpaRepository<ItineraryArticles, Long> {

  // @Query(value = "SELECT * FROM.itinerary_articles ia JOIN ItineraryArticles i
  // ON ia.ItineraryArticles_id = i.id JOIN articles a ON ia.articles_id = a.id
  // WHERE i.itineraries_id = ?1", nativeQuery = true)
  // List<ItineraryArticles> findAllByIdItinerary(long itineraries_id);

  @Query(value = " SELECT * FROM itinerary_articles  WHERE itineraries_id=?1 order by date_start", nativeQuery = true)
  List<ItineraryArticles> findAllByIdItinerary(long itineraries_id);

  @Query(value = " SELECT * FROM itinerary_articles  WHERE itineraries_id=?1 and date_start=?2", nativeQuery = true)
  List<ItineraryArticles> findAllByDateStart(long itineraries_id, String date_start);//yyyy-MM-dd

  
  @Query(value = "SELECT * FROM itinerary_articles WHERE itineraries_id = ?1 AND articles_id = ?2", nativeQuery = true)
  Optional<ItineraryArticles> findByItinerariesIdAndArticlesId(long itinerariesId, long articlesId);
}