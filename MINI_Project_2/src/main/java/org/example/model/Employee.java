package org.example.model;

import java.sql.Date;
import java.sql.Time;

public class Employee {
    private int employeeId;
    private int userId;
    private String designation;
    private Time shiftStart;
    private Time shiftEnd;
    private Date joinedDate;
    private String name;
    private String role;
    private String contactNumber;
    private String email;

    public Employee() {}

    public Employee(int employeeId, int userId, String designation,
                    Time shiftStart, Time shiftEnd, Date joinedDate) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.designation = designation;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.joinedDate = joinedDate;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Time getShiftStart() { return shiftStart; }
    public void setShiftStart(Time shiftStart) { this.shiftStart = shiftStart; }

    public Time getShiftEnd() { return shiftEnd; }
    public void setShiftEnd(Time shiftEnd) { this.shiftEnd = shiftEnd; }

    public Date getJoinedDate() { return joinedDate; }
    public void setJoinedDate(Date joinedDate) { this.joinedDate = joinedDate; }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", userId=" + userId +
                ", designation='" + designation + '\'' + ", shiftStart=" + shiftStart +
                ", shiftEnd=" + shiftEnd + ", joinedDate=" + joinedDate + '}';
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
