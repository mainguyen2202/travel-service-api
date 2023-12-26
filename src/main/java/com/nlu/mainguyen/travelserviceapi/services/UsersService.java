package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class UsersService {

    private UsersRepository repository;

    public UsersService(UsersRepository UsersRepository) {
        this.repository = UsersRepository;
    }

    public Iterable<Users> showAll() {
        return this.repository.findAll();
    }

    public Users create(Users input) {
        return this.repository.save(input);
    }

    public Users getById(Long id) {
        Optional<Users> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public void update(Users input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }
}
