package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;

@Service
public class ItinerariesService {

    private ItinerariesRepository repository;

    public ItinerariesService(ItinerariesRepository repository) {
        this.repository = repository;
    }

    public Iterable<Itineraries> showAll() {
        return this.repository.findAll();
    }

    public Itineraries create(Itineraries input) {
        return this.repository.save(input);
    }

    public Itineraries getById(Long id) {
        Optional<Itineraries> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Itineraries input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
