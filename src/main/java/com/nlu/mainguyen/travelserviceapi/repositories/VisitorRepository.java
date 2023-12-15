package com.nlu.mainguyen.travelserviceapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nlu.mainguyen.travelserviceapi.entities.VisitorTModel;

@Repository
public interface VisitorRepository extends CrudRepository<VisitorTModel, Long> {
    // kế thừa những phương thức của thư viện CrudRepository

    // và có 2 phương thức riêng
    List<VisitorTModel> findAll();
    Optional<VisitorTModel> findById(Long id);
    
}