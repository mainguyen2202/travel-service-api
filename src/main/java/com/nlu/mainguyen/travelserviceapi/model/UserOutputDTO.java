package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.*;

@Data
public class UserOutputDTO { // response <> output
    private long id;// Long

    private String name;
    private String email;
    private String username;
    private String password;
    private String image;
    private int status;// Interger
    private int role;
    private Date createAt; 
}