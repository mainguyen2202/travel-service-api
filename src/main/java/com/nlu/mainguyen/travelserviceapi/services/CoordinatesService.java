package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.repositories.CoordinatesRepository;

@Service
public class CoordinatesService {

    @Autowired
    private CoordinatesRepository repository;

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

    public List<Coordinates> getAll() {
        return this.repository.findAll();
    }
}
