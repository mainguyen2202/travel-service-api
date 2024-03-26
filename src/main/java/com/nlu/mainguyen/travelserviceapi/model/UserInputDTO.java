package com.nlu.mainguyen.travelserviceapi.model;

import lombok.*;

@Data
public class UserInputDTO { // request <> input
    private String name;
    private String email;
    private String username;
    private String password;
}
