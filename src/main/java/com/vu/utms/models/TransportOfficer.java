package com.vu.utms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransportOfficer extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> assignmentHistory;
    // Additional transport officer-specific attributes
    private String officerId;
    private String role;
    private String department;
    private int yearsOfExperience;
    
    /**
     * Constructor for creating a new transport officer
     * @param userId System user ID
     * @param name Officer's full name
     * @param email Officer's email
     * @param phoneNumber Officer's contact number
     * @param password Officer's password
     * @param officerId Transport department officer ID
     * @param role Specific role in transport department
     * @param department Department name
     * @param yearsOfExperience Years of service
     */
    public TransportOfficer(String userId, String name, String email, String phoneNumber,
                           String password, String officerId, String role,
                           String department, int yearsOfExperience) {
        super(userId, name, email, phoneNumber, password);
        this.officerId = officerId;
        this.role = role;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.assignmentHistory = new ArrayList<>();
    }
    
    // Getter and Setter methods for transport officer-specific attributes
    public String getOfficerId() { return officerId; }
    public void setOfficerId(String officerId) { this.officerId = officerId; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
    
    /**
     * Implementation of abstract method from User class
     * Demonstrates polymorphism with administrative-level transport request handling
     * @param destination The destination location
     * @param dateTime The requested date and time
     * @return String containing the transport request status
     */
    @Override
    public String requestTransport(String destination, String dateTime) {
        String request = String.format("Administrative Transport Request - Officer: %s, ID: %s\n" +
                           "Role: %s, Department: %s\n" +
                           "Destination: %s, DateTime: %s\n" +
                           "Status: Administrative Priority",
                           getName(), getOfficerId(), role, department,
                           destination, dateTime);
        assignmentHistory.add(request);
        return request;
    }
    
    /**
     * Implementation of abstract method from User class
     * @return String containing the officer's transport management history
     */
    @Override
    public String viewTransportHistory() {
        if (assignmentHistory.isEmpty()) {
            return "No transport management history available";
        }
        return String.format("Transport Management History for Officer %s (%s)\n" +
                           "Role: %s, Department: %s\n" +
                           "History:\n%s",
                           getName(), getOfficerId(), role, department,
                           String.join("\n", assignmentHistory));
    }
    
    /**
     * Method demonstrating method overloading for assigning drivers
     * @param vehicleType Type of vehicle to assign driver to
     * @return String containing assignment status
     */
    public String assignDriver(String vehicleType) {
        return String.format("Driver Assignment by Vehicle Type\n" +
                           "Vehicle Type: %s\n" +
                           "Assigned by Officer: %s",
                           vehicleType, getName());
    }
    
    /**
     * Overloaded method for assigning drivers based on shift time
     * @param shiftTime Time of the shift
     * @param duration Duration of the shift
     * @return String containing assignment status
     */
    public String assignDriver(String shiftTime, int duration) {
        return String.format("Driver Assignment by Shift Time\n" +
                           "Shift Time: %s\n" +
                           "Duration: %d hours\n" +
                           "Assigned by Officer: %s",
                           shiftTime, duration, getName());
    }
    
    /**
     * Method for approving transport requests
     * @param requestId The ID of the transport request
     * @param status Approval status
     * @return String containing approval status
     */
    public String approveTransportRequest(String requestId, boolean status) {
        String approvalStatus = status ? "Approved" : "Rejected";
        return String.format("Transport Request %s\n" +
                           "Request ID: %s\n" +
                           "Status: %s\n" +
                           "Processed by Officer: %s (%s)",
                           approvalStatus, requestId, approvalStatus,
                           getName(), getOfficerId());
    }
}