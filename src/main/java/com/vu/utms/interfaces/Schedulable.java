package com.vu.utms.interfaces;

/**
 * The Schedulable interface defines the contract for objects that can be scheduled
 * in the University Transport Management System.
 * This interface is implemented by vehicle classes to manage scheduling and
 * availability of transport services.
 */
public interface Schedulable {
    /**
     * Checks if the object is available for scheduling at a specific time
     * @param dateTime The date and time to check availability
     * @return boolean indicating if the object is available
     */
    boolean isAvailable(String dateTime);
    
    /**
     * Schedules the object for a specific time slot
     * @param dateTime The date and time to schedule
     * @param duration The duration of the scheduled period
     * @param purpose The purpose of the scheduling
     * @return boolean indicating if scheduling was successful
     */
    boolean schedule(String dateTime, int duration, String purpose);
    
    /**
     * Cancels an existing schedule
     * @param scheduleId The unique identifier of the schedule to cancel
     * @return boolean indicating if cancellation was successful
     */
    boolean cancelSchedule(String scheduleId);
    
    /**
     * Gets the current schedule information
     * @return String containing the current schedule details
     */
    String getScheduleInfo();
}