package com.nlu.mainguyen.travelserviceapi.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
public class PlacesDTO { // Địa điểm
    private long id;
    
    private int subPlaceId;  // Địa điểm cha
    
    // @ManyToOne()
    // private CoordinatesDTO coordinates; // Tọa độ
    
    private String name;
    private String image;
    private String content;
    private int status;

    // @OneToMany(mappedBy = "places", cascade = CascadeType.ALL)
    // private List<Articles> articles = new ArrayList<>();

   

}
