package com.vu.utms.interfaces;

/**
 * The Serviceable interface defines the contract for objects that can be serviced
 * in the University Transport Management System.
 * This interface is primarily implemented by vehicle classes to ensure proper
 * maintenance tracking and service scheduling.
 */
public interface Serviceable {
    /**
     * Checks if the object is due for service based on specific criteria
     * @return boolean indicating if service is due
     */
    boolean isServiceDue();
    
    /**
     * Records a completed service with relevant details
     * @param serviceType The type of service performed
     * @param serviceDate The date when the service was performed
     */
    void recordService(String serviceType, String serviceDate);
    
    /**
     * Gets the date of the last service performed
     * @return String representing the date of last service
     */
    String getLastServiceDate();
}