package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.ArticlesService;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;

import jakarta.validation.Valid;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<ArticlesDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert về DTO thì stream().map()
                                                       // tương đương for

            List<ArticlesDTO> results = this.service.getAll().stream().map(i -> modelMapper.map(i, ArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;

            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @PostMapping("/create")
    public @ResponseBody String registration(@RequestBody Articles input) {
        // TODO
        Articles result = this.service.create(input);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Articles viewCommentsByID(@PathVariable("id") Long id) {
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
