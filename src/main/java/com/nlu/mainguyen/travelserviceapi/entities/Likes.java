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
@Table(name = "likes")
public class Likes { // Địa điểm yêu thích
    private @Id @GeneratedValue Long id;

    @ManyToOne()
    private Users users;

    @ManyToOne()
    private Articles articles;

    private int status;
    private Date creatAt;

 

}
