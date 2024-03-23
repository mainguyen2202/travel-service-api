package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.Topics;

public interface TopicsRepository extends JpaRepository<Topics, Long> {

    @Query(value = "SELECT * FROM topics WHERE sub_topics_id=?1", nativeQuery = true) // theo table name
    List<Topics> findAllBySubTopicsId(int subTopicsId);

} 