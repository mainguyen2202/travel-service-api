package com.nlu.mainguyen.travelserviceapi.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ItinerariesDTO { // Lập kế hoạch
    private long id;

    // @ManyToOne()
    // private UserDTO users;

    // @ManyToOne()
    // private Articles articles;

    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int status;
    private int position; // thứ tự

    private long users_id;
}
