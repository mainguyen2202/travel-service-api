package com.nlu.mainguyen.travelserviceapi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private int status;
}
