package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class HistoryArticlesDTOs {
    private long id;
    
    private Date modifyDate;
    private int count;
}