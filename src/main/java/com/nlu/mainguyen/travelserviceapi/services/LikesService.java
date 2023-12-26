package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.repositories.LikesRepository;
@Service
public class LikesService {
    
  private LikesRepository likesRepository;

	public LikesService(LikesRepository likesRepository) {
		this.likesRepository = likesRepository;
	}
      public Iterable<Likes> showAllLikes(){
        return this.likesRepository.findAll();
    }
    public Likes createLikes(Likes newLikes){
        return this.likesRepository.save(newLikes);
    }
    public Likes getByIdLikes(Long id){
      Optional<Likes> aLikes = this.likesRepository.findById(id);
        if(aLikes.isPresent()) {
            return aLikes.get();
        }
        return null;
    }
    public void updateLikes(Likes newLikes) {
        this.likesRepository.save(newLikes);
    }
    public void deleteByID(Long id) {
        this.likesRepository.deleteById(id);
    }

    
}
