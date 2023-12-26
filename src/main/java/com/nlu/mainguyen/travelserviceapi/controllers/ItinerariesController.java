package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.ItinerariesService;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/itineraries")
public class ItinerariesController {
    private ItinerariesService service;

	public ItinerariesController(ItinerariesService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Itineraries> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Itineraries input) {
        // TODO
        Itineraries result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Itineraries  viewCommentsByID(@PathVariable("id") Long id) {
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
