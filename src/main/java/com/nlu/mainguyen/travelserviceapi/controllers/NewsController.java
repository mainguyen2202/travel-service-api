package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.NewsService;

import com.nlu.mainguyen.travelserviceapi.entities.News;

import jakarta.validation.Valid;


@Controller
@RequestMapping(path="/News")
public class NewsController {
    private NewsService service;

	public NewsController(NewsService service) {
		this.service = service;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<News> showAll(Model model) {
        return this.service.showAll();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody News input) {
        // TODO
        News result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody News  viewCommentsByID(@PathVariable("id") Long id) {
        News result = this.service.getById(id);
        return result;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody News input) {
        this.service.update(input);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
