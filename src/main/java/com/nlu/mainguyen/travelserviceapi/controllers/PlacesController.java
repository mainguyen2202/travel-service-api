package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.services.PlacesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/places")
public class PlacesController {
    private PlacesService placesService;

	public PlacesController(PlacesService placesService) {
		this.placesService = placesService;
	}
    
      @GetMapping("/list")
    public @ResponseBody Iterable<Places> showAllPlaces(Model model) {
        return this.placesService.showAllPlaces();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registrationPlaces(@RequestBody Places places) {
        // TODO
        Places result = this.placesService.createPlaces(places);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Places  viewPlacesByID(@PathVariable("id") Long id) {
        Places places = this.placesService.getByIdPlaces(id);
        return places;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editPlaces(@PathVariable("id") Long id, @Valid @RequestBody Places updateCurrentPlaces) {
        this.placesService.updatePlaces(updateCurrentPlaces);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removePlaces(@PathVariable("id") Long id) {
        this.placesService.deleteByID(id);
        return "success";
    }
    
}
