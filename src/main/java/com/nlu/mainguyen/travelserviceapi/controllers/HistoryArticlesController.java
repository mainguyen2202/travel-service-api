package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.nlu.mainguyen.travelserviceapi.entities.HisArticles;
import com.nlu.mainguyen.travelserviceapi.model.HistoryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.HistoryArticlesService;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping(path="/historyArticles")
public class HistoryArticlesController {
    private HistoryArticlesService service;

	public HistoryArticlesController(HistoryArticlesService service) {
		this.service = service;
	}
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<HistoryArticlesDTO> showAll(Model model) {
        try {
     
            List<HistoryArticlesDTO> results = this.service.getAll().stream()
            .map(item-> modelMapper.map(item, HistoryArticlesDTO.class))
            .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @GetMapping("/listByIdArticles")
    // public @ResponseBody List<HistoryArticlesDTO> listBySearch(@RequestParam("articles_id")  long articles_id   ) {// B3
    //     try {
    //         List<HisArticles> ltsHisArticles = this.service.listByItineraryId(articles_id);

    //         List<HistoryArticlesDTO> results = ltsHisArticles.stream()
    //                 .map(item -> modelMapper.map(item, HistoryArticlesDTO.class))
    //                 .collect(Collectors.toList());// B4

    //         return results;
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return null;
    // }
    public @ResponseBody HistoryArticlesDTO getBySearch(@RequestParam("articles_id") long articles_id) {
        try {
            HisArticles hisArticles = this.service.listByItineraryId(articles_id);
            
            if (hisArticles != null) {
                HistoryArticlesDTO result = modelMapper.map(hisArticles, HistoryArticlesDTO.class);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây hoặc ném ngoại lệ để xử lý ở tầng trên
        }
        return null;
    }
 
    @PostMapping("/edit")
      public ResponseEntity<ResponseDTO> update(@RequestParam("articles_id") long articles_id,
      @RequestBody HistoryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.update(articles_id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
    @PostMapping("/create")

    public ResponseEntity<ResponseDTO> create(@RequestBody HistoryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
    
}
