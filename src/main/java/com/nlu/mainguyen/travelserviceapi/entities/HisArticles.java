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
@Table(name = "hisArticles")
public class HisArticles {
    private @Id @GeneratedValue Long id;
    @ManyToOne()
    private Articles articles;
    private Date creatAt;
    private int count;
}
