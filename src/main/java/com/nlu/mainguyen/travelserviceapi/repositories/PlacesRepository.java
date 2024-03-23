package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.Places;

public interface PlacesRepository extends JpaRepository<Places, Long> {

    // SELECT * FROM places WHERE name LIKE '%keyword%';
    // @Query("select u from Places u where u.name like %?1")// theo entity model
    @Query(value = "SELECT * FROM places WHERE name like %?1", nativeQuery = true) // theo table name
    List<Places> findByName(String name);


  
}
