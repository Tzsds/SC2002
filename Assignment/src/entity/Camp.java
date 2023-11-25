package entity;
import java.util.ArrayList;

/**
 * This is the camp entity file that contains information about a camp, 
 * including details, participants, camp committee members, withdrawn students, and suggestions
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class Camp {
    /** The details of the camp */
    private CampDetails campDetails;
    /** The list of participants in the camp */
    private ArrayList<Student> participants = new ArrayList<>();
    /** The list of participants in the camp */
    private ArrayList<Student> campCommittee = new ArrayList<>();;
    /** The list of students who have withdrawn from the camp */
    private ArrayList<Student> withdrawnStudents = new ArrayList<>();;
     /** The list of suggestions related to the camp */
    private ArrayList<Suggestion> listOfSuggestions = new ArrayList<Suggestion>();

    /**
     * Constructs a new Camp object with the specified camp details
     * @param campDetails - the details of the camp
     */
    public Camp(CampDetails CampDetails) {
        this.campDetails = CampDetails;
    }

     /**
     * Sets the camp details for the camp
     * @param campDetails - the new camp details
     */
    public void setCampDetails(CampDetails campDetails) {
        this.campDetails = campDetails;
    }

    /**
     * Retrieves the camp details for the camp
     * @return the camp details
     */
    public CampDetails getCampDetails() {
        return campDetails;
    }
    /**
     * Retrieves participant list for the camp
     * @return the participant list
     */
    public ArrayList<Student> getParticipants() {
        return participants;
    }
    /**
     * Retrieves camp committee list for the camp
     * @return the camp committee list
     */
    public ArrayList<Student> getCampCommittee() {
        return campCommittee; 
    }
    /**
     * Retrieves withdrawn student list for the camp
     * @return the withdrawn student list
     */
    public ArrayList<Student> getWithdrawnStudents() {
        return withdrawnStudents; 
    }
    /**
     * Retrieves suggestion list for the camp
     * @return the suggestion list
     */
    public ArrayList<Suggestion> getListOfSuggestions(){
        return listOfSuggestions;
    }
    
    /**
     * Edits the camp name
     * @param newName - the new name for the camp
     */
    public void editCampName(String newName){
        campDetails.setCampName(newName);
    }
    /**
     * Edits the camp location
     * @param newLocation - the new location for the camp
     */
    public void editLocation(String newLocation){
        campDetails.setLocation(newLocation);
    }
    /**
     * Edits the camp user group
     * @param newUserGroup - the new user group for the camp
     */
    public void editUserGroup(String newUserGroup){
        campDetails.setUserGroup(newUserGroup);
    }
     /**
     * Edits the camp total slots
     * @param newTotalSlots - the new total slots for the camp
     */
    public void editTotalSlots(int newTotalSlots){
        campDetails.setTotalSlots(newTotalSlots);
    }
     /**
     * Edits the camp camp committee slots
     * @param newCommitteeSlots - the new camp committee slots for the camp
     */
    public void editCampCommitteeSlots(int newCommitteeSlots){
        campDetails.setCampCommitteeSlots(newCommitteeSlots);
    }
    /**
     * Edits the camp description slots
     * @param newDescription - the new description for the camp
     */
    public void editDecsription(String newDescription){
        campDetails.setDescription(newDescription);
    }
    /**
     * Edits the camp visibility slots
     * @param newVisibility - the new visibility for the camp
     */
    public void editVisibility(boolean newVisibility){
        campDetails.setVisibility(newVisibility);
    }
    
    /**
     * Adds a particpant into the participant list
     * @param student - the student being added into the list
     */
    public void addParticipants(Student student) {
        participants.add(student);
    }
    /**
     * Adds a camp committee into the camp committee list
     * @param student - the student being added into the list
     */
    public void addCampCommittee(Student student) {
        campCommittee.add(student);
    }
    /**
     * Adds a particpant into the withdrawn students list
     * @param student - the student being added into the list
     */
    public void addWithdrawnStudent(Student student){
        withdrawnStudents.add(student);
    }
    /**
     * Adds a particpant into the withdrawn student list and removed from the participant list
     * @param student - the student being added into the withdrawn list and removed from participant list
     */
    public void withdrawStudent(Student student){
        participants.remove(student);
        withdrawnStudents.add(student);
    }
}
