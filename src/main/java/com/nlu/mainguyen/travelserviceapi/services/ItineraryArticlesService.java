package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.model.ItineraryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ItineraryArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;

@Service
public class ItineraryArticlesService {
    @Autowired
    private ItineraryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    private final ArticlesRepository articlesRepository;
    public ItineraryArticlesService(ItineraryArticlesRepository repository, ArticlesRepository articlesRepository,
            ItinerariesRepository itinerariesRepository) {
        this.repository = repository;
        this.articlesRepository = articlesRepository;
    }

      // lấy danh sách
    public List<ItineraryArticles> getAll() {
        return this.repository.findAll();
    }


    public List<ItineraryArticles> listByItineraryId(long itineraries_id, String date_start) {
        // if (date_start == null) {
        if (date_start == "") {
            return this.repository.findAllByIdItinerary(itineraries_id);
        }

        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // String date_str = format.format(date_start);// covert Date to String
        // System.out.println(date_str);

        return this.repository.findAllByDateStart(itineraries_id, date_start);
    }




    public ResponseDTO create(ItineraryArticlesDTO dto) {
        try {
            ItineraryArticles ent = modelMapper.map(dto, ItineraryArticles.class);
            
            Optional<Articles> userOptional = articlesRepository.findById(dto.getArticles().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Articles user = userOptional.get();
            ent.setArticles(user);
    
            // Kiểm tra xem đã tồn tại bản ghi với cặp ArticlesId và articlesId hay chưa
            Optional<ItineraryArticles> existingItineraryArticles = repository.findByItinerariesIdAndArticlesId(ent.getItineraries().getId(),user.getId());
            if (existingItineraryArticles.isPresent()) {
                return new ResponseDTO(2, "articlesId and userId already exist");
            }
    
            ItineraryArticles created = repository.save(ent);
            ItineraryArticlesDTO responseDto = modelMapper.map(created, ItineraryArticlesDTO.class);
            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO update(long id, ItineraryArticlesDTO dto) {
        try {
            Optional<ItineraryArticles> opl = this.repository.findById(id);
            if (opl.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                ItineraryArticles ia = opl.get();
                ia.setDateStart(dto.getDateStart());

                ItineraryArticles update = this.repository.save(ia);
                ItineraryArticlesDTO responseDto = modelMapper.map(update, ItineraryArticlesDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ItineraryArticles getById(Long id) {
        Optional<ItineraryArticles> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<ItineraryArticles> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }
}
