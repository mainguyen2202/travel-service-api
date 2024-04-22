package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.model.LikesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.LikesService;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path="/likes")
public class LikesController {
    private LikesService service;

	public LikesController(LikesService service) {
		this.service = service;
	}
    @Autowired
    private ModelMapper modelMapper;
    

    @GetMapping("/list")
    public @ResponseBody List<LikesDTO> showAll(Model model) {
        try {
     
            List<LikesDTO> results = this.service.getAll().stream()
            .map(item-> modelMapper.map(item, LikesDTO.class))
            .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 

    @GetMapping("/listBySearch")
    public @ResponseBody List<LikesDTO> listBySearch(@RequestParam("users_id") long users_id) {// B3
        try {
            List<Likes> ltsLikes = this.service.listByUserId(users_id);

            List<LikesDTO> results = ltsLikes.stream()
                    .map(item -> modelMapper.map(item, LikesDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody LikesDTO request) {
        try {
          
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

}
