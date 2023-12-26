package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.services.TopicsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/Topics")
public class TopicsController {
    private TopicsService topicsService;

	public TopicsController(TopicsService topicsService) {
		this.topicsService = topicsService;
	}
    
      @GetMapping("/list")
    public @ResponseBody Iterable<Topics> showAllTopics(Model model) {
        return this.topicsService.showAllTopics();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registrationTopics(@RequestBody Topics Topics) {
        // TODO
        Topics result = this.topicsService.createTopics(Topics);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Topics  viewTopicsByID(@PathVariable("id") Long id) {
        Topics Topics = this.topicsService.getByIdTopics(id);
        return Topics;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editTopics(@PathVariable("id") Long id, @Valid @RequestBody Topics updateCurrentTopics) {
        this.topicsService.updateTopics(updateCurrentTopics);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removeTopics(@PathVariable("id") Long id) {
        this.topicsService.deleteByID(id);
        return "success";
    }
    
}

