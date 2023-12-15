package com.nlu.mainguyen.travelserviceapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nlu.mainguyen.travelserviceapi.entities.Employee;
import com.nlu.mainguyen.travelserviceapi.repositories.EmployeeRepository;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

  private final EmployeeRepository repository;

  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  public List<Employee> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/employees")
  public Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/employees/{id}")
  public Employee one(@PathVariable Long id) {
    // return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    return repository.findById(id).get();
  }

  @PutMapping("/employees/{id}")
  public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}