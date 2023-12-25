package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Users {
	private @Id @GeneratedValue Long id;
	private String lastname;
	private String firstname;
	private String email;
	private String username;
	private String password;
	private String image;
	private int status;
	private int role;
	private Date createAt;
	private List<Likes> likes = new ArrayList<>();
	private List<Articles> articles = new ArrayList<>();
	private List<Itineraries> itineraries = new ArrayList<>();
	private List<Comments> comments = new ArrayList<>();
	public Users() {
	}
	public Users(Long id, String lastname, String firstname, String email, String username, String password,
			String image, int status, int role, Date createAt, List<Likes> likes, List<Articles> articles,
			List<Itineraries> itineraries, List<Comments> comments) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
		this.status = status;
		this.role = role;
		this.createAt = createAt;
		this.likes = likes;
		this.articles = articles;
		this.itineraries = itineraries;
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", image=" + image + ", status=" + status
				+ ", role=" + role + ", createAt=" + createAt + ", likes=" + likes + ", articles=" + articles
				+ ", itineraries=" + itineraries + ", comments=" + comments + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public List<Articles> getArticles() {
		return articles;
	}
	public void setArticles(List<Articles> articles) {
		this.articles = articles;
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
