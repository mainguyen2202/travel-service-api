package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;

@Service
public class ItinerariesService {
  @Autowired
    private ItinerariesRepository repository;

   

    public List<Itineraries> getAll() {
        return this.repository.findAll();
    }

 
// lấy danh sách theo id
    public List<Itineraries> listByUsersId(long users_id) {//B2
        return this.repository.findAllByUsersId(users_id);
    }

    public Itineraries create(Itineraries input) {
        return this.repository.save(input);
    }

    public Itineraries getById(Long id) {
        Optional<Itineraries> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Itineraries input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
