package com.nlu.mainguyen.travelserviceapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.PlacesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.TopicsRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository repository;

    @Autowired
    private PlacesRepository placesRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private UsersRepository userRepository;

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
    public List<Articles> listAllBySearch(long places_id, long topics_id, String places_ids) {
        // 1,2,8,10 -> String -> mảng long
        List<Long> arrPlaceIds = new ArrayList<Long>();
        if (!StringUtils.isEmpty(places_ids)) {
            String[] arrOfStr = places_ids.split(",");
            for (String id : arrOfStr) {
                arrPlaceIds.add(Long.parseLong(id, 10));
            }
        }

        if (arrPlaceIds.size() > 0 && topics_id == 0) {
            return this.repository.findSearchMuiltiPlaces(arrPlaceIds);
        } else if (arrPlaceIds.size() > 0 && topics_id != 0) {
            return this.repository.findSearchMuilti(arrPlaceIds, topics_id);
        }
        // places_id có giá trị và topics_id = 0
        // places_id = 0 và topics_id có giá trị
        // tất cả điều có giá trị
        else if (places_id != 0 && topics_id == 0) {
            return this.repository.findAllByPlacesId(places_id);
        } else if (places_id == 0 && topics_id != 0) {
            return this.repository.findAllByTopicsId(topics_id);
        } else if (places_id == 0 && topics_id == 0) {
            return this.repository.findAll();
        } else {
            return this.repository.findAllBySearch(places_id, topics_id);
        }
        // giá trị mặc định
    }

    public List<Articles> detailBySearchName(String name) {
        if (name != null && !name.isEmpty()) {
            String search = "%" + name + "%";
            return repository.findAllSearchKeyword(search);
        }
        // giá trị mặc định
        return null;
    }

    public ResponseDTO create(ArticlesDTO dto) {
        try {
            Articles entity = modelMapper.map(dto, Articles.class);

            Optional<Places> optPlace = placesRepository.findById(dto.getPlaces().getId());
            if (optPlace.isEmpty()) {
                return new ResponseDTO(2, "Places not found");
            }
            Places place = optPlace.get();
            entity.setPlaces(place);

            Optional<Topics> optTopic = topicsRepository.findById(dto.getTopics().getId());
            if (optTopic.isEmpty()) {
                return new ResponseDTO(2, "Topics not found");
            }
            Topics topic = optTopic.get();
            entity.setTopics(topic);

            Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            entity.setUsers(user);

            Articles create = this.repository.save(entity);// lưu thành công và có id định danh

            // Tạo đối tượng chứa thông tin người dùng và thông báo thành công
            // convert entity to DTO
            ArticlesDTO response = modelMapper.map(create, ArticlesDTO.class);

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

    public ResponseDTO update(long id, ArticlesDTO dto) {
        try {
            Optional<Articles> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                Articles get = opt.get();
                get.setName(dto.getName());
                get.setTitle(dto.getTitle());
                get.setPrice(dto.getPrice());
                get.setContent(dto.getContent());
                get.setCreateAt(dto.getCreateAt());
                get.setImage(dto.getImage());
                get.setLongitude(dto.getLongitude());
                get.setLatitude(dto.getLatitude());
                get.setStatus(dto.getStatus());

                Optional<Places> optPlace = placesRepository.findById(dto.getPlaces().getId());
                if (optPlace.isEmpty()) {
                    return new ResponseDTO(2, "User not found");
                }
                Places place = optPlace.get();
                get.setPlaces(place);

                Optional<Topics> optTopic = topicsRepository.findById(dto.getTopics().getId());
                if (optTopic.isEmpty()) {
                    return new ResponseDTO(2, "User not found");
                }
                Topics topic = optTopic.get();
                get.setTopics(topic);

                Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
                if (userOptional.isEmpty()) {
                    return new ResponseDTO(2, "User not found");
                }
                Users user = userOptional.get();
                get.setUsers(user);

                Articles update = this.repository.save(get);
                ArticlesDTO responseDto = modelMapper.map(update, ArticlesDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<Articles> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
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
