package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    private @Id @GeneratedValue Long id;

    private String name;
    private String email;
    private String username;
    private String password;
    private String image;
    private int status;
    private int role;
    private Date createAt;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Articles> articles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Itineraries> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Feedbacks> feedbacks = new ArrayList<>();   

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ShareItineraries> shareItineraries = new ArrayList<>();  
}
