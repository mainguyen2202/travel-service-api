package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Likes {
         private @Id @GeneratedValue Long id;
         private Users user;
         private Articles articles;
         private int status;
         private Date creatAt;
		public Likes() {
		}
		public Likes(Long id, Users user, Articles articles, int status, Date creatAt) {
			this.id = id;
			this.user = user;
			this.articles = articles;
			this.status = status;
			this.creatAt = creatAt;
		}
		@Override
		public String toString() {
			return "Likes [id=" + id + ", user=" + user + ", articles=" + articles + ", status=" + status + ", creatAt="
					+ creatAt + "]";
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
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getCreatAt() {
			return creatAt;
		}
		public void setCreatAt(Date creatAt) {
			this.creatAt = creatAt;
		}
		
}
