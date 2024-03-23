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
@Table(name = "news")
public class News { // Tin tưc
    private @Id @GeneratedValue Long id;
    
    @ManyToOne()
    private Topics topics;
    
    private String title;
    private String content;
    private Date creatAt;
    private String status;
    
   

}
