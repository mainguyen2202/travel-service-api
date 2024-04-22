package com.nlu.mainguyen.travelserviceapi.model;


import java.sql.Date;


import lombok.Data;

@Data
public class HistoryArticlesDTO {
    private long id;
    private ArticlesDTO articles;
    private Date creatAt;
    private int count;
}
