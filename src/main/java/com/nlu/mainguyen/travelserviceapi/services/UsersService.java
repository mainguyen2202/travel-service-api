package com.nlu.mainguyen.travelserviceapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class UsersService {
    private UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
      public Iterable<Users> showAllUsers(){
        return usersRepository.findAll();
    }
    public Users createUsers(Users newUsers){
        return usersRepository.save(newUsers);
    }
    public Users getByIdUsers(Long id){
      Optional<Users> ausers = usersRepository.findById(id);
        if(ausers.isPresent()) {
            return ausers.get();
        }
        return null;
    }
    public void updateUsers(Users newUsers) {
        usersRepository.save(newUsers);
    }
    public void deleteByID(Long id) {
        usersRepository.deleteById(id);
    }
    
}
