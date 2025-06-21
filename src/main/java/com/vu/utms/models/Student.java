package com.vu.utms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> transportHistory;
    // Additional student-specific attributes
    private String studentId;
    private String course;
    private int year;
    
    /**
     * Constructor for creating a new student user
     * @param userId System user ID
     * @param name Student's full name
     * @param email Student's email
     * @param phoneNumber Student's contact number
     * @param password Student's password
     * @param studentId University student ID
     * @param course Student's course of study
     * @param year Current year of study
     */
    public Student(String userId, String name, String email, String phoneNumber, 
                  String password, String studentId, String course, int year) {
        super(userId, name, email, phoneNumber, password);
        this.studentId = studentId;
        this.course = course;
        this.year = year;
        this.transportHistory = new ArrayList<>();
    }
    
    // Getter and Setter methods for student-specific attributes
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    /**
     * Implementation of abstract method from User class
     * Demonstrates polymorphism by providing student-specific transport request behavior
     * @param destination The destination location
     * @param dateTime The requested date and time
     * @return String containing the transport request status
     */
    @Override
    public String requestTransport(String destination, String dateTime) {
        String request = String.format("Transport request to %s at %s", destination, dateTime);
        transportHistory.add(request);
        return request;
    }
    
    /**
     * Implementation of abstract method from User class
     * Provides student-specific transport history
     * @return String containing the student's transport history
     */
    @Override
    public String viewTransportHistory() {
        if (transportHistory.isEmpty()) {
            return "No transport history available";
        }
        return String.join("\n", transportHistory);
    }
    
    /**
     * Student-specific method for checking shuttle schedule
     * @param route The route number or name
     * @return String containing the shuttle schedule
     */
    public String checkShuttleSchedule(String route) {
        return String.format("Shuttle Schedule for Route %s\n" +
                           "Available for student use with valid student ID: %s",
                           route, studentId);
    }
}