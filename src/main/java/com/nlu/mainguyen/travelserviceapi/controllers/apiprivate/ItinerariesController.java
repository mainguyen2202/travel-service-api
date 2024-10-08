package com.nlu.mainguyen.travelserviceapi.controllers.apiprivate;

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

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.model.ItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.services.ItinerariesService;

@Controller
@RequestMapping(path = "/private/itineraries")
public class ItinerariesController {
    @Autowired
    private ItinerariesService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public @ResponseBody List<ItinerariesDTO> showAll(Model model) {
        try {
            // List<Users> users = this.service.showAll();// danh sách Entity mà cần convert
            // về DTO thì stream().map()
            // tương đương for

            List<ItinerariesDTO> results = this.service.getAll().stream()
                    .map(item -> modelMapper.map(item, ItinerariesDTO.class))
                    .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/listBySearch")
    public @ResponseBody List<ItinerariesDTO> listBySearch(@RequestParam("user_id") long user_id) {// B3
        try {
            List<Itineraries> itineraries = this.service.listByUserId(user_id);

            List<ItinerariesDTO> results = itineraries.stream()
                    .map(item -> modelMapper.map(item, ItinerariesDTO.class))
                    .collect(Collectors.toList());// B4

            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/create")

    public ResponseEntity<ResponseDTO> create(@RequestBody ItinerariesDTO request) {
        try {
            ResponseDTO response = this.service.create(request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ItinerariesDTO> viewByID(@PathVariable("id") Long id) {
        try {
            Itineraries i = this.service.getById(id);

            ItinerariesDTO resp = modelMapper.map(i, ItinerariesDTO.class);
            return ResponseEntity.ok().body(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<ItinerariesDTO>(new ItinerariesDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit/{id}")
      public ResponseEntity<ResponseDTO> update(@PathVariable long id,@RequestBody ItinerariesDTO request) {
        try {
            ResponseDTO response = this.service.update(id, request);// lưu database, trả về id
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(2, e.getMessage());
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
        }
    }
    @PostMapping("/admin/edit/{id}")
    public ResponseEntity<ResponseDTO> updateAdmin(@PathVariable long id,@RequestBody ItinerariesDTO request) {
      try {
          ResponseDTO response = this.service.updateAdmin(id, request);// lưu database, trả về id
          return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
      } catch (Exception e) {
          ResponseDTO response = new ResponseDTO(2, e.getMessage());
          return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);// OK : 200, 201
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
