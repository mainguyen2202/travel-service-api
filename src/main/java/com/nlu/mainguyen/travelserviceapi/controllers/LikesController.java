package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.LikesService;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/likes")
public class LikesController {
    private LikesService service;

	public LikesController(LikesService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Likes> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Likes input) {
        // TODO
        Likes result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Likes  viewCommentsByID(@PathVariable("id") Long id) {
        Likes result = this.service.getById(id);
        return result;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Likes input) {
        this.service.update(input);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
