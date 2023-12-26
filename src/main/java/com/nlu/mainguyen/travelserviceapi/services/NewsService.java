package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.News;
import com.nlu.mainguyen.travelserviceapi.repositories.NewsRepository;

@Service
public class NewsService {

    private NewsRepository repository;

    public NewsService(NewsRepository NewsRepository) {
        this.repository = NewsRepository;
    }

    public Iterable<News> showAll() {
        return this.repository.findAll();
    }

    public News create(News input) {
        return this.repository.save(input);
    }

    public News getById(Long id) {
        Optional<News> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(News input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
