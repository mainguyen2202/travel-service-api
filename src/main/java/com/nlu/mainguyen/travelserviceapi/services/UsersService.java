package com.nlu.mainguyen.travelserviceapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired 
    private UsersRepository repository;// new 

    // public UsersService(UsersRepository repository) {
    //     this.repository = repository;
    // }

    public List<Users> findPeople(Users probe) {
        return repository.findAll(Example.of(probe));
    }

    public List<Users> showAll() {
        Users person = new Users();                          
        person.setFirstname("Dave");  
        List<Users> items =  this.findPeople(person);
        System.out.println(items);

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

        List<Users> datas = this.repository.findAll(isLongTermCustomer());
        System.out.println(datas);

        Users person = new Users();                          
        person.setFirstname("Dave");                           
        ExampleMatcher matcher = ExampleMatcher.matching()     
        .withIgnorePaths("lastname")                         
        .withIncludeNullValues()                             
        .withStringMatcher(StringMatcher.ENDING);     
        Example<Users> example = Example.of(person, matcher); 

        Optional<Users> match = repository.findBy(example,
            q -> q
                .sortBy(Sort.by("lastname").descending())
                .first()
        );

        return null;
    }

    public static Specification<Users> isLongTermCustomer() {
        return (root, query, builder) -> {

            // TypedSort<Users> person = Sort.sort(Users.class);
            // Sort sort = person.by(Users::getFirstname).ascending()
            // .and(person.by(Users::getLastname).descending());

            LocalDate date = LocalDate.now().minusYears(2);
            return builder.lessThan(root.get("createAt"), date);
        };
    }

    public void update(Users input) {
        this.repository.save(input);
    }

    public void deleteByID(Long id) {
        this.repository.deleteById(id);
    }

    public Users getByName(String name) {
       return this.repository.findByName(name);
    }
}
