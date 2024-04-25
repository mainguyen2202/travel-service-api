package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ShareItinerariesDTO {
    private long id;

  
    private ItinerariesDTO itineraries; 

   
    private UserOutputDTO users;

    private Date createAt;
    private int status;
    private String content;

    
    
}
