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

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserOutputDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserInputDTO;

@Controller
@CrossOrigin("http://localhost:3000")
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
    public @ResponseBody List<UserOutputDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<UserOutputDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, UserOutputDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // đăng ký
    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> registration(@RequestBody UserInputDTO userRequest) {
        try {
            ResponseDTO userResponse = this.service.registration(userRequest);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(userResponse, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO userResponse = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(userResponse, HttpStatus.OK);// OK : 200, 201
        }
    }

    // đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserInputDTO userRequest) {
        ResponseDTO userResponse = this.service.login(userRequest.getUsername(), userRequest.getPassword());// lưu database, trả về id
        return new ResponseEntity<ResponseDTO>(userResponse, HttpStatus.OK);// OK : 200, 201
        
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserOutputDTO> viewCommentsByID(@PathVariable("id") Long id) {
        try {
            Users i = this.service.getById(id);

            UserOutputDTO resp = modelMapper.map(i, UserOutputDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<UserOutputDTO> update(@PathVariable long id, @RequestBody UserOutputDTO UserOutputDTO) {

        try {

            // convert DTO to Entity
            Users userRequest = modelMapper.map(UserOutputDTO, Users.class);
            Users users = this.service.update(id, userRequest);

            // entity to DTO
            UserOutputDTO resp = modelMapper.map(users, UserOutputDTO.class);

            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<UserOutputDTO> viewCommentsByUserName(@PathVariable("username") String username) {
        try {
            Users i = this.service.getByName("minhminh");

            UserOutputDTO resp = modelMapper.map(i, UserOutputDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(), HttpStatus.BAD_REQUEST);
        }
    }
}
