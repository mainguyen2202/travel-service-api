package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

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
@Table(name = "shareItineraries")
public class ShareItineraries {
       private @Id @GeneratedValue Long id;

    @ManyToOne()
    @JoinColumn(name = "itineraries_id")
    private Itineraries itineraries; 

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    private Date createAt;
    private int status;
    private String content;
    
}
