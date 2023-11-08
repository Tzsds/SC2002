package Entity;
import java.util.ArrayList;

public class Camp {
    private CampDetails campDetails;
    private ArrayList<Student> participants;
    private ArrayList<Student> campComittee;
    private Enquiry enquiries;
    private ArrayList<Student> withdrawnStudents;

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
}
