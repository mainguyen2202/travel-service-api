package com.nlu.mainguyen.travelserviceapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topics {// danh mục
    private @Id @GeneratedValue Long id;

    private String title;//Thiên nhiên và thế giới bên ngoài, Truyền thống
    
    private String content;
    private int status;
    private int subTopicsId;

    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();

    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    private List<Articles> articles = new ArrayList<>();



}
