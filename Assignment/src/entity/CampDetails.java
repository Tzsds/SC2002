package entity;

import java.time.LocalDate;

import repository.CampRepository;

/**
 * This class contains the details of a camp, including its name, dates, user group, location, 
 * available slots, camp committee slots, description, staff in charge, and visibility status
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class CampDetails {
    /** The name of the camp */
    private String name;
    /** The start date of the camp */
    private LocalDate startDate;
    /** The end date of the camp */
    private LocalDate endDate;
    /** The registration close date of the camp */
    private LocalDate closeDate;
    /** The user group associated with the camp */
    private String userGroup;
    /** The location of the camp */
    private String location;
    /** The total number of available slots in the camp */
    private int slots;
    /** The number of camp committee slots available in the camp */
    private int campCommitteeSlots;
    /** The description of the camp */
    private String description;
    /** The staff in charge of the camp */
    private String staffInCharge;
    /** The visibility status of the camp */
    private boolean visibility;
    /** The faculty associated with the camp */
    private String faculty;

    /**
     * Constructs a new CampDetails object with the specified attributes
     *
     * @param name - the name of the camp
     * @param startDate - the start date of the camp
     * @param endDate - the end date of the camp
     * @param closeDate - the close date of the camp
     * @param userGroup - the user group associated with the camp
     * @param location - the location of the camp
     * @param slots - the total number of available slots in the camp
     * @param campCommitteeSlots - the number of camp committee slots available in the camp
     * @param description - the description of the camp
     * @param staffID - the staff in charge of the camp
     * @param visibility - the visibility status of the camp
     */
    public CampDetails(
            String name, LocalDate startDate, LocalDate endDate, LocalDate closeDate, 
            String userGroup,String location, int slots, int campComitteeSlots,
            String description, String staffID, boolean visibility) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeDate = closeDate;
        this.userGroup = userGroup;
        this.location = location;
        this.slots = slots;
        this.campCommitteeSlots = campComitteeSlots;
        this.description = description;
        this.visibility = visibility;
        this.staffInCharge = staffID;
    }


    /**
     * Sets the camp name of the camp
     * @param name - the camp name
     */
    public void setCampName(String name) {
        this.name = name;
    }
    /**
     * Sets the start date of the camp
     * @param date - the start date
     */
    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }
    /**
     * Sets the end date of the camp
     * @param date - the end date
     */
    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }
    /**
     * Sets the registration close date of the camp
     * @param date - the registration close date
     */
    public void setCloseDate(LocalDate date) {
        this.closeDate = date;
    }
    /**
     * Sets the user group of the camp
     * @param userGroup - the user group
     */
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
    /**
     * Sets the location of the camp
     * @param location - the location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Sets the total slots of the camp
     * @param slots - the amount of total slots
     */
    public void setTotalSlots(int slots) {
        this.slots = slots;
    }
    /**
     * Sets the camp committee slots of the camp
     * @param campCommitteeSlots - the amount of camp committee slots
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }
    /**
     * Sets the description of the camp
     * @param description - the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Sets the staff in charge of the camp
     * @param staffInCharge - the staff in charge
     */
    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }
    /**
     * Sets the visiblity of the camp
     * @param bool - the visibility of the camp
     */
    public void setVisibility(boolean bool) {
        this.visibility = bool;
    }

    /**
     * Gets the faculty of the camp
     * @return the faculty of the camp
     */
    public String getFaculty(){
        return faculty;
    }
    /**
     * Gets the camp name of the camp
     * @return the camp name of the camp
     */
    public String getCampName() {
        return name;
    }
    /**
     * Gets the start date of the camp
     * @return the start date of the camp
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Gets the end date of the camp
     * @return the end date of the camp
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * Gets the registrartion closing date of the camp
     * @return the registration closing date of the camp
     */
    public LocalDate getCloseDate() {
        return closeDate;
    }
    /**
     * Gets the user group of the camp
     * @return the user group of the camp
     */
    public String getUserGroup() {
        return userGroup;
    }
    /**
     * Gets the location of the camp
     * @return the location of the camp
     */
    public String getLocation() {
        return location;
    }
    /**
     * Gets the total slots of the camp
     * @return the total slots of the camp
     */
    public int getTotalSlots() {
        return slots;
    }
    /**
     * Gets the faculty of the camp
     * @return the faculty of the camp
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }
    /**
     * Gets the description of the camp
     * @return the description of the camp
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the staff in charge of the camp
     * @return the staff in charge of the camp
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }
    /**
     * Gets the visibility of the camp
     * @return the visibility of the camp
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Helper method to check if a camp with the given name exists in the repository
     *
     * @param campName - the name of the camp to check
     * @return {@code true} if the camp exists, {@code false} otherwise
     */
    public static boolean campExists(String campName) {
        if (CampRepository.getCampByCampName(campName) != null){
            return true;
        }
        else{
            return false;
        }
    }

    
}