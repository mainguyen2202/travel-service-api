package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    private String name;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    private int status;
    private int position; // thứ tự
    private String content;

    @OneToMany(mappedBy = "itineraries", cascade = CascadeType.ALL)
    private List<ItineraryArticles> itineraries = new ArrayList<>();
}
