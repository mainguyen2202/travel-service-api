package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.repositories.PlacesRepository;
@Service
public class PlacesService {
    
  private PlacesRepository placesRepository;

	public PlacesService(PlacesRepository PlacesRepository) {
		this.placesRepository = PlacesRepository;
	}
      public Iterable<Places> showAllPlaces(){
        return placesRepository.findAll();
    }
    public Places createPlaces(Places newPlaces){
        return placesRepository.save(newPlaces);
    }
    public Places getByIdPlaces(Long id){
      Optional<Places> aPlaces = placesRepository.findById(id);
        if(aPlaces.isPresent()) {
            return aPlaces.get();
        }
        return null;
    }
    public void updatePlaces(Places newPlaces) {
        placesRepository.save(newPlaces);
    }
    public void deleteByID(Long id) {
        placesRepository.deleteById(id);
    }

    
}
