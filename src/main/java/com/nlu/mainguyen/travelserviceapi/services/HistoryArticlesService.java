package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;
import com.nlu.mainguyen.travelserviceapi.model.HistoryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.HistoryArticlesRepository;

@Service
public class HistoryArticlesService {
    @Autowired
    private HistoryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    private final ArticlesRepository articlesRepository;

    public HistoryArticlesService(HistoryArticlesRepository repository, ArticlesRepository articlesRepository) {
        this.repository = repository;
        this.articlesRepository = articlesRepository;
    }

    // lấy danh sách
    public List<HisArticles> getAll() {
        return repository.findAll();
    }
    
    public HisArticles listByItineraryId(long articles_id) {
        return this.repository.findByArticlesIdCount(articles_id);
    }
    // public List<HisArticles> listByItineraryId(long articles_id) {
       

    //     return this.repository.findAllByArticlesId(articles_id);
    // }

    public ResponseDTO create(HistoryArticlesDTO dto) {
        try {
            HisArticles ent = modelMapper.map(dto, HisArticles.class); // chuyển từ dto sang entity

            Optional<HisArticles> opt = repository.findByArticlesId(dto.getArticles().getId());
            if (opt.isPresent()) {
                return new ResponseDTO(2, "Articles already exist");
            }
            HisArticles created = repository.save(ent);

            HistoryArticlesDTO responseDto = modelMapper.map(created, HistoryArticlesDTO.class);

            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    
    public ResponseDTO update(long articles_id, HistoryArticlesDTO dto) {
        try {
            HisArticles ent = modelMapper.map(dto, HisArticles.class); // chuyển từ dto sang entity
    
            Optional<HisArticles> opt = repository.findByArticlesId(articles_id);
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "Articles not found");
            } else {
                HisArticles get = opt.get();
                get.setCount(dto.getCount());
                HisArticles update = repository.save(get);
                HistoryArticlesDTO responseDto = modelMapper.map(update, HistoryArticlesDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to update: " + e.getMessage());
        }
    }

    
    

}
