package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository repository;

    // lấy danh sách
    public List<Articles> getAll() {
        return repository.findAll();
    }

    // lấy danh sách theo idPlaces, idTopics
    public List<Articles> listAllBySearch(long places_id, long topics_id) {
        // places_id có giá trị và topics_id = 0
        // places_id = 0 và topics_id có giá trị
        // tất cả điều có giá trị
        if (places_id != 0 && topics_id == 0) {
            return this.repository.findAllByPlacesId(places_id);
        } else if (places_id == 0 && topics_id != 0) {
            return this.repository.findAllByTopicsId(topics_id);
        } else  {
            return this.repository.findAllBySearch(places_id, topics_id);
        }
        // giá trị mặc định
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
    public Articles getByArticlesId(Long topicId) {
        Optional<Articles> items = this.repository.findById(topicId);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }
}
