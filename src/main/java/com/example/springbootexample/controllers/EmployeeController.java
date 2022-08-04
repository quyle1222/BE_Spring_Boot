package com.example.springbootexample.controllers;


import com.example.springbootexample.models.ApiRepository;
import com.example.springbootexample.models.Employee;
import com.example.springbootexample.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService empService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.createEmployee(emp);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiRepository readEmployees() {
        ApiRepository repository = new ApiRepository();
        repository = empService.getEmployees();
        return repository;
    }

}
