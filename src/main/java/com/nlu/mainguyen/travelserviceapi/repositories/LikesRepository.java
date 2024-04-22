package com.nlu.mainguyen.travelserviceapi.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "SELECT * FROM likes WHERE users_id = ?1", nativeQuery = true)
    List<Likes> findAllByUserId(long users_id);// B1
    
}