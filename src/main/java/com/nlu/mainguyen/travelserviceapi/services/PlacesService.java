package com.nlu.mainguyen.travelserviceapi.services;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseInfoDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.PlacesRepository;

@Service
public class PlacesService {

    private PlacesRepository repository;

    public PlacesService(PlacesRepository repository) {
        this.repository = repository;
    }

    public Iterable<Places> showAll() {
        return this.repository.findAll();
    }

    public Iterable<Places> findByName(String name) {
        return this.repository.findByName(name);
    }

    public ResponseInfoDTO create(Places input) {
        Places result = this.repository.save(input);
        return new ResponseInfoDTO(1, "", result);
    }

    public ResponseInfoDTO getById(Long id) {
        Optional<Places> items = this.repository.findById(id);
        if (items.isPresent()) {
            Places result = items.get();
            return new ResponseInfoDTO(1, "", result);
        }
        return new ResponseInfoDTO(1, "", null);
    }

    public ResponseDTO update(Long id, Places input) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.save(input);
            return new ResponseDTO(1, "Thành công");
        } else {
            return new ResponseDTO(2, "Khong ton tai");
        }
    }

    public boolean deleteByID(Long id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Lấy danh sách con theo submenuid

}
