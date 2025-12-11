package com.example.exam.controller;

import com.example.exam.entity.Employee;
import com.example.exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {
    
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public String index(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.addEmployees(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");
        return "redirect:/employees";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (employee == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found!");
            return "redirect:/employees";
        }
        
        model.addAttribute("employee", employee);
        model.addAttribute("isEdit", true);
        return "index";
    }
}

