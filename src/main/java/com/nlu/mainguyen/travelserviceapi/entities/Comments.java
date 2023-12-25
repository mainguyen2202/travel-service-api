package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Comments {
	private @Id @GeneratedValue Long id;
	private Users user;
	private Articles articles;
	private int like;
	private int share;
	private String comment;
	private Date creatAt;
	private String content;
	private int status;
	public Comments() {
	}
	public Comments(Long id, Users user, Articles articles, int like, int share, String comment, Date creatAt,
			String content, int status) {
		this.id = id;
		this.user = user;
		this.articles = articles;
		this.like = like;
		this.share = share;
		this.comment = comment;
		this.creatAt = creatAt;
		this.content = content;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", user=" + user + ", articles=" + articles + ", like=" + like + ", share="
				+ share + ", comment=" + comment + ", creatAt=" + creatAt + ", content=" + content + ", status="
				+ status + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Articles getArticles() {
		return articles;
	}
	public void setArticles(Articles articles) {
		this.articles = articles;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreatAt() {
		return creatAt;
	}
	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
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
	

}
