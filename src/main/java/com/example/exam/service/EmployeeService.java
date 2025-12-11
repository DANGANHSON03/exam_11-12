package com.example.exam.service;

import com.example.exam.entity.Employee;
import com.example.exam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    public Employee addEmployees(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
            throw new RuntimeException("Employee not found with id: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }
}

