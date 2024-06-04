package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nlu.mainguyen.travelserviceapi.entities.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    
} 