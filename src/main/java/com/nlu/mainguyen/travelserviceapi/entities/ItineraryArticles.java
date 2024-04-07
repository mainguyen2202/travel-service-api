package com.nlu.mainguyen.travelserviceapi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;


import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itineraryArticles")
public class ItineraryArticles {// bài viết của nhiều địa điểm và nhiều topics

    private @Id @GeneratedValue Long id;

    @ManyToOne()
    @JoinColumn(name = "articles_id")
    private Articles articles;

    @ManyToOne()
    @JoinColumn(name = "itineraries_id")
    private Itineraries itineraries; // vị trí cụ thể

    @Column(name = "date_start")
    private Date dateStart;

    private int status;
}
