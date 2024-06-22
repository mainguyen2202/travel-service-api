package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    @Query(value = "SELECT * FROM articles WHERE places_id = ?1", nativeQuery = true)
    List<Articles> findAllByPlacesId(long places_id);// B1

    @Query(value = "SELECT * FROM articles WHERE topics_id = ?1", nativeQuery = true)
    List<Articles> findAllByTopicsId(long topic_id);// B1

    @Query(value = "SELECT * FROM articles WHERE places_id = ?1 AND topics_id = ?2", nativeQuery = true)
    List<Articles> findAllBySearch(long places_id, long topic_id);// B1

    @Query(value = "SELECT * FROM articles WHERE places_id in (?1)", nativeQuery = true)
    List<Articles> findSearchMuiltiPlaces(List<Long> places_ids);// B1

    @Query(value = "SELECT * FROM articles WHERE places_id in (?1) AND topics_id = ?2", nativeQuery = true)
    List<Articles> findSearchMuilti(List<Long> places_ids, long topic_id);// B1

    @Query(value = " SELECT * FROM articles s ORDER BY s.create_at DESC", nativeQuery = true)
    List<Articles> findAllDescDate();// B1

    @Query(value = "SELECT * FROM articles  WHERE  name LIKE ?1", nativeQuery = true)
    List<Articles> findAllSearchKeyword(String name);// B1
}
