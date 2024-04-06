package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.Itineraries;
import com.nlu.mainguyen.travelserviceapi.model.ItineraryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ItineraryArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;

@Service
public class ItineraryArticlesService {
  @Autowired
    private ItineraryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
     private final ArticlesRepository articlesRepository;
     private final ItinerariesRepository itinerariesRepository;

    public ItineraryArticlesService(ItineraryArticlesRepository repository, ArticlesRepository articlesRepository,ItinerariesRepository itinerariesRepository) {
        this.repository = repository;
        this.articlesRepository = articlesRepository;
        this.itinerariesRepository =itinerariesRepository;
    }


    public List<ItineraryArticles> listByItineraryId(long ItineraryArticles_id) {
        return this.repository.findAllByIdItinerary(ItineraryArticles_id);
      
    }
   
     public ResponseDTO create(ItineraryArticlesDTO dto) {
        try {
            ItineraryArticles itineraryArticles = modelMapper.map(dto, ItineraryArticles.class);// chuyển từ dto sang entity

            Optional<Articles> optional = articlesRepository.findById(dto.getArticles().getId());
            if (optional.isEmpty()) {
                return new ResponseDTO(2, "Articles not found");
            }
            Articles input = optional.get();
            itineraryArticles.setArticles(input);

            Optional<Itineraries> optionalItineraries = itinerariesRepository.findById(dto.getItineraries().getId());
            if (optionalItineraries.isEmpty()) {
                return new ResponseDTO(2, "Itineraries not found");
            }
            Itineraries itineraries = optionalItineraries.get();
            itineraryArticles.setItineraries(itineraries);

            ItineraryArticles created = this.repository.save(itineraryArticles);

            ItineraryArticlesDTO responseDto = modelMapper.map(created, ItineraryArticlesDTO.class);

            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

  
}
