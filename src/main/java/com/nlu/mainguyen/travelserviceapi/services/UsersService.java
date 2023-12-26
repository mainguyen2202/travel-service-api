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
        return this.usersRepository.findAll();
    }
    public Users createUsers(Users newUsers){
        return this.usersRepository.save(newUsers);
    }
    public Users getByIdUsers(Long id){
      Optional<Users> aUsers = this.usersRepository.findById(id);
        if(aUsers.isPresent()) {
            return aUsers.get();
        }
        return null;
    }
    public void updateUsers(Users newUsers) {
        this.usersRepository.save(newUsers);
    }
    public void deleteByID(Long id) {
        this.usersRepository.deleteById(id);
    }
    
}
