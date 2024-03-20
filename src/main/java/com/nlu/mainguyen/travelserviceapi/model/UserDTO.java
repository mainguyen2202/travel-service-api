package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
public class UserDTO {
    private long id;// Long

    private String lastname;
    private String firstname;
    private String email;
    private String username;
    private String password;
    private String image;
    private int status;// Interger
    private int role;
    private Date createAt;

    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    // private List<Likes> likes = new ArrayList<>();

    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    // private List<Articles> articles = new ArrayList<>();

    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ItinerariesDTO> itineraries;

    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    // private List<Feedbacks> feedbacks = new ArrayList<>();   
}
