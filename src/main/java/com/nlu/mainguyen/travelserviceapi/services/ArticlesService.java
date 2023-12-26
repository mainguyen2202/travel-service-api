package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesReponsitory;


@Service
public class ArticlesService {
       private ArticlesReponsitory articlesRepository;

	public ArticlesService(ArticlesReponsitory articlesRepository) {
		this.articlesRepository = articlesRepository;
	}
      public Iterable<Articles> showAllArticles(){
        return this.articlesRepository.findAll();
    }
    public Articles createArticles(Articles newArticles){
        return this.articlesRepository.save(newArticles);
    }
    public Articles getByIdArticles(Long id){
      Optional<Articles> aArticles = this.articlesRepository.findById(id);
        if(aArticles.isPresent()) {
            return aArticles.get();
        }
        return null;
    }
    public void updateArticles(Articles newArticles) {
        this.articlesRepository.save(newArticles);
    }
    public void deleteByID(Long id) {
        this.articlesRepository.deleteById(id);
    }
}
