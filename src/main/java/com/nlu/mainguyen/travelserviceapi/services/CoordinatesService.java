package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.model.CoordinatesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.CoordinatesRepository;

@Service
public class CoordinatesService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CoordinatesRepository repository;

    public List<Coordinates> getAll() {
        return this.repository.findAll();
    }

   
    public Coordinates getById(Long id) {
        Optional<Coordinates> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public ResponseDTO create(CoordinatesDTO dto) {
        try {
            Coordinates entity = modelMapper.map(dto, Coordinates.class);
            Coordinates create = this.repository.save(entity);// lưu thành công và có id định danh

            // Tạo đối tượng chứa thông tin người dùng và thông báo thành công
            // convert entity to DTO
            CoordinatesDTO response = modelMapper.map(create, CoordinatesDTO.class);

            return new ResponseDTO(1, " Created successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

    public ResponseDTO update(long id, CoordinatesDTO dto) {
        try {
            Coordinates entity = modelMapper.map(dto, Coordinates.class); // chuyển từ dto sang entity
    
            Optional<Coordinates> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                Coordinates get = opt.get();
                get.setLatitude(dto.getLatitude());
                get.setLongitude(dto.getLongitude());
                get.setStatus(dto.getStatus());

            
    
                Coordinates update = this.repository.save(get);
                CoordinatesDTO responseDto = modelMapper.map(update, CoordinatesDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<Coordinates> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }

   
}
