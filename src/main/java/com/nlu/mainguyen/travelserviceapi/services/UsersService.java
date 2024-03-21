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
import com.nlu.mainguyen.travelserviceapi.exception.ResourceNotFoundException;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;// new

    // public UsersService(UsersRepository repository) {
    // this.repository = repository;
    // }

    // lấy danh sách
    public List<Users> getAll() {
        return repository.findAll();
    }
     // tạo
    public Users create(Users input) {
        return this.repository.save(input);
    }

    // update
    public Users update(long id, Users userRequest) {
        Users user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));

        if (userRequest.getLastname() != null) {
            user.setLastname(userRequest.getLastname());
        }
        if (userRequest.getFirstname() != null) {
            user.setFirstname(userRequest.getFirstname());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }

        return repository.save(user);
    }

    // delete

    public void deleteByID(long id) {

        Users user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));

        this.repository.delete(user);
    }



    public Users getById(Long id) {
        Optional<Users> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }else {
			throw new ResourceNotFoundException("Post", "id", id);
		}
    }


    public Users getByName(String name) {
        return this.repository.findByName(name);
    }





   // danh sách người ten dave
    // public List<Users> showAll() {
    //     Users person = new Users();
    //     person.setFirstname("Dave");
    //     List<Users> items = this.findPeople(person);
    //     System.out.println(items);

    //     return this.repository.findAll();
    // }
    // public Users getById(Long id) {
    //     Optional<Users> items = this.repository.findById(id);
    //     if (items.isPresent()) {
    //         return items.get();
    //     }else {
	// 		throw new ResourceNotFoundException("Post", "id", id);
	// 	}

    //     List<Users> datas = this.repository.findAll(isLongTermCustomer());
    //     System.out.println(datas);

    //     Users person = new Users();
    //     person.setFirstname("Dave");
    //     ExampleMatcher matcher = ExampleMatcher.matching()
    //             .withIgnorePaths("lastname")
    //             .withIncludeNullValues()
    //             .withStringMatcher(StringMatcher.ENDING);
    //     Example<Users> example = Example.of(person, matcher);

    //     Optional<Users> match = repository.findBy(example,
    //             q -> q
    //                     .sortBy(Sort.by("lastname").descending())
    //                     .first());

    //     return null;
    // }
 


    public static Specification<Users> isLongTermCustomer() {
        return (root, query, builder) -> {

            // TypedSort<Users> person = Sort.sort(Users.class);
            // Sort sort = person.by(Users::getFirstname).ascending()
            // .and(person.by(Users::getLastname).descending());

            LocalDate date = LocalDate.now().minusYears(2);
            return builder.lessThan(root.get("createAt"), date);
        };
    }


}
