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
@Table(name = "coordinates")
public class Coordinates { // Tọa độ
    private @Id @GeneratedValue Long id;
    private String longitude; //kinh độ
    private String latitude;// vĩ độ
    private int status;

    @OneToMany(mappedBy = "coordinates", cascade = CascadeType.ALL)
    private List<Places> places = new ArrayList<>();

    }
