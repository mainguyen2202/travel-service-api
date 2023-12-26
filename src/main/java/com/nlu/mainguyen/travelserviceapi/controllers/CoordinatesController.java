package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.CoordinatesService;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/coordinates")
public class CoordinatesController {
    private CoordinatesService service;

	public CoordinatesController(CoordinatesService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Coordinates> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Coordinates input) {
        // TODO
        Coordinates result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Coordinates  viewCommentsByID(@PathVariable("id") Long id) {
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
