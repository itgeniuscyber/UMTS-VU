package com.vu.utms.models;

import com.vu.utms.interfaces.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements Serviceable, Trackable, Schedulable, Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> scheduleHistory;
    // Protected fields accessible by child classes
    protected String vehicleId;
    protected String registrationNumber;
    protected String model;
    protected int capacity;
    protected String currentLocation;
    protected String status;
    protected String lastServiceDate;
    
    /**
     * Constructor for creating a new vehicle
     * @param vehicleId Unique identifier for the vehicle
     * @param registrationNumber Vehicle registration number
     * @param model Vehicle model
     * @param capacity Passenger capacity
     */
    public Vehicle(String vehicleId, String registrationNumber, String model, int capacity) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.capacity = capacity;
        this.status = "Available";
        this.currentLocation = "Campus Depot";
        this.scheduleHistory = new ArrayList<>();;
    }
    
    // Getter and Setter methods
   
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { 
        this.registrationNumber = registrationNumber; 
    }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    // Implementation of Trackable interface methods
    @Override
    public void updateLocation(double latitude, double longitude) {
        this.currentLocation = String.format("%.6f, %.6f", latitude, longitude);
    }
    
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }
    
    @Override
    public void updateStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String getCurrentStatus() {
        return status;
    }
    
    // Implementation of Serviceable interface methods
    @Override
    public String getLastServiceDate() {
        return lastServiceDate;
    }
    
    @Override
    public void recordService(String serviceType, String serviceDate) {
        this.lastServiceDate = serviceDate;
        this.status = "Serviced";
    }
    
    /**
     * Abstract method for calculating fuel efficiency
     * To be implemented by specific vehicle types
     * @return double representing fuel efficiency in km/l
     */
    public abstract double calculateFuelEfficiency();
    
    /**
     * Abstract method for getting vehicle type
     * @return String representing the type of vehicle
     */
    public abstract String getVehicleType();
    
    /**
     * Common method for all vehicles to display vehicle information
     * @return String containing vehicle details
     */
    public String getVehicleInfo() {
        return String.format("Vehicle ID: %s\n" +
                           "Type: %s\n" +
                           "Registration: %s\n" +
                           "Model: %s\n" +
                           "Capacity: %d passengers\n" +
                           "Status: %s\n" +
                           "Current Location: %s",
                           vehicleId, getVehicleType(), registrationNumber,
                           model, capacity, status, currentLocation);
    }
}