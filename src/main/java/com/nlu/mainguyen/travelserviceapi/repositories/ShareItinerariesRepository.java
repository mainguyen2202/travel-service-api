package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.ShareItineraries;

public interface ShareItinerariesRepository extends JpaRepository< ShareItineraries, Long> {
    
    @Query(value = "SELECT * FROM share_itineraries WHERE users_id = ?1 AND itineraries_id = ?2", nativeQuery = true)
    Optional<ShareItineraries> findByUsersIdAndItinerariesId(long usersId, long itinerariesId);
    
    @Query(value = "SELECT * FROM share_itineraries WHERE users_id = ?1 ", nativeQuery = true)
    List<ShareItineraries> findByUsersId(long usersId);
}
