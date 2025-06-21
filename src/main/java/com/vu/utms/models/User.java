package com.vu.utms.models;

import java.io.Serializable;

/**
 * Abstract base class representing a user in the University Transport Management System.
 * This class provides common attributes and behaviors for all types of users.
 * It implements encapsulation by keeping data private and accessible only through
 * getter and setter methods.
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // Private fields for encapsulation
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    
    /**
     * Constructor for creating a new user
     * @param userId Unique identifier for the user
     * @param name Full name of the user
     * @param email Email address of the user
     * @param phoneNumber Contact number of the user
     * @param password User's password (should be encrypted in production)
     */
    public User(String userId, String name, String email, String phoneNumber, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    
    // Getter and Setter methods for encapsulation
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    protected String getPassword() { return password; }
    protected void setPassword(String password) { this.password = password; }
    
    /**
     * Abstract method for requesting transport
     * This demonstrates polymorphism as each user type will implement it differently
     * @param destination The destination location
     * @param dateTime The requested date and time
     * @return String containing the transport request status
     */
    public abstract String requestTransport(String destination, String dateTime);
    
    /**
     * Abstract method for viewing transport history
     * @return String containing the user's transport history
     */
    public abstract String viewTransportHistory();
    
    /**
     * Common method for all users to update their profile
     * @param newEmail Updated email address
     * @param newPhone Updated phone number
     * @return boolean indicating if update was successful
     */
    public boolean updateProfile(String newEmail, String newPhone) {
        this.email = newEmail;
        this.phoneNumber = newPhone;
        return true;
    }
}