package com.vu.utms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecturer class represents a lecturer user in the University Transport Management System.
 * This class extends the User class, demonstrating inheritance and polymorphism
 * by implementing lecturer-specific behavior.
 */
public class Lecturer extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> transportHistory;
    // Additional lecturer-specific attributes
    private String staffId;
    private String department;
    private String designation;
    private boolean isPermanent;
    
    /**
     * Constructor for creating a new lecturer user
     * @param userId System user ID
     * @param name Lecturer's full name
     * @param email Lecturer's email
     * @param phoneNumber Lecturer's contact number
     * @param password Lecturer's password
     * @param staffId University staff ID
     * @param department Academic department
     * @param designation Academic position
     * @param isPermanent Employment status
     */
    public Lecturer(String userId, String name, String email, String phoneNumber,
                   String password, String staffId, String department,
                   String designation, boolean isPermanent) {
        super(userId, name, email, phoneNumber, password);
        this.staffId = staffId;
        this.department = department;
        this.designation = designation;
        this.isPermanent = isPermanent;
        this.transportHistory = new ArrayList<>();
    }
    
    // Getter and Setter methods for lecturer-specific attributes
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    
    public boolean isPermanent() { return isPermanent; }
    public void setPermanent(boolean permanent) { isPermanent = permanent; }
    
    /**
     * Implementation of abstract method from User class
     * Demonstrates polymorphism by providing lecturer-specific transport request behavior
     * @param destination The destination location
     * @param dateTime The requested date and time
     * @return String containing the transport request status
     */
    @Override
    public String requestTransport(String destination, String dateTime) {
        String request = String.format("Lecturer Transport Request - Staff: %s, ID: %s\n" +
                           "Department: %s, Designation: %s\n" +
                           "Destination: %s, DateTime: %s\n" +
                           "Status: Priority Request - Processing",
                           getName(), getStaffId(), department, designation,
                           destination, dateTime);
        transportHistory.add(request);
        return request;
    }
    
    /**
     * Implementation of abstract method from User class
     * Provides lecturer-specific transport history
     * @return String containing the lecturer's transport history
     */
    @Override
    public String viewTransportHistory() {
        if (transportHistory.isEmpty()) {
            return String.format("No transport history available for Lecturer %s (%s)", getName(), getStaffId());
        }
        return String.format("Transport History for Lecturer %s (%s):\n%s",
                           getName(), getStaffId(), String.join("\n", transportHistory));
    }
    
    /**
     * Lecturer-specific method for requesting special transport arrangements
     * @param purpose Purpose of the special transport request
     * @param numberOfPassengers Expected number of passengers
     * @return String containing the special transport request status
     */
    public String requestSpecialTransport(String purpose, int numberOfPassengers) {
        return String.format("Special Transport Request\n" +
                           "Lecturer: %s (%s)\n" +
                           "Purpose: %s\n" +
                           "Number of Passengers: %d\n" +
                           "Status: Under Review",
                           getName(), getStaffId(), purpose, numberOfPassengers);
    }
}