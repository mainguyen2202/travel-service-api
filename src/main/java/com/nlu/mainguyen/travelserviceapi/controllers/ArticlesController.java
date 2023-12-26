package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.services.ArticlesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/articles")
public class ArticlesController {
     private ArticlesService articlesService;

	public ArticlesController(ArticlesService articlesService) {
		this.articlesService = articlesService;
	}
    
      @GetMapping("/list")
    public @ResponseBody Iterable<Articles> showAllArticles(Model model) {
        return this.articlesService.showAllArticles();
        
    }
      @PostMapping("/create")
    public @ResponseBody String createArticles(@RequestBody Articles Articles) {
        // TODO
        Articles result = this.articlesService.createArticles(Articles);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Articles  viewArticlesByID(@PathVariable("id") Long id) {
        Articles Articles = this.articlesService.getByIdArticles(id);
        return Articles;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editArticles(@PathVariable("id") Long id, @Valid @RequestBody Articles updateCurrentArticles) {
        this.articlesService.updateArticles(updateCurrentArticles);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removeArticles(@PathVariable("id") Long id) {
        this.articlesService.deleteByID(id);
        return "success";
    }
}
