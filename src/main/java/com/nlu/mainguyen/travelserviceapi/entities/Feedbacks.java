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
@Table(name = "feedbacks")
public class Feedbacks {// Bình luận

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Articles articles;

    private int status;
    private Date creatAt;

    private int heart; // thích
    private int share;
    private String review; // bình luận

  
    
}