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

    public void addParticipants(Student student) {
        participants.add(student);
        System.out.println(participants.size());
    }

    public void addCampCommittee(Student student) {
        campComittee.add(student);
    }
}
