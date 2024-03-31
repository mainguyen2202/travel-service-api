package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.exception.ResourceNotFoundException;
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
            Itineraries itineraries = new Itineraries();
            itineraries.setName(dto.getName());
            itineraries.setDateStart(dto.getDateStart());
            itineraries.setDateEnd(dto.getDateEnd());
            // itineraries.setStatus(dto.getStatus());
            // itineraries.setPosition(dto.getPosition());
            itineraries.setContent(dto.getContent());

            Users user = userRepository.findById(dto.getUsersId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            itineraries.setUsers(user);

            Itineraries created = this.repository.save(itineraries);

            ItinerariesDTO responseDto = new ItinerariesDTO();
            responseDto.setId(created.getId());
            responseDto.setName(created.getName());
            responseDto.setDateStart(created.getDateStart());
            responseDto.setDateEnd(created.getDateEnd());
            // responseDto.setStatus(created.getStatus());
            // responseDto.setPosition(created.getPosition());
            responseDto.setContent(created.getContent());
            responseDto.setUsersId(created.getUsers().getId());

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

    public Itineraries update(long id, Itineraries request) {
        Itineraries itineraries = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itineraries", "id", id));

        // Cập nhật các trường của itineraries với các giá trị mới từ request
        itineraries.setName(request.getName());
        itineraries.setContent(request.getContent());
        itineraries.setDateStart(request.getDateStart());
        itineraries.setDateEnd(request.getDateEnd());

        return this.repository.save(itineraries);
    }

    public Itineraries deleteByID(Long id) {
        Itineraries itineraries = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itineraries", "id", id));
    
        this.repository.deleteById(id);
    
        return itineraries;
    }
}
