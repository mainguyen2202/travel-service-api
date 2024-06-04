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
@Table(name = "contacts")
public class Contacts { // Tin tưc
    private @Id @GeneratedValue Long id;
    
  
    
    private String fullName;
    private String email;
    private String subject;
    private String mess;
    private Date creatAt;
    private String status;
    
   

}
