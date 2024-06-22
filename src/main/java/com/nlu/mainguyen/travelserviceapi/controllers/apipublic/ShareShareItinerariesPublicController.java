package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.ShareItineraries;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ShareItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.services.ShareItinerariesService;

@Controller
@RequestMapping(path = "/public/shareItineraries")
public class ShareShareItinerariesPublicController {
    @Autowired
    private ShareItinerariesService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody ShareItinerariesDTO request) {
        try {

     
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    @GetMapping("/listBySearch")
    public @ResponseBody List<ShareItinerariesDTO> listBySearch(@RequestParam("users_id") long users_id) {// B3
        try {
            List<ShareItineraries> shareItineraries = this.service.listByUserId(users_id);

            List<ShareItinerariesDTO> results = shareItineraries.stream()
                    .map(item -> modelMapper.map(item, ShareItinerariesDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    

    
}
