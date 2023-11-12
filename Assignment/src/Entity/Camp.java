package Entity;
import java.util.ArrayList;

public class Camp {
    private CampDetails campDetails;
    private ArrayList<Student> participants = new ArrayList<>();
    private ArrayList<Student> campComittee = new ArrayList<>();;
    private Enquiry enquiries;
    private ArrayList<Student> withdrawnStudents = new ArrayList<>();;
    private ArrayList<Suggestion> listOfSuggestions = new ArrayList<Suggestion>();

    //constructor
    public Camp(CampDetails CampDetails) {
        this.campDetails = CampDetails;
    }

    public Camp() {
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
    public ArrayList<Student> getCampComittee() {
        return campComittee;
        
    }
    
    public ArrayList<Suggestion> getListOfSuggestions(){
        return listOfSuggestions;
    }
    public String convertToString(){
        return campDetails.getCampName() + "," +
        campDetails.getStartDate() + "," +
        campDetails.getEndDate() + "," +
        campDetails.getCloseDate() + "," +
        campDetails.getUserGroup() + "," +
        campDetails.getLocation() + "," +
        campDetails.getTotalSlots() + "," +
        campDetails.getCampComitteeSlots() + "," +
        campDetails.getDescription() + "," +
        campDetails.getStaffInCharge() + "," +
        campDetails.getVisibility();

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
    public void editCampComitteeSlots(int newComitteeSlots){
        campDetails.setCampComitteeSlots(newComitteeSlots);
    }
    public void editDecsription(String newDescription){
        campDetails.setDescription(newDescription);
    }
    public void editVisibility(boolean newVisibility){
        campDetails.setVisibility(newVisibility);
    }
    

    public void addParticipants(Student student) {
        participants.add(student);
        System.out.println(participants.size());
    }

    public void addCampCommittee(Student student) {
        campComittee.add(student);
    }
}
