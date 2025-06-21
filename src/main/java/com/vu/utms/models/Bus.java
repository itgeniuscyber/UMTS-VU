package com.vu.utms.models;

import java.io.Serializable;

/**
 * Bus class represents a bus vehicle in the transport system.
 * It extends the Vehicle class and provides bus-specific implementations
 * of abstract methods and additional functionality.
 */
public class Bus extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    // Bus-specific attributes
    private String routeNumber;
    private boolean isAccessible; // For wheelchair accessibility
    private boolean hasWifi;
    private int standingCapacity;
    
    /**
     * Constructor for creating a new bus
     * @param vehicleId Unique identifier for the bus
     * @param registrationNumber Bus registration number
     * @param model Bus model
     * @param capacity Seated passenger capacity
     * @param routeNumber Assigned route number
     * @param isAccessible Wheelchair accessibility status
     * @param hasWifi WiFi availability status
     * @param standingCapacity Standing passenger capacity
     */
    public Bus(String vehicleId, String registrationNumber, String model, 
               int capacity, String routeNumber, boolean isAccessible,
               boolean hasWifi, int standingCapacity) {
        super(vehicleId, registrationNumber, model, capacity);
        this.routeNumber = routeNumber;
        this.isAccessible = isAccessible;
        this.hasWifi = hasWifi;
        this.standingCapacity = standingCapacity;
    }
    
    // Getter and Setter methods for bus-specific attributes
    public String getRouteNumber() { return routeNumber; }
    public void setRouteNumber(String routeNumber) { this.routeNumber = routeNumber; }
    
    public boolean isAccessible() { return isAccessible; }
    public void setAccessible(boolean accessible) { isAccessible = accessible; }
    
    public boolean hasWifi() { return hasWifi; }
    public void setHasWifi(boolean hasWifi) { this.hasWifi = hasWifi; }
    
    public int getStandingCapacity() { return standingCapacity; }
    public void setStandingCapacity(int standingCapacity) { 
        this.standingCapacity = standingCapacity; 
    }
    
    /**
     * Implementation of abstract method from Vehicle class
     * Calculates bus-specific fuel efficiency
     * @return double representing fuel efficiency in km/l
     */
    @Override
    public double calculateFuelEfficiency() {
        // Implementation for bus fuel efficiency calculation
        return 8.5; // Example value in km/L
    }
    
    /**
     * Implementation of abstract method from Vehicle class
     * @return String indicating this is a Bus type vehicle
     */
    @Override
    public String getVehicleType() {
        return "Bus";
    }
    
    /**
     * Implementation of Serviceable interface method
     * Checks if service is due based on bus-specific criteria
     * @return boolean indicating if service is due
     */
    @Override
    public boolean isServiceDue() {
        // Bus-specific service due logic
        // Example: Check based on last service date
        if (getLastServiceDate() == null) return true;
        // Add actual service interval check logic here
        return false;
    }
    
    /**
     * Implementation of Schedulable interface method
     * @param dateTime The date and time to check availability
     * @return boolean indicating if the bus is available
     */
    @Override
    public boolean isAvailable(String dateTime) {
        // Bus-specific availability check
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
        return String.format("Bus Schedule Information\n" +
                           "Bus ID: %s\n" +
                           "Route Number: %s\n" +
                           "Status: %s",
                           getVehicleId(), routeNumber, getCurrentStatus());
    }
    
    /**
     * Bus-specific method for displaying route information
     * @return String containing route details
     */
    public String getRouteInfo() {
        return String.format("Route Information\n" +
                           "Route Number: %s\n" +
                           "Bus ID: %s\n" +
                           "Total Capacity: %d (Seated: %d, Standing: %d)\n" +
                           "Accessibility: %s\n" +
                           "WiFi: %s",
                           routeNumber, getVehicleId(),
                           (capacity + standingCapacity), capacity, standingCapacity,
                           isAccessible ? "Wheelchair Accessible" : "Not Accessible",
                           hasWifi ? "Available" : "Not Available");
    }
}