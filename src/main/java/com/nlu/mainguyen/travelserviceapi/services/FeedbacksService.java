package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.FeedbacksDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.FeedbacksRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class FeedbacksService {

    private FeedbacksRepository repository;
    
    private final UsersRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public FeedbacksService(FeedbacksRepository repository,UsersRepository userRepository) {
        
        this.repository = repository;
        this.userRepository = userRepository;
    }

       // lấy danh sách theo id
       public List<Feedbacks> listByArticlesId( long articlesId) {// B2
        return this.repository.findAllByArticlesId( articlesId);
    }

    
    public List<Feedbacks> listByHeart( int heart ,long articlesId) {// B2
        return this.repository.findAllByHeart(heart, articlesId);
    }
    
    
  

    public ResponseDTO create(FeedbacksDTO dto) {
        try {
            Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            Feedbacks ent = modelMapper.map(dto, Feedbacks.class);
            ent.setUsers(user);
    
            // Kiểm tra xem đã tồn tại bản ghi với cặp usersId và articlesId hay chưa
            Optional<Feedbacks> existingFeedbacks = repository.findByUsersIdAndArticlesId(user.getId(), ent.getArticles().getId());
            if (existingFeedbacks.isPresent()) {
                return new ResponseDTO(2, "articlesId and userId already exist");
            }
    
            Feedbacks created = repository.save(ent);
            FeedbacksDTO responseDto = modelMapper.map(created, FeedbacksDTO.class);
            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }
}
