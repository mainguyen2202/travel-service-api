package com.nlu.mainguyen.travelserviceapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.entities.Places;
import com.nlu.mainguyen.travelserviceapi.entities.Topics;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.exception.ResourceNotFoundException;
import com.nlu.mainguyen.travelserviceapi.model.ArticlesDTO;
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

    public Users getById(long id) {
        Optional<Users> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }
    // update
    // public Users update(long id, Users userRequest) {
    // Users user = repository.findById(id).orElseThrow(() -> new
    // ResourceNotFoundException("Users", "id", id));

    // if (userRequest.getName() != null) {
    // user.setName(userRequest.getName());
    // }

    // if (userRequest.getEmail() != null) {
    // user.setEmail(userRequest.getEmail());
    // }
    // if (userRequest.getUsername() != null) {
    // user.setUsername(userRequest.getUsername());
    // }
    // user.setStatus(userRequest.getStatus());
    // user.setRole(userRequest.getRole());
    // user.setCreateAt(userRequest.getCreateAt());
    // user.setImage(userRequest.getImage());

    // return repository.save(user);
    // }
    public ResponseDTO update(long id, UserOutputDTO dto) {
        try {
            Users user = modelMapper.map(dto, Users.class); // chuyển từ dto sang entity
            if (dto.getName() != null) {
                user.setName(dto.getName());
            }

            if (dto.getEmail() != null) {
                user.setEmail(dto.getEmail());
            }
            if (dto.getUsername() != null) {
                user.setUsername(dto.getUsername());
            }
            
            Optional<Users> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }else {
                Users info = opt.get();
                info.setName(dto.getName());
                info.setEmail(dto.getEmail());
                info.setUsername(dto.getUsername());
                info.setStatus(dto.getStatus());
                info.setRole(dto.getRole());
                info.setCreateAt(dto.getCreateAt());
                info.setImage(dto.getImage());

                info.setPassword(dto.getPassword());
                Users update = this.repository.save(info);
                UserOutputDTO responseDto = modelMapper.map(update, UserOutputDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }           



        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }
    public ResponseDTO updatePassword(long id, UserOutputDTO dto) {
        try {
            Users user = modelMapper.map(dto, Users.class); // chuyển từ dto sang entity
       
            
            Optional<Users> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }else {
                Users info = opt.get();
              
                info.setPassword(dto.getPassword());
                Users update = this.repository.save(info);
                UserOutputDTO responseDto = modelMapper.map(update, UserOutputDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }           



        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    // delete
    public ResponseDTO deleteByID(Long id) {
        Optional<Users> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }

    public Users detailBySearch(String username, String email, int role) {
        if (username != "") {
            return this.repository.findByName(username); // Tìm người dùng theo tên người dùng trong cơ sở dữ liệu
        } else if (email != "") {
            return this.repository.findByName(username); // tìm theo email
        } else if (role != 0) {
            return this.repository.findByName(username); // tìm theo role
        }
        return null;
    }

    public Users detailBySearchUserName(String username) {
        if (username != "") {
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
