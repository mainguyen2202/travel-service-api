package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.FeedbacksService;

import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/feedbacks")
public class FeedbacksController {
    private FeedbacksService service;

	public FeedbacksController(FeedbacksService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Feedbacks> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Feedbacks input) {
        // TODO
        Feedbacks result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Feedbacks  viewCommentsByID(@PathVariable("id") Long id) {
        Feedbacks result = this.service.getById(id);
        return result;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Feedbacks input) {
        this.service.update(input);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
