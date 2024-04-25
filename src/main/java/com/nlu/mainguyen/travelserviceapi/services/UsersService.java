package com.nlu.mainguyen.travelserviceapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.exception.ResourceNotFoundException;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserInputDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserOutputDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;// new

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    // lấy danh sách
    public List<Users> getAll() {
        return repository.findAll();
    }

    // tạo
    public Users create(Users input) {
        return this.repository.save(input);
    }

    public ResponseDTO registration(UserInputDTO userDto) {
        try {
            // convert DTO to entity
            Users userEntity = modelMapper.map(userDto, Users.class);

            // Kiểm tra sự tồn tại của người dùng bằng username
            if (this.repository.findByUsername(userEntity.getUsername()) != null) {
                return new ResponseDTO(2, "Username already exists");
            }

            // Kiểm tra sự tồn tại của người dùng bằng email
            if (this.repository.findByEmail(userEntity.getEmail()) != null) {
                return new ResponseDTO(2, "Email already exists");
            }

            // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            // String encodedPassword = passwordEncoder.encode(input.getPassword());
            // input.setPassword(encodedPassword);

            // Lưu người dùng vào cơ sở dữ liệu
            Users createdUser = this.repository.save(userEntity);// lưu thành công và có id định danh

            // Tạo đối tượng chứa thông tin người dùng và thông báo thành công
            // convert entity to DTO
            UserOutputDTO userResponse = modelMapper.map(createdUser, UserOutputDTO.class);

            return new ResponseDTO(1, "User created successfully", userResponse);
        } catch (Exception e) {
            String errorMessage = "Failed to register user: " + e.getMessage();
            return new ResponseDTO(2, errorMessage);
        }
    }

    public ResponseDTO login(String username, String password) {
        // Kiểm tra sự tồn tại của người dùng bằng username
        Users getUser = this.repository.findOneByUsernameOrEmail(username, username);
        if (getUser == null) {
            return new ResponseDTO(2, "Không tồn tại tên đăng nhập");
        }
        // Kiểm tra tính chính xác của mật khẩu
        if (!getUser.getPassword().equals(password)) {
            return new ResponseDTO(2, "Không đúng mật khẩu");
        }
        // Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        // if (!isPwdRight) {
        // return new ResponseDTO(2, "Không đúng mật khẩu");
        // }

        UserOutputDTO userResponse = modelMapper.map(getUser, UserOutputDTO.class);
        return new ResponseDTO(1, "", userResponse);
    }

    // update
    public Users update(long id, Users userRequest) {
        Users user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));

        if (userRequest.getName() != null) {
            user.setName(userRequest.getName());
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

    public Users getById(long id) {
        Optional<Users> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    public Users detailBySearch(String username, String email, int role) {
        if (username!= ""){
            return this.repository.findByName(username); // Tìm người dùng theo tên người dùng trong cơ sở dữ liệu
        } else if (email!= ""){
            return this.repository.findByName(username); // tìm theo email
        } else if (role != 0){
            return this.repository.findByName(username); // tìm theo role
        } 
        return null;
    }
    public Users detailBySearchUserName(String username) {
        if (username!= ""){
            return this.repository.findByName(username); // Tìm người dùng theo tên người dùng trong cơ sở dữ liệu
        }
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

}
