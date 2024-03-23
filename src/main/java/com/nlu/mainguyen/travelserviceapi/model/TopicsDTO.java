package com.nlu.mainguyen.travelserviceapi.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "topics")
public class TopicsDTO {// danh mục
    private long id;

    private String title;//Thiên nhiên và thế giới bên ngoài, Truyền thống
    
    private String content;
    private int status;
    private int subTopicsId;

    // @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    // private List<News> news = new ArrayList<>();

    // @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    // private List<Articles> articles = new ArrayList<>();



}
