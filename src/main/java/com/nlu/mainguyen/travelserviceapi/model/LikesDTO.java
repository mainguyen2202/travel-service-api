package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import com.nlu.mainguyen.travelserviceapi.entities.Articles;

import lombok.Data;

@Data

public class LikesDTO { // Địa điểm yêu thích
    private long id;

    private UserOutputDTO users;

    private Articles articles;

    private int status;
    private Date creatAt;
    private String content;
 

}
