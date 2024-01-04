package com.nlu.mainguyen.travelserviceapi.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Places { // Địa điểm
    private @Id @GeneratedValue Long id;
    
    private int subPlaceId;  // Địa điểm cha
    
    @ManyToOne()
    private Coordinates coordinates; // Tọa độ
    
    private String name;
    private String image;
    private String content;
    private int status;

    @OneToMany(mappedBy = "places", cascade = CascadeType.ALL)
    private List<Articles> articles = new ArrayList<>();

    public Places() {
    }

    public Places(int subPlaceId, String name, String image, String content, int status) {
        this.subPlaceId = subPlaceId;
        this.name = name;
        this.image = image;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSubPlaceId() {
        return subPlaceId;
    }

    public void setSubPlaceId(int subPlaceId) {
        this.subPlaceId = subPlaceId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

   

}
