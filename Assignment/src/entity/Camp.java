package entity;
import java.util.ArrayList;

public class Camp {
    private CampDetails campDetails;
    private ArrayList<Student> participants = new ArrayList<>();
    private ArrayList<Student> campCommittee = new ArrayList<>();;
    private ArrayList<Student> withdrawnStudents = new ArrayList<>();;
    private ArrayList<Suggestion> listOfSuggestions = new ArrayList<Suggestion>();

    //constructor
    public Camp(CampDetails CampDetails) {
        this.campDetails = CampDetails;
    }

    //set functions
    public void setCampDetails(CampDetails campDetails) {
        this.campDetails = campDetails;
    }

    //get functions
    public CampDetails getCampDetails() {
        return campDetails;
    }
    public ArrayList<Student> getParticipants() {
        return participants;
    }
    public ArrayList<Student> getCampCommittee() {
        return campCommittee; 
    }
    public ArrayList<Student> getWithdrawnStudents() {
        return withdrawnStudents; 
    }
    
    public ArrayList<Suggestion> getListOfSuggestions(){
        return listOfSuggestions;
    }
    
    //edit functions
    public void editCampName(String newName){
        campDetails.setCampName(newName);
    }
    public void editLocation(String newLocation){
        campDetails.setLocation(newLocation);
    }
    public void editUserGroup(String newUserGroup){
        campDetails.setUserGroup(newUserGroup);
    }
    public void editTotalSlots(int newTotalSlots){
        campDetails.setTotalSlots(newTotalSlots);
    }
    public void editCampCommitteeSlots(int newCommitteeSlots){
        campDetails.setCampCommitteeSlots(newCommitteeSlots);
    }
    public void editDecsription(String newDescription){
        campDetails.setDescription(newDescription);
    }
    public void editVisibility(boolean newVisibility){
        campDetails.setVisibility(newVisibility);
    }
    
    //functions
    public void addParticipants(Student student) {
        participants.add(student);
    }

    public void addCampCommittee(Student student) {
        campCommittee.add(student);
    }

    public void addWithdrawnStudent(Student student){
        withdrawnStudents.add(student);
    }

    public void withdrawStudent(Student student){
        participants.remove(student);
        withdrawnStudents.add(student);
    }
}
