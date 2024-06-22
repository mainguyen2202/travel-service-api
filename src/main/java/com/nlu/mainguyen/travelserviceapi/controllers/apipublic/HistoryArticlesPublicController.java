package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.nlu.mainguyen.travelserviceapi.model.HistoryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.HistoryArticlesService;

@Controller
@RequestMapping(path = "/public/historyArticles")
public class HistoryArticlesPublicController {
    private HistoryArticlesService service;

    public HistoryArticlesPublicController(HistoryArticlesService service) {
        this.service = service;
    }

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<HistoryArticlesDTO> showAll(Model model) {
        try {

            List<HistoryArticlesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, HistoryArticlesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // @GetMapping("/getByIdArticles")
    // public ResponseEntity<ResponseDTO> getByIdArticles(@RequestParam("articles_id") long articles_id) {
    //     try {
    //         ResponseDTO response = this.service.getByIdArticles(articles_id);
    //         return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    //     } catch (Exception e) {
    //         ResponseDTO response = new ResponseDTO(2, e.getMessage());
    //         return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    //     }
    // }

     @GetMapping("/getByIdArticles")
    public @ResponseBody ResponseDTO getByIdArticles(@RequestParam("articles_id") long articles_id) {// B3
        try {
            ResponseDTO response = this.service.getByIdArticles(articles_id);
            return response;
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return response;
        }
    }

    @PostMapping("/clickView")
    public ResponseEntity<ResponseDTO> clickView(@RequestBody HistoryArticlesDTO request) {
        try {
            ResponseDTO response = this.service.clickView(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

}
