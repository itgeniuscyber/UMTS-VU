package com.vu.utms.models;

import java.io.Serializable;

public class Van extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    // Van-specific attributes
    private boolean hasCargoSpace;
    private double cargoCapacity; // in cubic meters
    private String purposeType; // e.g., "Staff Transport", "Cargo", "Special Needs"
    private boolean hasFirstAidKit;
    
    /**
     * Constructor for creating a new van
     * @param vehicleId Unique identifier for the van
     * @param registrationNumber Van registration number
     * @param model Van model
     * @param capacity Passenger capacity
     * @param hasCargoSpace Whether van has dedicated cargo space
     * @param cargoCapacity Cargo capacity in cubic meters
     * @param purposeType Primary purpose of the van
     * @param hasFirstAidKit First aid kit availability
     */
    public Van(String vehicleId, String registrationNumber, String model,
               int capacity, boolean hasCargoSpace, double cargoCapacity,
               String purposeType, boolean hasFirstAidKit) {
        super(vehicleId, registrationNumber, model, capacity);
        this.hasCargoSpace = hasCargoSpace;
        this.cargoCapacity = cargoCapacity;
        this.purposeType = purposeType;
        this.hasFirstAidKit = hasFirstAidKit;
        // this.purpose = "General Purpose";
    }
    
    // Getter and Setter methods for van-specific attributes
    public boolean hasCargoSpace() { return hasCargoSpace; }
    public void setHasCargoSpace(boolean hasCargoSpace) { 
        this.hasCargoSpace = hasCargoSpace; 
    }
    
    public double getCargoCapacity() { return cargoCapacity; }
    public void setCargoCapacity(double cargoCapacity) { 
        this.cargoCapacity = cargoCapacity; 
    }
    
    public String getPurposeType() { return purposeType; }
    public void setPurposeType(String purposeType) { this.purposeType = purposeType; }
    
    public boolean hasFirstAidKit() { return hasFirstAidKit; }
    public void setHasFirstAidKit(boolean hasFirstAidKit) { 
        this.hasFirstAidKit = hasFirstAidKit; 
    }
    
    /**
     * Implementation of abstract method from Vehicle class
     * Calculates van-specific fuel efficiency
     * @return double representing fuel efficiency in km/l
     */
    @Override
    public double calculateFuelEfficiency() {
        // Implementation for van fuel efficiency calculation
        return 10.5; // Example value in km/L
    }
    
    /**
     * Implementation of abstract method from Vehicle class
     * @return String indicating this is a Van type vehicle
     */
    @Override
    public String getVehicleType() {
        return "Van";
    }
    
    /**
     * Implementation of Serviceable interface method
     * Checks if service is due based on van-specific criteria
     * @return boolean indicating if service is due
     */
    @Override
    public boolean isServiceDue() {
        // Van-specific service due logic
        // Example: Check based on last service date
        if (getLastServiceDate() == null) return true;
        // Add actual service interval check logic here
        return false;
    }
    
    /**
     * Implementation of Schedulable interface method
     * @param dateTime The date and time to check availability
     * @return boolean indicating if the van is available
     */
    @Override
    public boolean isAvailable(String dateTime) {
        // Van-specific availability check
        return "Available".equals(getCurrentStatus());
    }
    
    /**
     * Implementation of Schedulable interface method
     * @param dateTime The date and time to schedule
     * @param duration The duration of the scheduled period
     * @param purpose The purpose of the scheduling
     * @return boolean indicating if scheduling was successful
     */
    @Override
    public boolean schedule(String dateTime, int duration, String purpose) {
        if (isAvailable(dateTime)) {
            updateStatus("Scheduled");
            return true;
        }
        return false;
    }
    
    /**
     * Implementation of Schedulable interface method
     * @param scheduleId The unique identifier of the schedule to cancel
     * @return boolean indicating if cancellation was successful
     */
    @Override
    public boolean cancelSchedule(String scheduleId) {
        updateStatus("Available");
        return true;
    }
    
    /**
     * Implementation of Schedulable interface method
     * @return String containing the current schedule information
     */
    @Override
    public String getScheduleInfo() {
        return String.format("Van Schedule Information\n" +
                           "Van ID: %s\n" +
                           "Purpose Type: %s\n" +
                           "Status: %s",
                           getVehicleId(), purposeType, getCurrentStatus());
    }
    
    /**
     * Van-specific method for checking cargo capacity availability
     * @param requiredCapacity Required cargo space in cubic meters
     * @return boolean indicating if the van can accommodate the cargo
     */
    public boolean checkCargoCapacity(double requiredCapacity) {
        return hasCargoSpace && cargoCapacity >= requiredCapacity;
    }
    
    /**
     * Van-specific method for displaying detailed information
     * @return String containing van details
     */
    public void displayCargoInformation() {
        System.out.println("Van Cargo Information:");
        System.out.println("Cargo Capacity: " + cargoCapacity + " kg");
        System.out.println("Purpose: " + purposeType);
        System.out.println("Current Location: " + getCurrentLocation());
        System.out.println("Status: " + getCurrentStatus());
    }
    }
