package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ArticlesDTO { // Địa điểm
    private long id;

    private UserOutputDTO users;

    private PlacesDTO places; // vị trí cụ thể

    // Tọa độ chính xác của địa điểm
    private String longitude; // kinh độ
    private String latitude;// vĩ độ

    private TopicsDTO topics;// danh mục về món ăn

    private String name;
    private String title;
    private float price;
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
