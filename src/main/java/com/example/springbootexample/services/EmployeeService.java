package com.example.springbootexample.services;

import com.example.springbootexample.models.ApiRepository;
import com.example.springbootexample.models.Employee;
import com.example.springbootexample.repositorys.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepository;

    // CREATE
    public Employee createEmployee(Employee emp) {
        return empRepository.save(emp);
    }

    // READ
    public ApiRepository getEmployees() {
        ApiRepository repository = new ApiRepository();
        List<Employee> list = empRepository.findAll();
        repository.setData(list);
        repository.setSuccess(true);
        return repository;
    }

    // DELETE
    public void deleteEmployee(Long empId) {
        empRepository.deleteById(empId);
    }

    // UPDATE
    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Employee emp = empRepository.findById(empId).get();
        emp.setFirstName(employeeDetails.getFirstName());
        emp.setLastName(employeeDetails.getLastName());
        emp.setEmailId(employeeDetails.getEmailId());
        return empRepository.save(emp);
    }
}
