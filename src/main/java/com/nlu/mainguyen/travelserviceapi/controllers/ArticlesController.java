package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.ArticlesService;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/articles")
public class ArticlesController {
    private ArticlesService service;

	public ArticlesController(ArticlesService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Articles> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Articles input) {
        // TODO
        Articles result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Articles  viewCommentsByID(@PathVariable("id") Long id) {
        Articles result = this.service.getById(id);
        return result;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Articles input) {
        this.service.update(input);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
