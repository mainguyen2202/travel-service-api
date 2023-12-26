package com.nlu.mainguyen.travelserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.services.LikesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/likes")
public class LikesController {
    private LikesService likesService;

	public LikesController(LikesService likesService) {
		this.likesService = likesService;
	}
    
      @GetMapping("/list")
    public @ResponseBody Iterable<Likes> showAllLikes(Model model) {
        return this.likesService.showAllLikes();
        
    }
      @PostMapping("/create")
    public @ResponseBody String registrationLikes(@RequestBody Likes Likes) {
        // TODO
        Likes result = this.likesService.createLikes(Likes);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Likes  viewLikesByID(@PathVariable("id") Long id) {
        Likes Likes = this.likesService.getByIdLikes(id);
        return Likes;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editLikes(@PathVariable("id") Long id, @Valid @RequestBody Likes updateCurrentLikes) {
        this.likesService.updateLikes(updateCurrentLikes);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removeLikes(@PathVariable("id") Long id) {
        this.likesService.deleteByID(id);
        return "success";
    }
    
}
