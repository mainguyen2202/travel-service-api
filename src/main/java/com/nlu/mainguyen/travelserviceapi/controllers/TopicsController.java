package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.TopicsService;
import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.model.TopicsDTO;

import jakarta.validation.Valid;


@Controller

@RequestMapping(path="/topics")
public class TopicsController {
    @Autowired
    private TopicsService service;

 @Autowired
    private ModelMapper modelMapper;
    
 @GetMapping("/list")
    public @ResponseBody List<TopicsDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<TopicsDTO> results = this.service.getAll().stream().map(item -> modelMapper.map(item, TopicsDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @GetMapping("/list/{sub_topics_id}")
    public @ResponseBody
    List<TopicsDTO> showAllId(@PathVariable("sub_topics_id") int subTopicsId) {
        try {
            List<Topics> topics = service.getAllById(subTopicsId);

            List<TopicsDTO> results = topics.stream()
                    .map(topic -> modelMapper.map(topic, TopicsDTO.class))
                    .collect(Collectors.toList());

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

      @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Topics input) {
        // TODO
        Topics result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Topics  viewCommentsByID(@PathVariable("id") Long id) {
        Topics result = this.service.getById(id);
        return result;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String edit(@PathVariable("id") Long id, @Valid @RequestBody Topics input) {
        this.service.update(input);
        return "success";

    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        this.service.deleteByID(id);
        return "success";
    }
}
