package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nlu.mainguyen.travelserviceapi.services.ArticlesService;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;

import jakarta.validation.Valid;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/temp")
    public @ResponseBody List<ArticlesDTO> showAll(Model model) {
        try {
            // List<Articles> Articles = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<ArticlesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, ArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/list")
    public @ResponseBody List<ArticlesDTO> getArticlesBySearch(@RequestParam("places_id") long places_id,
            @RequestParam("topics_id") long topics_id) {

        try {
            List<Articles> articles = service.listAllBySearch(places_id, topics_id);

            List<ArticlesDTO> results = articles.stream()
                    .map(item -> modelMapper.map(item, ArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return new ArrayList<ArticlesDTO>();
    }
    @GetMapping("/listDate")
    public @ResponseBody List<ArticlesDTO> showAllDescDate(Model model) {
        try {
            // List<Articles> Articles = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<ArticlesDTO> results = this.service.getAllDescDate().stream()
                    .map(item -> modelMapper.map(item, ArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody ArticlesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
    

    @GetMapping("/detail/{id}")
    public ResponseEntity<ArticlesDTO> viewByID(@PathVariable("id") Long id) {
        try {
            Articles i = this.service.getById(id);

            ArticlesDTO resp = modelMapper.map(i, ArticlesDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<ArticlesDTO>(new ArticlesDTO(), HttpStatus.BAD_REQUEST);
        }
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
