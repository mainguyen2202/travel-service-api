package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.repositories.CoordinatesRepository;

public class CoordinatesService {
    private CoordinatesRepository coordinatesRepository;

    public CoordinatesService(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    public Iterable<Coordinates> showAllCoordinates() {
        return coordinatesRepository.findAll();
    }

    public Coordinates createCoordinates(Coordinates newCoordinates) {
        return coordinatesRepository.save(newCoordinates);
    }

    public Coordinates getByIdCoordinates(Long id) {
        Optional<Coordinates> aCoordinates = coordinatesRepository.findById(id);
        if (aCoordinates.isPresent()) {
            return aCoordinates.get();
        }
        return null;
    }

    public void updateCoordinates(Coordinates newCoordinates) {
        coordinatesRepository.save(newCoordinates);
    }

    public void deleteByID(Long id) {
        coordinatesRepository.deleteById(id);
    }
}
