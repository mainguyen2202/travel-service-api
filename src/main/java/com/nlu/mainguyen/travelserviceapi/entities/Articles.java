package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    @ManyToOne()
    private Topics topics;//danh mục về món ăn

    private String title;
    private String image;
    private Date createAt;
    private String content;
    private int status;

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Itineraries> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Feedbacks> feedbacks = new ArrayList<>();

    public static Stream<Articles> stream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stream'");
    }

  

}
