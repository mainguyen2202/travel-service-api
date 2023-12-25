package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Itineraries {
         private @Id @GeneratedValue Long id;
         private Users users;
         private Articles articles;
         private String name;
         private Date dateStart;
         private Date dateEnd;
         private int status;
         private int position;
		public Itineraries() {
		}
		public Itineraries(Long id, Users users, Articles articles, String name, Date dateStart, Date dateEnd,
				int status, int position) {
			this.id = id;
			this.users = users;
			this.articles = articles;
			this.name = name;
			this.dateStart = dateStart;
			this.dateEnd = dateEnd;
			this.status = status;
			this.position = position;
		}
		@Override
		public String toString() {
			return "Itineraries [id=" + id + ", users=" + users + ", articles=" + articles + ", name=" + name
					+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", status=" + status + ", position="
					+ position + "]";
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
		public Articles getArticles() {
			return articles;
		}
		public void setArticles(Articles articles) {
			this.articles = articles;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getDateStart() {
			return dateStart;
		}
		public void setDateStart(Date dateStart) {
			this.dateStart = dateStart;
		}
		public Date getDateEnd() {
			return dateEnd;
		}
		public void setDateEnd(Date dateEnd) {
			this.dateEnd = dateEnd;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		 
	 
}
