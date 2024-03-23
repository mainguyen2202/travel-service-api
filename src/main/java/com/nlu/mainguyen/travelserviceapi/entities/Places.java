package com.nlu.mainguyen.travelserviceapi.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "places")
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

   

}
