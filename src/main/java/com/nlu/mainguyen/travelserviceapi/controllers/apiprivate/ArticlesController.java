package com.nlu.mainguyen.travelserviceapi.controllers.apiprivate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseListDTO;
import com.nlu.mainguyen.travelserviceapi.services.ArticlesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/private/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/listAll")
    public @ResponseBody List<ArticlesDTO> showAll(Model model) {
        try {
            // List<Articles> Articles = this.service.showAll();// danh sách Entity mà cần
            // convert
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
    // @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ArticlesDTO> getArticlesBySearch(@RequestParam("places_id") long places_id,
            @RequestParam("topics_id") long topics_id) {

        try {
            List<Articles> articles = service.listAllBySearch(places_id, topics_id);

            List<ArticlesDTO> results = articles.stream()
                    .map(item -> modelMapper.map(item, ArticlesDTO.class))
                    .collect(Collectors.toList());

            // ObjectMapper mapper = new ObjectMapper();
            // String convert = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
            // System.out.println(convert);

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<ArticlesDTO>();
    }

   
    @GetMapping("/listDate")
    public @ResponseBody List<ArticlesDTO> showAllDescDate(Model model) {
        try {
            // List<Articles> Articles = this.service.showAll();// danh sách Entity mà cần
            // convert
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

    @GetMapping("/listSearchKeyWord")
    public @ResponseBody List<ArticlesDTO> getArticlesBySearchName(@RequestParam("name") String name) {

        try {
            List<Articles> articles = service.detailBySearchName(name);

            List<ArticlesDTO> results = articles.stream()
                    .map(item -> modelMapper.map(item, ArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<ArticlesDTO>();
    }


    @PostMapping("/edit/{id}")
      public ResponseEntity<ResponseDTO> update(@PathVariable long id,@RequestBody ArticlesDTO request) {
        try {
            ResponseDTO response = this.service.update(id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }}


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
        try {
            ResponseDTO response = this.service.deleteByID(id);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
}
