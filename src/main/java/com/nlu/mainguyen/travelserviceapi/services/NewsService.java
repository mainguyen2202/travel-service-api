package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.News;
import com.nlu.mainguyen.travelserviceapi.repositories.NewsRepository;
@Service
public class NewsService {
    
  private NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
      public Iterable<News> showAllNews(){
        return this.newsRepository.findAll();
    }
    public News createNews(News newNews){
        return this.newsRepository.save(newNews);
    }
    public News getByIdNews(Long id){
      Optional<News> aNews = this.newsRepository.findById(id);
        if(aNews.isPresent()) {
            return aNews.get();
        }
        return null;
    }
    public void updateNews(News newNews) {
        this.newsRepository.save(newNews);
    }
    public void deleteByID(Long id) {
        this.newsRepository.deleteById(id);
    }

    
}

