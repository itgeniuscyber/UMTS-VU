package com.vu.utms;

import com.vu.utms.models.*;
import com.vu.utms.data.DataManager;
import java.util.Scanner;

/**
 * UserRegistration class handles the registration of different types of users
 * in the University Transport Management System.
 */
public class UserRegistration {
    private static Scanner scanner = new Scanner(System.in);
    private static DataManager dataManager = DataManager.getInstance();
    
    /**
     * Main method to demonstrate user registration
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to UTMS User Registration\n");
        
        while (true) {
            System.out.println("Select user type to register:");
            System.out.println("1. Student");
            System.out.println("2. Lecturer");
            System.out.println("3. Transport Officer");
            System.out.println("4. Exit");
            
            int choice = getIntInput("Enter your choice (1-4): ");
            
            if (choice == 4) {
                System.out.println("Thank you for using UTMS Registration System.");
                break;
            }
            
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    registerLecturer();
                    break;
                case 3:
                    registerTransportOfficer();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Method to register a new student
     */
    private static void registerStudent() {
        System.out.println("\n=== Student Registration ===");
        
        String userId = getInput("Enter User ID: ");
        String name = getInput("Enter Full Name: ");
        String email = getInput("Enter Email: ");
        String phoneNumber = getInput("Enter Phone Number: ");
        String password = getInput("Enter Password: ");
        String studentId = getInput("Enter Student ID: ");
        String course = getInput("Enter Course: ");
        int year = getIntInput("Enter Year of Study: ");
        
        Student student = new Student(userId, name, email, phoneNumber,
                                    password, studentId, course, year);
        
        // Save student data
        dataManager.addStudent(student);
        
        System.out.println("\nStudent Registration Successful!");
        System.out.println("Student Details:");
        displayStudentDetails(student);
    }
    
    /**
     * Method to register a new lecturer
     */
    private static void registerLecturer() {
        System.out.println("\n=== Lecturer Registration ===");
        
        String userId = getInput("Enter User ID: ");
        String name = getInput("Enter Full Name: ");
        String email = getInput("Enter Email: ");
        String phoneNumber = getInput("Enter Phone Number: ");
        String password = getInput("Enter Password: ");
        String staffId = getInput("Enter Staff ID: ");
        String department = getInput("Enter Department: ");
        String designation = getInput("Enter Designation: ");
        boolean isPermanent = getBooleanInput("Is Permanent Staff? (true/false): ");
        
        Lecturer lecturer = new Lecturer(userId, name, email, phoneNumber,
                                        password, staffId, department,
                                        designation, isPermanent);
        
        // Save lecturer data
        dataManager.addLecturer(lecturer);
        
        System.out.println("\nLecturer Registration Successful!");
        System.out.println("Lecturer Details:");
        displayLecturerDetails(lecturer);
    }
    
    /**
     * Method to register a new transport officer
     */
    private static void registerTransportOfficer() {
        System.out.println("\n=== Transport Officer Registration ===");
        
        String userId = getInput("Enter User ID: ");
        String name = getInput("Enter Full Name: ");
        String email = getInput("Enter Email: ");
        String phoneNumber = getInput("Enter Phone Number: ");
        String password = getInput("Enter Password: ");
        String officerId = getInput("Enter Officer ID: ");
        String role = getInput("Enter Role: ");
        String department = getInput("Enter Department: ");
        int experience = getIntInput("Enter Years of Experience: ");
        
        TransportOfficer officer = new TransportOfficer(userId, name, email,
                                                      phoneNumber, password,
                                                      officerId, role,
                                                      department, experience);
        
        // Save officer data
        dataManager.addOfficer(officer);
        
        System.out.println("\nTransport Officer Registration Successful!");
        System.out.println("Transport Officer Details:");
        displayOfficerDetails(officer);
    }
    
    /**
     * Helper method to display student details
     * @param student The student object to display
     */
    private static void displayStudentDetails(Student student) {
        System.out.println("Name: " + student.getName());
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Phone: " + student.getPhoneNumber());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Year: " + student.getYear());
        System.out.println();
    }
    
    /**
     * Helper method to display lecturer details
     * @param lecturer The lecturer object to display
     */
    private static void displayLecturerDetails(Lecturer lecturer) {
        System.out.println("Name: " + lecturer.getName());
        System.out.println("Staff ID: " + lecturer.getStaffId());
        System.out.println("Email: " + lecturer.getEmail());
        System.out.println("Phone: " + lecturer.getPhoneNumber());
        System.out.println("Department: " + lecturer.getDepartment());
        System.out.println("Designation: " + lecturer.getDesignation());
        System.out.println("Permanent Staff: " + lecturer.isPermanent());
        System.out.println();
    }
    
    /**
     * Helper method to display transport officer details
     * @param officer The transport officer object to display
     */
    private static void displayOfficerDetails(TransportOfficer officer) {
        System.out.println("Name: " + officer.getName());
        System.out.println("Officer ID: " + officer.getOfficerId());
        System.out.println("Email: " + officer.getEmail());
        System.out.println("Phone: " + officer.getPhoneNumber());
        System.out.println("Role: " + officer.getRole());
        System.out.println("Department: " + officer.getDepartment());
        System.out.println("Experience: " + officer.getYearsOfExperience() + " years");
        System.out.println();
    }
    
    /**
     * Helper method to get string input
     * @param prompt The prompt to display
     * @return The user's input string
     */
    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Helper method to get integer input
     * @param prompt The prompt to display
     * @return The user's input as an integer
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Helper method to get boolean input
     * @param prompt The prompt to display
     * @return The user's input as a boolean
     */
    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter 'true' or 'false'.");
        }
    }
}