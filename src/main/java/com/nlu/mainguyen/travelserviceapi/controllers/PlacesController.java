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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseInfoDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseListDTO;
import com.nlu.mainguyen.travelserviceapi.model.PlacesDTO;
import com.nlu.mainguyen.travelserviceapi.model.PlacesDTO;

import com.nlu.mainguyen.travelserviceapi.services.PlacesService;

import jakarta.validation.Valid;

@Controller // bảo mật thông tin - system - cho phép client nào được phép truy cập
@RequestMapping(path = "/places")
public class PlacesController {

    @Autowired
    private PlacesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/private/showCart")
    public @ResponseBody String showCart() {
        return "Show Cart";
    }

    @GetMapping("/public/showProducts")
    public @ResponseBody String listProducts() {
        return "List Products";
    }

    @GetMapping("/registerUser")
    public @ResponseBody String registerUser() {
        return "Register User";
    }

    @GetMapping("/public/list")
    public @ResponseBody List<PlacesDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<PlacesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, PlacesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/list/{coordinates_id}")
    public @ResponseBody List<PlacesDTO> showAllId(@PathVariable("coordinates_id") long coordinates_id) {// B3
        try {
            List<Places> places = service.listByCoordinatesId(coordinates_id);

            /*
             * List<PlacesDTO> results = new ArrayList<PlacesDTO>();
             * for (Places item : places) {
             * PlacesDTO itemDTO = modelMapper.map(item, PlacesDTO.class);
             * results.add(itemDTO);
             * }
             */
            List<PlacesDTO> results = places.stream()
                    .map(item -> modelMapper.map(item, PlacesDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // @GetMapping("/list")
    // public @ResponseBody ResponseListDTO showAll() {
    // try {
    // List<Places> result = this.service.showAll();
    // return new ResponseListDTO(1, "", result);
    // } catch (Exception ex) {
    // System.out.println(ex.getMessage());
    // return new ResponseListDTO(2, ex.getMessage(), null);
    // }
    // }
    @GetMapping("/seachByName")
    public @ResponseBody ResponseListDTO findByName(@RequestParam String name) {
        Iterable<Places> result = this.service.findByName(name);
        return new ResponseListDTO(1, "", result);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<PlacesDTO> viewByID(@PathVariable("id") Long id) {
        try {
            Places i = this.service.getById(id);

            PlacesDTO resp = modelMapper.map(i, PlacesDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<PlacesDTO>(new PlacesDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody PlacesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable long id, @RequestBody PlacesDTO request) {
        try {
            ResponseDTO response = this.service.update(id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

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
}
