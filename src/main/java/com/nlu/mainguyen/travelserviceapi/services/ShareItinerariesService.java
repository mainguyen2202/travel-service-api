package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.Util.GEmailSender;
import com.nlu.mainguyen.travelserviceapi.entities.ShareItineraries;
import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.model.ShareItinerariesDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ShareItinerariesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.UsersRepository;

@Service
public class ShareItinerariesService {
    @Autowired
    private ShareItinerariesRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private GEmailSender gEmailSender;

    // lấy danh sách theo id
    public List<ShareItineraries> listByUserId(long users_id) {// B2
        return this.repository.findByUsersId(users_id);
    }

    public ResponseDTO create(ShareItinerariesDTO dto) {
        try {
            ShareItineraries ent = modelMapper.map(dto, ShareItineraries.class);// chuyển từ dto sang
                                                                                // entity

            Optional<Users> userOptional = userRepository.findById(dto.getUsers().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Users user = userOptional.get();
            ent.setUsers(user);

            Optional<ShareItineraries> existingLikes = repository.findByUsersIdAndItinerariesId(user.getId(),
                    ent.getItineraries().getId());
            if (existingLikes.isPresent()) {
                return new ResponseDTO(2, "Itineraries and userId already exist");
            }

            ShareItineraries created = this.repository.save(ent);


            ShareItinerariesDTO responseDto = modelMapper.map(created, ShareItinerariesDTO.class);

           
             // Tạo URL đặt lại mật khẩu
        String passwordResetUrl = " http://localhost:3000/itinerarie";
    
            // Send email
            String to = userOptional.get().getEmail();
            String subject = "Hành trình mới được chia sẻ với bạn";
            String text = "Bạn của bạn đã chia sẻ hành trình với bạn. Làm ơn hãy kiểm tra nó."+passwordResetUrl;
            gEmailSender.sendEmail(to, subject, text);

            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    // Send mail theo return "newpassword123";

    // public ResponseDTO forgotPassword(String email) {
    // // Find the user by email
    // Users user = this.repository.findByEmail(email);
    // if (user == null) {
    // throw new RuntimeException("User not found");
    // }

    // // Generate a new password and update the user's password
    // String newPassword = generateNewPassword();
    // user.setPassword(newPassword);
    // this.repository.save(user);

    // // Send the new password to the user's email
    // String subject = "Your new password";
    // String text = "Your new password is: " + newPassword;
    // gEmailSender.sendEmail(email, "trucmainguyen02@gmail.com", subject, text);

    // // Return the response
    // return new ResponseDTO(1, "New password sent to your email");
    // }

    // private String generateNewPassword() {
    // // Generate a new password here
    // return "Bạn nhận được kế hoạch từ bạn bè ";
    // }

}
