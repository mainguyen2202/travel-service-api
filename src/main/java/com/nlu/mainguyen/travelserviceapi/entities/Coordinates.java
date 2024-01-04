package com.nlu.mainguyen.travelserviceapi.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Coordinates { // Tọa độ
    private @Id @GeneratedValue Long id;
    private String longitude; //kinh độ
    private String latitude;// vĩ độ
    private int status;

    @OneToMany(mappedBy = "coordinates", cascade = CascadeType.ALL)
    private List<Places> places = new ArrayList<>();

    public Coordinates() {
    }

    public Coordinates(String longitude, String latitude, int status) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void setPlaces(List<Places> places) {
        this.places = places;
    }


}
