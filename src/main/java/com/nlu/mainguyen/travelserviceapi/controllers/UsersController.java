package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.UsersService;

import jakarta.validation.Valid;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.exception.ResourceNotFoundException;
import com.nlu.mainguyen.travelserviceapi.model.UserDTO;

@Controller
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
    private ModelMapper modelMapper;

    // public UsersController(UsersService service) {
    // this.service = service;
    // }

    @GetMapping("/list")
    public @ResponseBody List<UserDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert về DTO thì stream().map()
                                                       // tương đương for

            List<UserDTO> results = this.service.getAll().stream().map(i -> modelMapper.map(i, UserDTO.class))
                    .collect(Collectors.toList());

            return results;

            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDto) {
        try {
            // convert DTO to entity
            Users userRequest = modelMapper.map(userDto, Users.class);

            Users user = this.service.create(userRequest);// lưu database, trả về id

            // convert entity to DTO
            UserDTO userResponse = modelMapper.map(user, UserDTO.class);

            return new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);// OK : 200, 201
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserDTO> viewCommentsByID(@PathVariable("id") Long id) {
        try {
            Users i = this.service.getById(id);

            UserDTO resp = modelMapper.map(i, UserDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
    }
 

    @PostMapping("/edit/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable long id, @RequestBody UserDTO userDTO) {

        try {

            // convert DTO to Entity
            Users userRequest = modelMapper.map(userDTO, Users.class);

            Users users = this.service.update(id, userRequest);

            // entity to DTO
            UserDTO resp = modelMapper.map(users, UserDTO.class);

            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/remove/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            // Thực hiện xóa dữ liệu với ID đã cho
            this.service.deleteByID(id);

            return ResponseEntity.ok().body("Delete successful");
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về mã lỗi BAD_REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @GetMapping("/detail/{username}")
    public ResponseEntity<UserDTO> viewCommentsByUserName(@PathVariable("username") String username) {
        try {
            Users i = this.service.getByName("minhminh");

            UserDTO resp = modelMapper.map(i, UserDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
    }
}
