package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Coordinates;
import com.nlu.mainguyen.travelserviceapi.services.CoordinatesService;

@Controller
@RequestMapping(path="/coordinates")
public class CoordinatesController {
        private CoordinatesService coordinatesService;


    
    public CoordinatesController(CoordinatesService coordinatesService) {
			this.coordinatesService = coordinatesService;
		}



	@GetMapping("/list")
    public @ResponseBody Iterable<Coordinates> showAllCoordinates(Model model) {
        return this.coordinatesService.showAllCoordinates();
        
    }
}
