package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.model.ItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.services.ItinerariesService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/itineraries")
public class ItinerariesController {
    @Autowired
    private ItinerariesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<ItinerariesDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<ItinerariesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, ItinerariesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/list/{users_id}")
    public @ResponseBody
    List<ItinerariesDTO> showAllUsersId(@PathVariable("users_id") long users_id) {//B3
        try {
            List<Itineraries> Itineraries = this.service.listByUsersId(users_id);

           
            List<ItinerariesDTO> results = Itineraries.stream()
                    .map(item -> modelMapper.map(item, ItinerariesDTO.class))
                    .collect(Collectors.toList());//B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Itineraries input) {
        // TODO
        Itineraries result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Itineraries viewCommentsByID(@PathVariable("id") Long id) {
        Itineraries result = this.service.getById(id);
        return result;
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Itineraries input) {
        this.service.update(input);
        return "success";

    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
