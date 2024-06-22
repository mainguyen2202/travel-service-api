package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;
import com.nlu.mainguyen.travelserviceapi.model.ItineraryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.PlacesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.ItineraryArticlesService;

@Controller
@RequestMapping(path = "/public/itineraryArticles")
public class ItineraryArticlesPublicController {
    @Autowired
    private ItineraryArticlesService service;

    @Autowired
    private ModelMapper modelMapper;

      @GetMapping("/list")
    public @ResponseBody List<ItineraryArticlesDTO> showAll(Model model) {
        try {
           

            List<ItineraryArticlesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, ItineraryArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody ItineraryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    // GET
    // http://127.0.0.1:8080/itineraryArticles/listBySearch?date_start=2024-04-19&itineraries_id=1
    @GetMapping("/listBySearch")
    public @ResponseBody List<ItineraryArticlesDTO> listBySearch(@RequestParam("itineraries_id") long itineraries_id,
            @RequestParam("date_start") String date_start
            // convert String to Date
            // @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_start
            // @RequestParam("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_start
            ) {// B3
        try {
            List<ItineraryArticles> ltsItineraryArticles = this.service.listByItineraryId(itineraries_id, date_start);

            List<ItineraryArticlesDTO> results = ltsItineraryArticles.stream()
                    .map(item -> modelMapper.map(item, ItineraryArticlesDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable long id, @RequestBody ItineraryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.update(id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ItineraryArticlesDTO> viewByID(@PathVariable("id") Long id) {
        try {
            ItineraryArticles i = this.service.getById(id);

            ItineraryArticlesDTO resp = modelMapper.map(i, ItineraryArticlesDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<ItineraryArticlesDTO>(new ItineraryArticlesDTO(), HttpStatus.BAD_REQUEST);
        }
    }
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
