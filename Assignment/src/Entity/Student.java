package Entity;

import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    private ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
   
    public Student(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }

    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }

    public ArrayList<Camp> getRegisteredCamps(){
        return registeredCamps;
    }
}
