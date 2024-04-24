package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.LikesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.LikesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class LikesService {

    private LikesRepository repository;
    private final UsersRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public LikesService(LikesRepository repository, UsersRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Likes> getAll() {
        return this.repository.findAll();
    }

    // lấy danh sách theo id
    public List<Likes> listByUserId(long users_id) {// B2
        return this.repository.findAllByUserId(users_id);
    }

    public ResponseDTO create(LikesDTO dto) {
        try {
            Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            Likes ent = modelMapper.map(dto, Likes.class);
            ent.setUsers(user);
    
            // Kiểm tra xem đã tồn tại bản ghi với cặp usersId và articlesId hay chưa
            Optional<Likes> existingLikes = repository.findByUsersIdAndArticlesId(user.getId(), ent.getArticles().getId());
            if (existingLikes.isPresent()) {
                return new ResponseDTO(2, "articlesId and userId already exist");
            }
    
            Likes created = repository.save(ent);
            LikesDTO responseDto = modelMapper.map(created, LikesDTO.class);
            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

}
