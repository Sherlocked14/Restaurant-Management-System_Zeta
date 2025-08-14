package org.example.service.interfaces;

import org.example.model.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee getEmployeeByUserId(int userId);
    List<Employee> getAllEmployees();
}
