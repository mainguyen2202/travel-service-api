package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseInfoDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseListDTO;
import com.nlu.mainguyen.travelserviceapi.services.PlacesService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin("http://localhost:3000")// bảo mật thông tin - system -  cho phép client nào được phép truy cập
@RequestMapping(path = "/places")
public class PlacesController {
    private PlacesService service;

    public PlacesController(PlacesService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public @ResponseBody ResponseListDTO showAll() {
        try {
            Iterable<Places> result = this.service.showAll();
            return new ResponseListDTO(1, "", result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseListDTO(2, ex.getMessage(), null);
        }
    }

    @GetMapping("/seachByName")
    public @ResponseBody ResponseListDTO findByName(@RequestParam String name) {
        Iterable<Places> result = this.service.findByName(name);
        return new ResponseListDTO(1, "", result);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseInfoDTO registration(@RequestBody Places input) {
        return this.service.create(input);
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody ResponseInfoDTO viewCommentsByID(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody ResponseDTO edit(@PathVariable("id") Long id, @Valid @RequestBody Places input) {
        // route param
        return this.service.update(id, input);

    }

    @PostMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable("id") Long id) {
        boolean result = this.service.deleteByID(id);
        if (result) {
            return new ResponseDTO(1, "");
        } else {
            return new ResponseDTO(2, "Thất bại");
        }
    }
}
