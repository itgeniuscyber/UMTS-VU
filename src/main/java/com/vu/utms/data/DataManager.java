package com.vu.utms.data;

import com.vu.utms.models.*;
import java.io.*;
import java.util.*;

public class DataManager {
    private static final String DATA_DIR = "utms_data";
    private static final String STUDENTS_FILE = DATA_DIR + File.separator + "students.dat";
    private static final String LECTURERS_FILE = DATA_DIR + File.separator + "lecturers.dat";
    private static final String OFFICERS_FILE = DATA_DIR + File.separator + "officers.dat";
    private static final String VEHICLES_FILE = DATA_DIR + File.separator + "vehicles.dat";
    
    private static DataManager instance;
    private List<Student> students;
    private List<Lecturer> lecturers;
    private List<TransportOfficer> officers;
    private List<Vehicle> vehicles;
    
    private DataManager() {
        students = new ArrayList<>();
        lecturers = new ArrayList<>();
        officers = new ArrayList<>();
        vehicles = new ArrayList<>();
        initializeDataDirectory();
        loadAllData();
    }
    
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    
    /**
     * Initialize the data directory
     */
    private void initializeDataDirectory() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    private void loadAllData() {
        students = loadData(STUDENTS_FILE, new ArrayList<>());
        lecturers = loadData(LECTURERS_FILE, new ArrayList<>());
        officers = loadData(OFFICERS_FILE, new ArrayList<>());
        vehicles = loadData(VEHICLES_FILE, new ArrayList<>());
    }
    
    private <T> List<T> loadData(String filename, List<T> defaultValue) {
        File file = new File(filename);
        if (!file.exists()) {
            return defaultValue;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + filename + ": " + e.getMessage());
            return defaultValue;
        }
    }
    
    private void saveData(String filename, List<?> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filename + ": " + e.getMessage());
        }
    }
    
    /**
     * Save a student to the data file
     * @param student Student object to save
     */
    public void addStudent(Student student) {
        students.add(student);
        saveData(STUDENTS_FILE, students);
    }

    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
        saveData(LECTURERS_FILE, lecturers);
    }

    public void addOfficer(TransportOfficer officer) {
        officers.add(officer);
        saveData(OFFICERS_FILE, officers);
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveData(VEHICLES_FILE, vehicles);
    }
    
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    
    public List<Lecturer> getLecturers() {
        return new ArrayList<>(lecturers);
    }
    
    public List<TransportOfficer> getOfficers() {
        return new ArrayList<>(officers);
    }
    
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }
    
    /**
     * Save a vehicle to the data file
     * @param vehicle Vehicle object to save
     */
    public static void saveVehicle(Vehicle vehicle) {
        List<Vehicle> vehicles = loadVehicles();
        vehicles.add(vehicle);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(VEHICLES_FILE))) {
            oos.writeObject(vehicles);
        } catch (IOException e) {
            System.out.println("Error saving vehicle: " + e.getMessage());
        }
    }
    
    /**
     * Load all students from the data file
     * @return List of Student objects
     */
    @SuppressWarnings("unchecked")
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students: " + e.getMessage());
            }
        }
        return students;
    }
    
    /**
     * Load all lecturers from the data file
     * @return List of Lecturer objects
     */
    @SuppressWarnings("unchecked")
    public static List<Lecturer> loadLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        File file = new File(LECTURERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                lecturers = (List<Lecturer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading lecturers: " + e.getMessage());
            }
        }
        return lecturers;
    }
    
    /**
     * Load all transport officers from the data file
     * @return List of TransportOfficer objects
     */
    @SuppressWarnings("unchecked")
    public static List<TransportOfficer> loadTransportOfficers() {
        List<TransportOfficer> officers = new ArrayList<>();
        File file = new File(OFFICERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                officers = (List<TransportOfficer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading officers: " + e.getMessage());
            }
        }
        return officers;
    }
    
    /**
     * Load all vehicles from the data file
     * @return List of Vehicle objects
     */
    @SuppressWarnings("unchecked")
    public static List<Vehicle> loadVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        File file = new File(VEHICLES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                vehicles = (List<Vehicle>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading vehicles: " + e.getMessage());
            }
        }
        return vehicles;
    }
}