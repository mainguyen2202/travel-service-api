package com.nlu.mainguyen.travelserviceapi.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
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

    public ResponseDTO clickLike(LikesDTO dto) {
        try {

            Optional<Likes> opt = repository.findByUsersIdAndArticlesId(dto.getUsers().getId(), dto.getArticles().getId());
            if (opt.isEmpty()) {
                Likes ent = modelMapper.map(dto, Likes.class); // chuyển từ dto sang entity
                ent.setStatus(1);

                LocalDate localDate = LocalDate.now();// thời gian hiện tại
                Date now = Date.valueOf(localDate);
                ent.setModifyDate(now);

                Likes created = repository.save(ent);
                LikesDTO responseDto = modelMapper.map(created, LikesDTO.class);
                return new ResponseDTO(1, "Create successfully", responseDto);
            } else {
                Likes info = opt.get();
                if (info.getStatus() == 1) {
                    info.setStatus(0);
                } else {
                    info.setStatus(1);
                }

                info.setModifyDate(new Date(System.currentTimeMillis()));// thời gian hiện tại

                Likes update = repository.save(info);
                LikesDTO responseDto = modelMapper.map(update, LikesDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to update: " + e.getMessage());
        }
    }

}
