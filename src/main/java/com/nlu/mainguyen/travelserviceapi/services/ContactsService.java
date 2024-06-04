package com.nlu.mainguyen.travelserviceapi.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Contacts;
import com.nlu.mainguyen.travelserviceapi.model.ContactsDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ContactsRepository;

@Service
public class ContactsService {
    @Autowired
    private ContactsRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseDTO create(ContactsDTO dto) {
        try {
            Contacts entity = modelMapper.map(dto, Contacts.class);
            Contacts create= this.repository.save(entity);

            // convert entity to DTO
            ContactsDTO response = modelMapper.map(create, ContactsDTO.class);

            return new ResponseDTO(1, " Created successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

}
