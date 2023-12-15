package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nlu.mainguyen.travelserviceapi.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // kế thừa những phương thức của thư viện CrudRepository
    
}