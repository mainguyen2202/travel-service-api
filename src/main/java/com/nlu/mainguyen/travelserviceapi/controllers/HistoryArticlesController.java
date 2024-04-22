package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.nlu.mainguyen.travelserviceapi.model.HistoryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.services.HistoryArticlesService;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path="/historyArticles")
public class HistoryArticlesController {
    private HistoryArticlesService service;

	public HistoryArticlesController(HistoryArticlesService service) {
		this.service = service;
	}
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<HistoryArticlesDTO> showAll(Model model) {
        try {
     
            List<HistoryArticlesDTO> results = this.service.getAll().stream()
            .map(item-> modelMapper.map(item, HistoryArticlesDTO.class))
            .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    
}
