package com.nlu.mainguyen.travelserviceapi.model;

import lombok.*;
import java.util.Date;
@Data
public class UserInputDTO { // request <> input
    private String name;
    private String email;
    private String username;
    private String password;
    private int role;
    private int status;
    private Date createAt;
}
