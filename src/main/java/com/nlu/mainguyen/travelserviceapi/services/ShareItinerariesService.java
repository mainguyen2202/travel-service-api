package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.ShareItineraries;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ShareItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ShareItinerariesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class ShareItinerariesService {
    @Autowired
    private ShareItinerariesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    private final UsersRepository userRepository;
    private final ItinerariesRepository itinerariesRepository;

    public ShareItinerariesService(ShareItinerariesRepository repository, UsersRepository userRepository,
            ItinerariesRepository itinerariesRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.itinerariesRepository = itinerariesRepository;
    }

       // lấy danh sách theo id
       public List<ShareItineraries> listByUserId(long users_id) {// B2
      

        return this.repository.findByUsersId(users_id);
      
    }


    public ResponseDTO create(ShareItinerariesDTO dto) {
        try {
            ShareItineraries ent = modelMapper.map(dto, ShareItineraries.class);// chuyển từ dto sang
                                                                                // entity
                                                                                

            Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            ent.setUsers(user);

         
            Optional<ShareItineraries> existingLikes = repository.findByUsersIdAndItinerariesId(user.getId(), ent.getItineraries().getId());
            if (existingLikes.isPresent()) {
                return new ResponseDTO(2, "Itineraries and userId already exist");
            }

            ShareItineraries created = this.repository.save(ent);

            ShareItinerariesDTO responseDto = modelMapper.map(created, ShareItinerariesDTO.class);

            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    

}
