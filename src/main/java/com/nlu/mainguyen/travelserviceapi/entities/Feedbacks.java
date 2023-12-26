package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
public class Feedbacks {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Articles articles;

    private int status;
    private Date creatAt;

    private int heart;
    private int share;
    private String review;

    public Feedbacks() {
    }

    

  



    public Feedbacks(Users users, Articles articles, int status, Date creatAt, int heart, int share, String review) {
        this.users = users;
        this.articles = articles;
        this.status = status;
        this.creatAt = creatAt;
        this.heart = heart;
        this.share = share;
        this.review = review;
    }







    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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





    public int getShare() {
        return share;
    }



    public void setShare(int share) {
        this.share = share;
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



    public int getHeart() {
        return heart;
    }



    public void setHeart(int heart) {
        this.heart = heart;
    }







    public String getReview() {
        return review;
    }







    public void setReview(String review) {
        this.review = review;
    }

    
}