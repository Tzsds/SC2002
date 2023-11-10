package Entity;
import java.time.LocalDate;

public class CampDetails {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate closeDate;
    private String openTo;
    private String location;
    private int slots;
    private int campComitteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;

    //constructor
    public CampDetails() {
    }

    //set functions
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
    //public void setOpenTo(String)
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTotalSlots(int slots) {
        this.slots = slots;
    }
    public void setCampComitteeSlots(int campComitteeSlots) {
        this.campComitteeSlots = campComitteeSlots;
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

    //get functions
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
    public String getOpenTo() {
        return openTo;
    }
    public String getLocation() {
        return location;
    }
    public int getTotalSlots() {
        return slots;
    }
    public int getCampComitteeSlots() {
        return campComitteeSlots;
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
}