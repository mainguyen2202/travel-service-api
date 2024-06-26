package com.nlu.mainguyen.travelserviceapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Contacts;
import com.nlu.mainguyen.travelserviceapi.model.ContactsDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ContactsRepository;
import com.nlu.mainguyen.travelserviceapi.util.GEmailSender;

@Service
public class ContactsService {
    @Autowired
    private ContactsRepository repository;

    @Autowired
    private GEmailSender gEmailSender;

    @Autowired
    private ModelMapper modelMapper;

    public ContactsService(ContactsRepository repository, GEmailSender gEmailSender) {
        this.repository = repository;
        this.gEmailSender = gEmailSender;
    }

    public ResponseDTO create(ContactsDTO dto) {
        try {
            Contacts entity = modelMapper.map(dto, Contacts.class);
            Contacts create = this.repository.save(entity);

            // Gửi mail cho KH
            // Admin đã nhận được mail với nội dung : .... vui lòng đợi phản hồi
            String text = " Admin đã nhận được mail với nội dung : " + dto.getMess() + " vui lòng đợi phản hồi";
            gEmailSender.sendEmail(dto.getEmail(), dto.getSubject(), text);

            // convert entity to DTO
            ContactsDTO response = modelMapper.map(create, ContactsDTO.class);

            return new ResponseDTO(1, " Created successfully", response);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

}
