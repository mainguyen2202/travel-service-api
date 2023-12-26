package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;

@Service
public class ArticlesService {

    private ArticlesRepository repository;

    public ArticlesService(ArticlesRepository ArticlesRepository) {
        this.repository = ArticlesRepository;
    }

    public Iterable<Articles> showAll() {
        return this.repository.findAll();
    }

    public Articles create(Articles input) {
        return this.repository.save(input);
    }

    public Articles getById(Long id) {
        Optional<Articles> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Articles input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
