package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.*;


@Entity
public class News { // Tin tưc
    private @Id @GeneratedValue Long id;
    
    @ManyToOne()
    private Topics topics;
    
    private String title;
    private String content;
    private Date creatAt;
    private String status;
    
   
    public News() {
    }

    public News(String title, String content, Date creatAt, String status) {
        this.title = title;
        this.content = content;
        this.creatAt = creatAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

}
