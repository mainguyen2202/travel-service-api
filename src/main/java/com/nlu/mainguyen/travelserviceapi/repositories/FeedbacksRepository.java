package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;

public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {

    
}