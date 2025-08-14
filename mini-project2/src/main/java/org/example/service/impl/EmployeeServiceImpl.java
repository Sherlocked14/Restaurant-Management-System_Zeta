package org.example.service.impl;

import org.example.model.Employee;
import org.example.service.interfaces.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public void addEmployee(Employee employee) {
        // Add employee
    }

    @Override
    public Employee getEmployeeByUserId(int userId) {
        // Fetch employee by user ID
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Fetch all employees
        return new ArrayList<>();
    }
}
