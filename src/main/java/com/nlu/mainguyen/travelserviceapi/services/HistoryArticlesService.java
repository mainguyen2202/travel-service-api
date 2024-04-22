package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;
import com.nlu.mainguyen.travelserviceapi.repositories.HistoryArticlesRepository;
@Service
public class HistoryArticlesService {
    @Autowired
    private HistoryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    // lấy danh sách
    public List<HisArticles> getAll() {
        return repository.findAll();
    }


}
