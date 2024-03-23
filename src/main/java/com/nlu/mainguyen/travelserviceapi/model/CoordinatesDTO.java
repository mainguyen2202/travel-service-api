package com.nlu.mainguyen.travelserviceapi.model;


import lombok.*;

@Data
public class CoordinatesDTO { // Tọa độ
    private long id;
    private String longitude; //kinh độ
    private String latitude;// vĩ độ
    private int status;

    // @OneToMany(mappedBy = "coordinates", cascade = CascadeType.ALL)
    // private List<Places> places = new ArrayList<>();

}
