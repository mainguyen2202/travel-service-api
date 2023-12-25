package com.nlu.mainguyen.travelserviceapi.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Coordinates {
	private @Id @GeneratedValue Long id;
	private String longitude;
	private String latitude;
	private int status;
	private List<Places> places = new ArrayList<>();
	public Coordinates() {
	}
	public Coordinates(Long id, String longitude, String latitude, int status, List<Places> places) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.status = status;
		this.places = places;
	}
	@Override
	public String toString() {
		return "Coordinates [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", status=" + status
				+ ", places=" + places + "]";
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
