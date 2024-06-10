package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class ItinerariesService {
    @Autowired
    private ItinerariesRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    private final UsersRepository userRepository;

    public ItinerariesService(ItinerariesRepository repository, UsersRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Itineraries> getAll() {
        return this.repository.findAll();
    }

    // lấy danh sách theo id
    public List<Itineraries> listByUserId(long user_id) {// B2
        return this.repository.findAllByUserId(user_id);
    }

    public ResponseDTO create(ItinerariesDTO dto) {
        try {
            Itineraries itineraries = modelMapper.map(dto, Itineraries.class);// chuyển từ dto sang entity

            Optional<Users> userOptional = userRepository.findById(dto.getUsersId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            itineraries.setUsers(user);

            Itineraries created = this.repository.save(itineraries);

            ItinerariesDTO responseDto = modelMapper.map(created, ItinerariesDTO.class);

            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public Itineraries getById(Long id) {
        Optional<Itineraries> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }


    public ResponseDTO update(long id, ItinerariesDTO dto) {
        try {
            Optional<Itineraries> itinerariesOptional = this.repository.findById(id);
        if (itinerariesOptional.isEmpty()) {
            return null;// không tìm thấy dữ liệu return rỗng
        } else {
            // Cập nhật các trường của itineraries với các giá trị mới từ request
            Itineraries itinerariesget = itinerariesOptional.get();
            itinerariesget.setName(dto.getName());
            itinerariesget.setContent(dto.getContent());
            itinerariesget.setDateStart(dto.getDateStart());
            itinerariesget.setDateEnd(dto.getDateEnd());
            itinerariesget.setParticipantCount((dto.getParticipantCount()));

            Itineraries update = this.repository.save(itinerariesget);
            ItinerariesDTO responseDto = modelMapper.map(update, ItinerariesDTO.class);

            return new ResponseDTO(1, "Update successfully", responseDto);
        }} catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }
    public ResponseDTO updateAdmin(long id, ItinerariesDTO dto) {
        try {
            Optional<Itineraries> itinerariesOptional = this.repository.findById(id);
        if (itinerariesOptional.isEmpty()) {
            return null;// không tìm thấy dữ liệu return rỗng
        } else {
            // Cập nhật các trường của itineraries với các giá trị mới từ request
            Itineraries itinerariesget = itinerariesOptional.get();
            itinerariesget.setName(dto.getName());
            itinerariesget.setContent(dto.getContent());
            itinerariesget.setDateStart(dto.getDateStart());
            itinerariesget.setDateEnd(dto.getDateEnd());
            itinerariesget.setStatus(dto.getStatus());
            itinerariesget.setParticipantCount((dto.getParticipantCount()));

            Itineraries update = this.repository.save(itinerariesget);
            ItinerariesDTO responseDto = modelMapper.map(update, ItinerariesDTO.class);

            return new ResponseDTO(1, "Update successfully", responseDto);
        }} catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<Itineraries> itineraries = this.repository.findById(id);
        if (itineraries.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }
}
