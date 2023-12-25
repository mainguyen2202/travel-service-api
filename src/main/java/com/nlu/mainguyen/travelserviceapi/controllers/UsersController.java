package com.nlu.mainguyen.travelserviceapi.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.services.UsersService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/users")
public class UsersController {
    private UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}
    
    @GetMapping("/list")
    public @ResponseBody Iterable<Users> showAllUsers(Model model) {
        return this.usersService.showAllUsers();
        
    }
      @PostMapping("/register")
    public @ResponseBody String registrationUsers(@RequestBody Users users) {
        // TODO
        Users result = this.usersService.createUsers(users);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody Users  viewUsersByID(@PathVariable("id") Long id) {
        Users users = usersService.getByIdUsers(id);
        return users;
    }
    @PostMapping("/edit/{id}")
    public @ResponseBody String editUsers(@PathVariable("id") Long id, @Valid @RequestBody Users updateCurrentUser) {
        usersService.updateUsers(updateCurrentUser);
        return "success";

    }
    @PostMapping("/remove/{id}")
    public String removeUsers(@PathVariable("id") Long id) {
        usersService.deleteByID(id);
        return "success";
    }
    
    
}
