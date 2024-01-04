package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class Likes { // Địa điểm yêu thích
    private @Id @GeneratedValue Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Articles articles;

    private int status;
    private Date creatAt;

    public Likes() {
    }

    public Likes(int status, Date creatAt) {
        this.status = status;
        this.creatAt = creatAt;
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
