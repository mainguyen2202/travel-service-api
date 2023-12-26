package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.entities.News;
import com.nlu.mainguyen.travelserviceapi.services.NewsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/news")
public class NewsController {
    private NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}
    
      @GetMapping("/list")
    public @ResponseBody Iterable<News> showAllNews(Model model) {
        return this.newsService.showAllNews();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registrationNews(@RequestBody News News) {
        // TODO
        News result = 	this.newsService.createNews(News);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody News  viewNewsByID(@PathVariable("id") Long id) {
        News News = 	this.newsService.getByIdNews(id);
        return News;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editNews(@PathVariable("id") Long id, @Valid @RequestBody News updateCurrentNews) {
        	this.newsService.updateNews(updateCurrentNews);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removeNews(@PathVariable("id") Long id) {
        	this.newsService.deleteByID(id);
        return "success";
    }
    
}
