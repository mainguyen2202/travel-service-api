package com.nlu.mainguyen.travelserviceapi.model;

import lombok.Data;
import java.sql.Date;

@Data
public class ItineraryArticlesDTO {// bài viết của nhiều địa điểm và nhiều topics

    private long id;

    // @ManyToOne()
    private ArticlesDTO articles;

    // @ManyToOne()
    private ItinerariesDTO itineraries; // vị trí cụ thể

    private Date dateStart;

    // private long articlesId;
    // private long itinerariesId;
    private int status;
}
