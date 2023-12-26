package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.repositories.CoordinatesRepository;

@Service
public class CoordinatesService {

    private CoordinatesRepository repository;

    public CoordinatesService(CoordinatesRepository CoordinatesRepository) {
        this.repository = CoordinatesRepository;
    }

    public Iterable<Coordinates> showAll() {
        return this.repository.findAll();
    }

    public Coordinates create(Coordinates input) {
        return this.repository.save(input);
    }

    public Coordinates getById(Long id) {
        Optional<Coordinates> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Coordinates input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
