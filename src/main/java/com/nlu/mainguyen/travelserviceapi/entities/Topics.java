package com.nlu.mainguyen.travelserviceapi.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Topics {
    private @Id @GeneratedValue Long id;

    private String title;
    private String content;
    private int status;

    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();

    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    private List<Articles> articles = new ArrayList<>();

    public Topics() {
    }

    public Topics(String title, String content, int status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

}
