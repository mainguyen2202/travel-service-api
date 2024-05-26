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
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserOutputDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserInputDTO;

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
        ResponseDTO userResponse = this.service.login(userRequest.getUsername(), userRequest.getPassword());// lưu
                                                                                                            // database,
                                                                                                            // trả về id
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
      public ResponseEntity<ResponseDTO> update(@PathVariable long id,@RequestBody UserOutputDTO request) {
        try {
            ResponseDTO response = this.service.update(id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }}

        @PostMapping("/editPassword/{id}")
        public ResponseEntity<ResponseDTO> updatePassword(@PathVariable long id,@RequestBody UserOutputDTO request) {
          try {
              ResponseDTO response = this.service.updatePassword(id, request);// lưu database, trả về id
              return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
          } catch (Exception e) {
              ResponseDTO response = new ResponseDTO(2, e.getMessage());
              return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
          }}

        @DeleteMapping("/remove/{id}")
        public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
            try {
                ResponseDTO response = this.service.deleteByID(id);
                return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
            } catch (Exception e) {
                ResponseDTO response = new ResponseDTO(2, e.getMessage());
                return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
            }
        }

    @GetMapping("/detailBySearch")
    public ResponseEntity<UserOutputDTO> detailBySearch(@RequestParam("username") String username,
            @RequestParam("email") String email, @RequestParam("role") int role) {
        try {
            Users user = this.service.detailBySearch(username, email, role); // Lấy thông tin người dùng dựa trên tên người
                                                                      // dùng

            UserOutputDTO resp = modelMapper.map(user, UserOutputDTO.class); // Chuyển đổi đối tượng người dùng thành
                                                                             // đối tượng DTO
            return ResponseEntity.ok().body(resp); // Trả về phản hồi thành công với đối tượng DTO
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(), HttpStatus.BAD_REQUEST); // Trả về phản hồi
                                                                                                   // lỗi nếu có lỗi xảy
                                                                                                   // ra
        }
    }
    @GetMapping("/detailBySearchUserName")
    public ResponseEntity<UserOutputDTO> detailBySearch(@RequestParam("username") String username) {
        try {
            Users user = this.service.detailBySearchUserName(username); // Lấy thông tin người dùng dựa trên tên người
                                                                      // dùng

            UserOutputDTO resp = modelMapper.map(user, UserOutputDTO.class); // Chuyển đổi đối tượng người dùng thành
                                                                             // đối tượng DTO
            return ResponseEntity.ok().body(resp); // Trả về phản hồi thành công với đối tượng DTO
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(), HttpStatus.BAD_REQUEST); // Trả về phản hồi
                                                                                                   // lỗi nếu có lỗi xảy
                                                                                                   // ra
        }
    }
}
