package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.repositories.TopicsRepository;
@Service
public class TopicsService {
    
  private TopicsRepository topicsRepository;

	public TopicsService(TopicsRepository topicsRepository) {
		this.topicsRepository = topicsRepository;
	}
      public Iterable<Topics> showAllTopics(){
        return this.topicsRepository.findAll();
    }
    public Topics createTopics(Topics newTopics){
        return this.topicsRepository.save(newTopics);
    }
    public Topics getByIdTopics(Long id){
      Optional<Topics> aTopics = this.topicsRepository.findById(id);
        if(aTopics.isPresent()) {
            return aTopics.get();
        }
        return null;
    }
    public void updateTopics(Topics newTopics) {
        this.topicsRepository.save(newTopics);
    }
    public void deleteByID(Long id) {
        this.topicsRepository.deleteById(id);
    }

    
}
