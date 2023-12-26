package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.repositories.PlacesRepository;

@Service
public class PlacesService {

    private PlacesRepository repository;

    public PlacesService(PlacesRepository PlacesRepository) {
        this.repository = PlacesRepository;
    }

    public Iterable<Places> showAll() {
        return this.repository.findAll();
    }

    public Places create(Places input) {
        return this.repository.save(input);
    }

    public Places getById(Long id) {
        Optional<Places> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Places input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
