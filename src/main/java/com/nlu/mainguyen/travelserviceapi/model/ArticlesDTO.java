package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
public class ArticlesDTO { // Địa điểm
    private long id;

    @ManyToOne()
    private UserDTO users;

    @ManyToOne()
    private PlacesDTO places; // vị trí cụ thể

    @ManyToOne()
    private TopicsDTO topics;// danh mục về món ăn

    private String title;
    private String image;
    private Date createAt;
    private String content;
    private int status;


    // @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    // private List<Likes> likes = new ArrayList<>();

    // @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    // private List<Itineraries> itineraries = new ArrayList<>();

    // @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    // private List<Feedbacks> feedbacks = new ArrayList<>();

}
