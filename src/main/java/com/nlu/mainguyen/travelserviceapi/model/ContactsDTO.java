package com.nlu.mainguyen.travelserviceapi.model;


import java.sql.Date;

import lombok.Data;


@Data

public class ContactsDTO { // Tin tưc
    private long id;
    
  
    
    private String fullName;
    private String email;
    private String subject;
    private String mess;
    private Date creatAt;
    private String status;
    
   

}
