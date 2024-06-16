package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlu.mainguyen.travelserviceapi.services.ContactsService;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ContactsDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/public/contacts")
public class ContactsControllerPublic {
    @Autowired
    private ContactsService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody ContactsDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
}
