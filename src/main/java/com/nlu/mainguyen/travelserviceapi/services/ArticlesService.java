package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;

@Service
public class ArticlesService {

    private ArticlesRepository repository;

    public ArticlesService(ArticlesRepository repository) {
        this.repository = repository;
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


    // lấy danh sách theo địa điểm placeid

    // lấy danh sách theo địa điểm topic
    public Articles getByTopicsId(Long topicId) {
        Optional<Articles> items = this.repository.findById(topicId);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }
}
