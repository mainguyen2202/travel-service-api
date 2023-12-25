package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    
}
