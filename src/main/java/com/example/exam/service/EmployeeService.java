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
    
    /**
     * Retrieve all employees in an array
     * @return List of all employees
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    
    /**
     * Add a new employee record
     * @param employee the employee to add
     * @return the saved employee
     */
    public Employee addEmployees(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    /**
     * Modify an existing employee record
     * @param employee the employee to update
     * @return the updated employee
     */
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
            throw new RuntimeException("Employee not found with id: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }
}

