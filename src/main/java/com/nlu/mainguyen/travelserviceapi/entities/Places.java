package com.nlu.mainguyen.travelserviceapi.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Places {
	private @Id @GeneratedValue Long id;
	private int subPlaceId;
	private Topics topic;
	private int idCoordinates;
	private String name;
	private String image;
	private String content;
	private int status;
	private List<Articles> articles = new ArrayList<>();
	public Places() {
	}
	public Places(Long id, int subPlaceId, Topics topic, int idCoordinates, String name, String image, String content,
			int status, List<Articles> articles) {
		this.id = id;
		this.subPlaceId = subPlaceId;
		this.topic = topic;
		this.idCoordinates = idCoordinates;
		this.name = name;
		this.image = image;
		this.content = content;
		this.status = status;
		this.articles = articles;
	}
	@Override
	public String toString() {
		return "Places [id=" + id + ", subPlaceId=" + subPlaceId + ", topic=" + topic + ", idCoordinates="
				+ idCoordinates + ", name=" + name + ", image=" + image + ", content=" + content + ", status=" + status
				+ ", articles=" + articles + "]";
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
	public Topics getTopic() {
		return topic;
	}
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	public int getIdCoordinates() {
		return idCoordinates;
	}
	public void setIdCoordinates(int idCoordinates) {
		this.idCoordinates = idCoordinates;
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
