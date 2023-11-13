package Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import Controller.Account.LoginManager;

public class CampDetails {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate closeDate;
    private String userGroup;
    private String location;
    private int slots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;
    private String faculty;

    // constructor
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


    // set functions
    public void setCampName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }

    public void setCloseDate(LocalDate date) {
        this.closeDate = date;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotalSlots(int slots) {
        this.slots = slots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public void setVisibility(boolean bool) {
        this.visibility = bool;
    }

    // get functions
    public String getFaculty(){
        return faculty;
    }

    public String getCampName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalSlots() {
        return slots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public boolean getVisibility() {
        return visibility;
    }

    // Helper method to check if the camp exists
    public static boolean campExists(String campName) {
        String campDetailsCSV = "Assignment/database/camp_details.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(campDetailsCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String currentCampName = row[0].trim(); // Assuming camp name is in the first column
                // Check if the currentCampName matches the provided campName
                if (currentCampName.equalsIgnoreCase(campName)) {
                    return true; // Camp exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it, display an error message, etc.)
        }
        return false; // Camp does not exist
    }

    
}