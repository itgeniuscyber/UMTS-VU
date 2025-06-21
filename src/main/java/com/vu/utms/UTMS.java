package com.vu.utms;

import com.vu.utms.models.*;
import com.vu.utms.data.DataManager;
import java.util.Scanner;
import java.util.List;

public class UTMS {
    private static Scanner scanner = new Scanner(System.in);
    private static DataManager dataManager = DataManager.getInstance();

    public static void main(String[] args) {
        
        while (true) {
            System.out.println("\nUniversity Transport Management System");
            System.out.println("1. Register New User");
            System.out.println("2. View Registered Users");
            System.out.println("3. Manage Vehicles");
            System.out.println("4. Request Transport");
            System.out.println("5. View Transport Requests");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerNewUser();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    manageVehicles();
                    break;
                case 4:
                    requestTransport();
                    break;
                case 5:
                    viewTransportRequests();
                    break;
                case 6:
                    System.out.println("Thank you for using UTMS!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void registerNewUser() {
        System.out.println("\nUser Registration");
        System.out.println("1. Register Student");
        System.out.println("2. Register Lecturer");
        System.out.println("3. Register Transport Officer");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber= scanner.nextLine();
        System.out.println("Enter password:");
        String password= scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Course: ");
                String course = scanner.nextLine();
                System.out.print("Enter Year: ");
                int year = scanner.nextInt();
                Student student = new Student(id, name, email, phoneNumber, password, id, course, year);
                dataManager.addStudent(student);
                System.out.println("Student registered successfully!");
                break;
                
            case 2:
                System.out.print("Enter Department: ");
                String lecDept = scanner.nextLine();
                System.out.print("Enter Designation: ");
                String designation = scanner.nextLine();
                Lecturer lecturer = new Lecturer(id, name, email, phoneNumber, password, id, lecDept, designation, true);
                dataManager.addLecturer(lecturer);
                System.out.println("Lecturer registered successfully!");
                break;
                
            case 3:
                System.out.print("Enter Department:  ");
                String department = scanner.nextLine();
                System.out.print("Enter Role:  ");
                String role = scanner.nextLine();
                System.out.print("Enter Years of Experience: ");
                int experience = scanner.nextInt();
                TransportOfficer officer = new TransportOfficer(id, name, email, phoneNumber, password, id, role, department, experience);
                dataManager.addOfficer(officer);
                System.out.println("Transport Officer registered successfully!");
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void viewRegisteredUsers() {
        System.out.println("\nRegistered Users:");
        
        System.out.println("\nStudents:");
        List<Student> students = dataManager.getStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getName() + 
                             ", Department: " + student.getCourse());
        }
        
        System.out.println("\nLecturers:");
        List<Lecturer> lecturers = dataManager.getLecturers();
        for (Lecturer lecturer : lecturers) {
            System.out.println("ID: " + lecturer.getStaffId() + ", Name: " + lecturer.getName() + 
                             ", Department: " + lecturer.getDepartment());
        }
        
        System.out.println("\nTransport Officers:");
        List<TransportOfficer> officers = dataManager.getOfficers();
        for (TransportOfficer officer : officers) {
            System.out.println("ID: " + officer.getOfficerId() + ", Name: " + officer.getName() + 
                             ", Division: " + officer.getDepartment());
        }
    }

    private static void manageVehicles() {
        System.out.println("\nVehicle Management");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                addNewVehicle();
                break;
            case 2:
                viewAllVehicles();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void addNewVehicle() {
        System.out.println("\nAdd New Vehicle");
        System.out.println("1. Add Bus");
        System.out.println("2. Add Van");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Vehicle ID: ");
        String id = scanner.nextLine();
        // System.out.print("Enter Make: ");
        // String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Registration Number: ");
        String regNumber = scanner.nextLine();
         System.out.print("Enter Seating Capacity: ");
                int capacity = scanner.nextInt();
        
        switch (choice) {
            case 1:
               
             
                System.out.print("Enter Route Number: ");
                String routeNumber = scanner.nextLine();

                Bus bus = new Bus(id, regNumber, model, capacity, routeNumber, false, false, capacity);
                dataManager.addVehicle(bus);
                System.out.println("Bus added successfully!");
                break;
                
            case 2:
                System.out.print("Enter Cargo Capacity (kg): ");
                double cargoCapacity = scanner.nextDouble();
                System.out.print("Enter Purpuse Type: ");
                String purposeType = scanner.nextLine();
                Van van = new Van(id, regNumber, model, capacity, true, cargoCapacity, purposeType, true);
                dataManager.addVehicle(van);
                System.out.println("Van added successfully!");
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void viewAllVehicles() {
        System.out.println("\nRegistered Vehicles:");
        List<Vehicle> vehicles = DataManager.loadVehicles();
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("\nID: " + vehicle.getVehicleId());
            System.out.println("Type: " + vehicle.getVehicleType());
            // System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Reg Number: " + vehicle.getRegistrationNumber());
            System.out.println("Status: " + vehicle.getCurrentStatus());
            System.out.println("Location: " + vehicle.getCurrentLocation());
        }
    }

    private static void requestTransport() {
        System.out.println("\nTransport Request");
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();
        
        // Find user and process request
        List<Student> students = DataManager.loadStudents();
        for (Student student : students) {
            if (student.getStudentId().equals(userId)) {
                student.requestTransport(destination, date + " " + time);
                System.out.println("Transport request submitted successfully!");
                return;
            }
        }
        
        List<Lecturer> lecturers = DataManager.loadLecturers();
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getStaffId().equals(userId)) {
                lecturer.requestTransport(destination, date + " " + time);
                System.out.println("Transport request submitted successfully!");
                return;
            }
        }
        
        System.out.println("User not found!");
    }

    private static void viewTransportRequests() {
        System.out.println("\nTransport Requests:");
        // Load all users and their transport requests
        List<Student> students = DataManager.loadStudents();
        List<Lecturer> lecturers = DataManager.loadLecturers();
        
        System.out.println("\nStudent Requests:");
        for (Student student : students) {
            System.out.println("Student: " + student.getName() + " (" + student.getStudentId() + ")");
            System.out.println("History: " + student.viewTransportHistory());
        }
        
        System.out.println("\nLecturer Requests:");
        for (Lecturer lecturer : lecturers) {
            System.out.println("Lecturer: " + lecturer.getName() + " (" + lecturer.getStaffId() + ")");
            System.out.println(" Transport History: " + lecturer.viewTransportHistory());
        }
    }
}