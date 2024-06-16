package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ItinerariesDTO { // Lập kế hoạch
    private long id;

    private String name;
    private int participantCount;
    private Date dateStart;
    private Date dateEnd;
    private int status;
    private int position; // thứ tự
    private String content;

    // private long usersId;
    private UserOutputDTO users;
}
