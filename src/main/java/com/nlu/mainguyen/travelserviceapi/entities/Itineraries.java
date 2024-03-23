package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itineraries")
public class Itineraries { // Lập kế hoạch
    private @Id @GeneratedValue Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Articles articles;

    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int status;
    private int position; // thứ tự

  

}
