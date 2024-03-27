package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;

public interface ItinerariesRepository extends JpaRepository<Itineraries, Long> {

    
   

      @Query(value = "SELECT * FROM itineraries WHERE users_id = ?1", nativeQuery = true)
    List<Itineraries> findAllByUsersId(long users_id);// B1

}