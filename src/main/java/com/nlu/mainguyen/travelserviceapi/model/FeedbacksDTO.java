package com.nlu.mainguyen.travelserviceapi.model;
import java.sql.Date;


import lombok.Data;

@Data
public class FeedbacksDTO {
    private long id;

    private UserOutputDTO users;

    private ArticlesDTO articles;

    private int status;
    private Date creatAt;

    private int heart; // thích
    private int share;
    private String review; // bình luận
    private String content;
}
