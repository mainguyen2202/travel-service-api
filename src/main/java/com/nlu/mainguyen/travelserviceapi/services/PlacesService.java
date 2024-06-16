package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.PlacesDTO;
import com.nlu.mainguyen.travelserviceapi.model.CoordinatesDTO;
import com.nlu.mainguyen.travelserviceapi.model.PlacesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseInfoDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.CoordinatesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.PlacesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class PlacesService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlacesRepository repository;

      private final CoordinatesRepository coordinatesRepository;

 
public PlacesService(PlacesRepository repository, CoordinatesRepository coordinatesRepository) {
        this.repository = repository;
        this.coordinatesRepository = coordinatesRepository;
    }

    // lấy danh sách
    public List<Places> getAll() {
        return this.repository.findAll();
    }

// lấy danh sách theo id
    public List<Places> listByCoordinatesId(long coordinates_id) {//B2
        return this.repository.findAllByCoordinatesId(coordinates_id);
    }

    public Iterable<Places> findByName(String name) {
        String search = "%" + name + "%";
        return this.repository.findByName(search);
    }

   

    public Places getById(Long id) {
        Optional<Places> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public ResponseDTO create(PlacesDTO dto) {
        try {
            Places entity = modelMapper.map(dto, Places.class);

            Optional<Coordinates> opt = coordinatesRepository.findById(dto.getCoordinates().getId());
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Coordinates c = opt.get();
            entity.setCoordinates(c);

          
            Places create = this.repository.save(entity);// lưu thành công và có id định danh

            // Tạo đối tượng chứa thông tin người dùng và thông báo thành công
            // convert entity to DTO
            PlacesDTO response = modelMapper.map(create, PlacesDTO.class);

            return new ResponseDTO(1, " Created successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

    public ResponseDTO update(long id, PlacesDTO dto) {
        try {
            Places entity = modelMapper.map(dto, Places.class); // chuyển từ dto sang entity
    
            Optional<Places> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                Places get = opt.get();
                get.setName(dto.getName());
                get.setContent(dto.getContent());
                get.setImage(dto.getImage());
                get.setStatus(dto.getStatus());

                Optional<Coordinates> optC = coordinatesRepository.findById(dto.getCoordinates().getId());
                if (optC.isEmpty()) {
                    return new ResponseDTO(2, "User not found");
                }
                Coordinates c = optC.get();
                get.setCoordinates(c);
    
                Places update = this.repository.save(get);
                PlacesDTO responseDto = modelMapper.map(update, PlacesDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<Places> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }

//  public ResponseInfoDTO getById(Long id) {
//         Optional<Places> items = this.repository.findById(id);
//         if (items.isPresent()) {
//             Places result = items.get();
//             return new ResponseInfoDTO(1, "", result);
//         }
//         return new ResponseInfoDTO(1, "", null);
//     }

   

// Lấy danh sách con theo submenuid

}
