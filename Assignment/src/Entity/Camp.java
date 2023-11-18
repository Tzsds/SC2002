package Entity;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp implements Comparable {
    private CampDetails campDetails;
    private ArrayList<Student> participants = new ArrayList<>();
    private ArrayList<Student> campCommittee = new ArrayList<>();;
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
    public ArrayList<Student> getCampCommittee() {
        return campCommittee; 
    }
    public ArrayList<Student> getWithdrawnStudents() {
        return withdrawnStudents; 
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
        campDetails.getCampCommitteeSlots() + "," +
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
    public void editCampCommitteeSlots(int newCommitteeSlots){
        campDetails.setCampCommitteeSlots(newCommitteeSlots);
    }
    public void editDecsription(String newDescription){
        campDetails.setDescription(newDescription);
    }
    public void editVisibility(boolean newVisibility){
        campDetails.setVisibility(newVisibility);
    }
    

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

    public int compareTo(Object object) {
        Camp camp = (Camp)object;
        LocalDate startDate = camp.getCampDetails().getStartDate();
        LocalDate now = LocalDate.now();
        System.out.println(startDate);
        if ((startDate).compareTo(now) < 0) {
            return -1;
        }
        else if ((startDate).compareTo(now) > 0) {
            return 1;
        }
        // same date
        return 0;
    }
}
