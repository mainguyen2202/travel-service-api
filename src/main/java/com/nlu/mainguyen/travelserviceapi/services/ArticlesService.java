package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    // lấy danh sách
    public List<Articles> getAll() {
        return repository.findAll();
    }
     // lấy danh sách
     public List<Articles> getAllDescDate() {
        return repository.findAllDescDate();
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
        } else if (places_id == 0 && topics_id == 0) {
            return this.repository.findAll();
        } else  {
            return this.repository.findAllBySearch(places_id, topics_id);
        }
        // giá trị mặc định
    }

    public List<Articles>  detailBySearchName(String name) {
        if (name != null) {
            return repository.findAllSearchKeyword(name);
        }
        // giá trị mặc định
        return null;
    }
   
   

    public ResponseDTO create(ArticlesDTO dto) {
         try {
            Articles entity = modelMapper.map(dto, Articles.class);

            Articles createdUser = this.repository.save(entity);// lưu thành công và có id định danh

            // Tạo đối tượng chứa thông tin người dùng và thông báo thành công
            // convert entity to DTO
            ArticlesDTO response = modelMapper.map(createdUser, ArticlesDTO.class);

            return new ResponseDTO(1, " Created successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
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
