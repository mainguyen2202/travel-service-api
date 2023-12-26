package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;
import com.nlu.mainguyen.travelserviceapi.repositories.FeedbacksRepository;

@Service
public class FeedbacksService {

    private FeedbacksRepository repository;

    public FeedbacksService(FeedbacksRepository FeedbacksRepository) {
        this.repository = FeedbacksRepository;
    }

    public Iterable<Feedbacks> showAll() {
        return this.repository.findAll();
    }

    public Feedbacks create(Feedbacks input) {
        return this.repository.save(input);
    }

    public Feedbacks getById(Long id) {
        Optional<Feedbacks> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Feedbacks input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
