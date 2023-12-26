package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.repositories.TopicsRepository;

@Service
public class TopicsService {

    private TopicsRepository repository;

    public TopicsService(TopicsRepository repository) {
        this.repository = repository;
    }

    public Iterable<Topics> showAll() {
        return this.repository.findAll();
    }

    public Topics create(Topics input) {
        return this.repository.save(input);
    }

    public Topics getById(Long id) {
        Optional<Topics> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Topics input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
