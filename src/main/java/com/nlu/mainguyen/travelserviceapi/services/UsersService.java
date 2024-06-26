package com.nlu.mainguyen.travelserviceapi.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.PasswordResetToken;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ResetPasswordInputDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserInputDTO;
import com.nlu.mainguyen.travelserviceapi.model.UserOutputDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.PasswordResetTokenRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;
import com.nlu.mainguyen.travelserviceapi.util.GEmailSender;

import io.micrometer.common.util.StringUtils;

import java.util.Base64;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;// new

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private GEmailSender gEmailSender;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

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
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

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
            return null;
        }
    }

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
            } else {
                Users info = opt.get();
                info.setName(dto.getName());
                info.setEmail(dto.getEmail());
                info.setUsername(dto.getUsername());
                info.setStatus(dto.getStatus());
                info.setRole(dto.getRole());

                info.setCreateAt(dto.getCreateAt());
                info.setImage(dto.getImage());

                // info.setPassword(dto.getPassword());
                Users update = this.repository.save(info);
                UserOutputDTO responseDto = modelMapper.map(update, UserOutputDTO.class);
                return new ResponseDTO(1, "Update successfully", responseDto);
            }

        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO updateUser(long id, UserOutputDTO dto) {
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
            } else {
                Users info = opt.get();
                info.setName(dto.getName());
                info.setEmail(dto.getEmail());
                info.setUsername(dto.getUsername());
                info.setImage(dto.getImage());
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
            Optional<Users> opt = this.repository.findById(id);
            if (opt.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            } else {
                Users info = opt.get();
                // info.setPassword(dto.getPassword());
                info.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    public void createPasswordResetTokenForUser(Users user, String token) {
        // Xóa những dữ liệu cũ
        passwordTokenRepository.deleteByUserId(user.getId());

        // PasswordResetToken myToken = new PasswordResetToken(token, user, expiryDate);
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }



    public ResponseDTO forgotPassword(String email) {
        // Tìm người dùng bằng email
        Users user = this.repository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Tạo token
        String resetToken = UUID.randomUUID().toString();// tạo 1 token random
        // Khởi tạo dữ liệu token và ngày hết hạn của người dùng
        createPasswordResetTokenForUser(user, resetToken);

        // Mã hóa -> để bảo mật -> chuyển sang dùng SHA hoặc RSA
        String encryptedToken = Base64.getEncoder().encodeToString((resetToken).getBytes());

        // Tạo URL đặt lại mật khẩu
        String passwordResetUrl = "http://localhost:3000/ForgotPassword?token=" + encryptedToken;

        // Gửi email đặt lại mật khẩu cho người dùng
        String subject = "Password Reset Request";
        String text = "Please click the following link to reset your password: " + passwordResetUrl;
        gEmailSender.sendEmail(email, subject, text);

        // Trả về phản hồi
        return new ResponseDTO(1, "Password reset instructions have been sent to your email");
    }

    // Cách đơn giản
    public ResponseDTO validatePasswordResetTokenV1(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);// lấy thông tin
        if (passToken == null) {
            return new ResponseDTO(2, "invalidToken"); // không tồn tại
        } else {
            final Calendar cal = Calendar.getInstance();// now()
            if (passToken.getExpiryDate().before(cal.getTime())) {
                return new ResponseDTO(2, "expired"); // hết hạn
            }
        }
        return new ResponseDTO(1, "");// tồn tại và còn thời hạn
    }

    // Cách tối ưu
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);// lấy thông tin
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired" : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return (passToken != null);
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());// kiểm tra thời gian
    }

    public ResponseDTO resetPassword(ResetPasswordInputDTO dto) {
        // Token -> giải mã -> userid
        // String encryptedUserId = "&userId=123456789";
        byte[] decodedBytes = Base64.getDecoder().decode(dto.getToken().getBytes());
        String decryptedResetToken = new String(decodedBytes);
        if (!StringUtils.isEmpty(decryptedResetToken)) {

            String check = validatePasswordResetToken(decryptedResetToken);
            if (check != null) {
                return new ResponseDTO(2, check);// Câu thông báo lỗi
            } else {
                // Cập nhật mật khẩu của bảng user 
                PasswordResetToken passToken = passwordTokenRepository.findByToken(decryptedResetToken);
                if (passToken == null) {
                    return new ResponseDTO(2, "User not found");
                } else {
                    Users info = passToken.getUser();
                    info.setPassword(passwordEncoder.encode(dto.getPassword()));
                    Users update = this.repository.save(info);
                    UserOutputDTO responseDto = modelMapper.map(update, UserOutputDTO.class);
                    return new ResponseDTO(1, "Update successfully", responseDto);
                }
            }
        } else {
            return new ResponseDTO(2, "UserId is empty");
        }
    }

    // end
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
