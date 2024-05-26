package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.CoordinatesService;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.model.CoordinatesDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<CoordinatesDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<Coordinates> data = this.service.getAll();// lấy dữ liệu ở db

            List<CoordinatesDTO> results = data.stream()
                    .map(item -> modelMapper.map(item, CoordinatesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Coordinates input) {
        // TODO
        Coordinates result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Coordinates viewCommentsByID(@PathVariable("id") Long id) {
        Coordinates result = this.service.getById(id);
        return result;
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Coordinates input) {
        this.service.update(input);
        return "success";

    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
