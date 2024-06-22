package com.nlu.mainguyen.travelserviceapi.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;
import com.nlu.mainguyen.travelserviceapi.model.HistoryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.HistoryArticlesRepository;

@Service
public class HistoryArticlesService {
    @Autowired
    private HistoryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public HistoryArticlesService(HistoryArticlesRepository repository) {
        this.repository = repository;
    }

    // lấy danh sách
    public List<HisArticles> getAll() {
        return repository.findAll();
    }

    public ResponseDTO getByIdArticles(long articles_id) {
        try {
            Optional<HisArticles> opt = this.repository.findByArticlesId(articles_id);
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            HistoryArticlesDTO response = modelMapper.map(opt.get(), HistoryArticlesDTO.class);
            return new ResponseDTO(1, " Successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

    public ResponseDTO clickView(HistoryArticlesDTO dto) {
        try {

            Optional<HisArticles> opt = repository.findByArticlesId(dto.getArticles().getId());
            if (opt.isEmpty()) {
                HisArticles ent = modelMapper.map(dto, HisArticles.class); // chuyển từ dto sang entity
                ent.setCount(1);

                LocalDate localDate = LocalDate.now();// thời gian hiện tại
                Date now = Date.valueOf(localDate);
                ent.setModifyDate(now);

                HisArticles created = repository.save(ent);
                HistoryArticlesDTO responseDto = modelMapper.map(created, HistoryArticlesDTO.class);
                return new ResponseDTO(1, "Create successfully", responseDto);
            } else {
                HisArticles info = opt.get();
                info.setCount(info.getCount() + 1);

                info.setModifyDate(new Date(System.currentTimeMillis()));// thời gian hiện tại

                HisArticles update = repository.save(info);
                HistoryArticlesDTO responseDto = modelMapper.map(update, HistoryArticlesDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
                return new ResponseDTO(2, "Failed to update: " + e.getMessage());
        }
    }

}
