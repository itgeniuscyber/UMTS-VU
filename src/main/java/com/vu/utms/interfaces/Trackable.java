package com.vu.utms.interfaces;

/**
 * The Trackable interface defines the contract for objects that can be tracked
 * in the University Transport Management System.
 * This interface is implemented by vehicle classes to provide real-time location
 * and status tracking capabilities.
 */
public interface Trackable {
    /**
     * Updates the current location of the trackable object
     * @param latitude The latitude coordinate
     * @param longitude The longitude coordinate
     */
    void updateLocation(double latitude, double longitude);
    
    /**
     * Gets the current location of the trackable object
     * @return String representing the current location coordinates
     */
    String getCurrentLocation();
    
    /**
     * Updates the current status of the trackable object
     * @param status The current operational status (e.g., "In Service", "Out of Service")
     */
    void updateStatus(String status);
    
    /**
     * Gets the current operational status
     * @return String representing the current status
     */
    String getCurrentStatus();
}