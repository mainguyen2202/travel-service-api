package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Articles {// bài viết của nhiều địa điểm và nhiều topics

    private @Id @GeneratedValue Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Places places; // vị trí cụ thể

    // Tọa độ chính xác của địa điểm
    private String longitude; // kinh độ
    private String latitude;// vĩ độ

    @ManyToOne()
    private Topics topics;// danh mục về món ăn

    private String name;
    private String title;
    private float price;

    @Column(columnDefinition = "LONGTEXT")
    private String image;

    private Date createAt;

    // @JsonIgnore
    // private final LocalDateTime editedAt = LocalDateTime.now();
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private int status;

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<HisArticles> historyArticles = new ArrayList<>(); // quản lí cả danh sách người click xem => count(1)

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Feedbacks> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<ItineraryArticles> itineraries = new ArrayList<>();

}
