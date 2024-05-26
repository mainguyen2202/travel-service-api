package com.nlu.mainguyen.travelserviceapi.entities;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
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

    // @Column(updatable = false)
    // @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date modifyDate;// ngày xem
    private int count;

    /*
     * 
     * new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-15")
     * new SimpleDateFormat("HH:mm:ss").parse("15:30:14")
     * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2017-11-15 15:30:14.332")
     * 
     * // Get the current date
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        
        // Or, alternatively, using LocalDate and converting to java.sql.Date
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
     */
}
