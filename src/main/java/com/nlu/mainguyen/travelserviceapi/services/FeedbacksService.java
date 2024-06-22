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
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.FeedbacksRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class FeedbacksService {

    private FeedbacksRepository repository;
    
    private final UsersRepository userRepository;
    private final ArticlesRepository articlesrepository;
    @Autowired
    private ModelMapper modelMapper;
    public FeedbacksService(FeedbacksRepository repository,UsersRepository userRepository,ArticlesRepository articlesrepository) {
        
        this.repository = repository;
        this.userRepository = userRepository;
        this.articlesrepository = articlesrepository;
    }

    
    // lấy danh sách
    public List<Feedbacks> getAll() {
        return repository.findAll();
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

    public Feedbacks getById(Long id) {
        Optional<Feedbacks> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

        public ResponseDTO update(long id, FeedbacksDTO dto) {
        try {
            Feedbacks entity = modelMapper.map(dto, Feedbacks.class); // chuyển từ dto sang entity
    
            Optional<Feedbacks> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                Feedbacks get = opt.get();
                get.setHeart(dto.getHeart());
                get.setShare(dto.getShare());
                get.setReview(dto.getReview());
                get.setContent(dto.getContent());
                get.setCreatAt(dto.getCreatAt());
                get.setStatus(dto.getStatus());

                // Optional<Articles> optArticles = articlesrepository.findById(dto.getArticles().getId());
                // if (optArticles.isEmpty()) {
                //     return new ResponseDTO(2, "User not found");
                // }
                // Articles articles = optArticles.get();
                // get.setArticles(articles);
    
               
    
                // Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
                // if (userOptional.isEmpty()) {
                //     return new ResponseDTO(2, "User not found");
                // }
                // Users user = userOptional.get();
                // get.setUsers(user);
                

                Feedbacks update = this.repository.save(get);
                FeedbacksDTO responseDto = modelMapper.map(update, FeedbacksDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }


    public ResponseDTO deleteByID(Long id) {
        Optional<Feedbacks> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }
}
