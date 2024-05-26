package com.nlu.mainguyen.travelserviceapi.model;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HistoryArticlesDTO {
    private long id;

    private ArticlesDTO articles;

    private Date modifyDate;
    private int count;
}
