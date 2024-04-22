package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Likes;
import com.nlu.mainguyen.travelserviceapi.repositories.LikesRepository;

@Service
public class LikesService {

    private LikesRepository repository;

    public LikesService(LikesRepository repository) {
        this.repository = repository;
    }

    public Iterable<Likes> showAll() {
        return this.repository.findAll();
    }

    // lấy danh sách theo id
    public List<Likes> listByUserId(long users_id) {// B2
        return this.repository.findAllByUserId(users_id);
    }


}
