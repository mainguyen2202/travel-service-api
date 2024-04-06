package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;
import com.nlu.mainguyen.travelserviceapi.model.ItineraryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.ItineraryArticlesService;


@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/itineraryArticles")
public class ItineraryArticlesController {
    @Autowired
    private ItineraryArticlesService service;

    @Autowired
    private ModelMapper modelMapper;

      @PostMapping("/create")

    public ResponseEntity<ResponseDTO> create(@RequestBody ItineraryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    @GetMapping("/listBySearch/{itineraries_id}")
    public @ResponseBody List<ItineraryArticlesDTO> listBySearch(@PathVariable("itineraries_id") long itineraries_id) {//B3
        try {
            List<ItineraryArticles> ItineraryArticles = this.service.listByItineraryId(itineraries_id);

           
            List<ItineraryArticlesDTO> results = ItineraryArticles.stream()
                    .map(item -> modelMapper.map(item, ItineraryArticlesDTO.class))
                    .collect(Collectors.toList());//B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

  

   
}
