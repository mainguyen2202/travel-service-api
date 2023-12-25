package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Articles {

	private @Id @GeneratedValue Long id;
	private Users users;
	private Places places;
	private Topics topics;
	private String title;
	private String image;
	private Date createAt;
	private String content;
	private int status;
	private List<Likes> likes = new ArrayList<>();
	private List<Itineraries> itineraries = new ArrayList<>();
	private List<Comments> comments = new ArrayList<>();
	public Articles() {
	}
	public Articles(Long id, Users users, Places places, Topics topics, String title, String image, Date createAt,
			String content, int status, List<Likes> likes, List<Itineraries> itineraries, List<Comments> comments) {
		this.id = id;
		this.users = users;
		this.places = places;
		this.topics = topics;
		this.title = title;
		this.image = image;
		this.createAt = createAt;
		this.content = content;
		this.status = status;
		this.likes = likes;
		this.itineraries = itineraries;
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Articles [id=" + id + ", users=" + users + ", places=" + places + ", topics=" + topics + ", title="
				+ title + ", image=" + image + ", createAt=" + createAt + ", content=" + content + ", status=" + status
				+ ", likes=" + likes + ", itineraries=" + itineraries + ", comments=" + comments + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Places getPlaces() {
		return places;
	}
	public void setPlaces(Places places) {
		this.places = places;
	}
	public Topics getTopics() {
		return topics;
	}
	public void setTopics(Topics topics) {
		this.topics = topics;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
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
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public List<Itineraries> getItineraries() {
		return itineraries;
	}
	public void setItineraries(List<Itineraries> itineraries) {
		this.itineraries = itineraries;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


}
