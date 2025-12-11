package com.example.exam.controller;

import com.example.exam.entity.Employee;
import com.example.exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Web service method: getEmployees - retrieve all employees in an array
     * @return array of all employees
     */
    @GetMapping
    public ResponseEntity<Employee[]> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        Employee[] employeeArray = employees.toArray(new Employee[0]);
        return ResponseEntity.ok(employeeArray);
    }
    
    /**
     * Web service method: addEmployees - add a new employee record
     * @param employee the employee to add
     * @return the saved employee
     */
    @PostMapping
    public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployees(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    
    /**
     * Web service method: updateEmployee - modify an existing employee record
     * @param employee the employee to update
     * @return the updated employee
     */
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

