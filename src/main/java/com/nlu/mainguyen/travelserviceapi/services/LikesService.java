package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.repositories.LikesRepository;

@Service
public class LikesService {

    private LikesRepository repository;

    public LikesService(LikesRepository repository) {
        this.repository = repository;
    }

    public Iterable<Likes> showAll() {
        return this.repository.findAll();
    }

    public Likes create(Likes input) {
        return this.repository.save(input);
    }

    public Likes getById(Long id) {
        Optional<Likes> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Likes input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
