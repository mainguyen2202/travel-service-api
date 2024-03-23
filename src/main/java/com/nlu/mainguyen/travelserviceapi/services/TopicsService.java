package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.repositories.TopicsRepository;

@Service
public class TopicsService {
    @Autowired

    private TopicsRepository repository;

    // lấy danh sách
    public List<Topics> getAll() {
        return this.repository.findAll();
    }

    // lấy danh sách theo id
   
    public List<Topics> getAllById(int subTopicsId) {
        // Thực hiện logic lấy danh sách Topics dựa trên subTopicsId
        // Ví dụ:
        return repository.findAllBySubTopicsId(subTopicsId);
    }

    public Topics create(Topics input) {
        return this.repository.save(input);
    }

    public Topics getById(Long id) {
        Optional<Topics> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Topics input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
