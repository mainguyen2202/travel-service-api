package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;
import com.nlu.mainguyen.travelserviceapi.model.FeedbacksDTO;
import com.nlu.mainguyen.travelserviceapi.model.FeedbacksDTO;
import com.nlu.mainguyen.travelserviceapi.model.FeedbacksDTO;
import com.nlu.mainguyen.travelserviceapi.model.FeedbacksDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.FeedbacksService;


@Controller
@RequestMapping(path = "/public/feedbacks")
public class FeedbacksPublicController {
    private FeedbacksService service;

    @Autowired
    private ModelMapper modelMapper;

    public FeedbacksPublicController(FeedbacksService service) {
        this.service = service;
    }

     @GetMapping("/listAll")
    public @ResponseBody List<FeedbacksDTO> showAll(Model model) {
        try {
            // List<Articles> Articles = this.service.showAll();// danh sách Entity mà cần
            // convert
            // về DTO thì stream().map()
            // tương đương for

            List<FeedbacksDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, FeedbacksDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/listBySearch")
    public @ResponseBody List<FeedbacksDTO> listBySearch(@RequestParam("articles_id") long articlesId) {// B3
        try {
            List<Feedbacks> ltsFeedbacks = this.service.listByArticlesId(articlesId);

            List<FeedbacksDTO> results = ltsFeedbacks.stream()
                    .map(item -> modelMapper.map(item, FeedbacksDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/listByHeart")
    public @ResponseBody List<FeedbacksDTO> listByHeart(@RequestParam("articles_id") long articlesId,
            @RequestParam("heart") int heart) {// B3
        try {
            List<Feedbacks> ltsFeedbacks = this.service.listByHeart(heart,articlesId);

            List<FeedbacksDTO> results = ltsFeedbacks.stream()
                    .map(item -> modelMapper.map(item, FeedbacksDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody FeedbacksDTO request) {
        try {

            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

        @GetMapping("/detail/{id}")
    public ResponseEntity<FeedbacksDTO> viewByID(@PathVariable("id") Long id) {
        try {
            Feedbacks i = this.service.getById(id);

            FeedbacksDTO resp = modelMapper.map(i, FeedbacksDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<FeedbacksDTO>(new FeedbacksDTO(), HttpStatus.BAD_REQUEST);
        }
    }
    
       @PostMapping("/edit/{id}")
      public ResponseEntity<ResponseDTO> update(@PathVariable long id,@RequestBody FeedbacksDTO request) {
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
