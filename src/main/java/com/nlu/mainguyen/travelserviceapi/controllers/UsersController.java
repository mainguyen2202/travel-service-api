package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.UsersService;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.UserDTO;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
	private ModelMapper modelMapper;

	// public UsersController(UsersService service) {
	// 	this.service = service;
	// }
    
    @GetMapping("/list")
    public @ResponseBody List<UserDTO> showAll(Model model) {
        try {
            List<Users> users = this.service.showAll();// danh sách Entity mà cần convert về DTO thì stream().map() tương đương for

            List<UserDTO> results = users.stream().map(i-> modelMapper.map(i, UserDTO.class)).collect(Collectors.toList());
        
            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
	public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDto) {

		// convert DTO to entity
		Users userRequest = modelMapper.map(userDto, Users.class);

		Users user = this.service.create(userRequest);// lưu database, trả về id 

		// convert entity to DTO
		UserDTO userResponse = modelMapper.map(user, UserDTO.class);

		return new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);// OK : 200, 201
	}

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserDTO> viewCommentsByID(@PathVariable("id") Long id) {
        try {
            // Users i = this.service.getById(id);
            Users i = this.service.getByName("minhminh");
            
            UserDTO resp = modelMapper.map(i, UserDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Users input) {
        this.service.update(input);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
