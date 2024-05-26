package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;


import lombok.Data;

@Data
public class LikesDTO { // Địa điểm yêu thích
    private long id;

    private UserOutputDTO users;

    private ArticlesDTO articles;

    private int status;
    private Date creatAt;
    private String content;
}
