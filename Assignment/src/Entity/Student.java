package entity;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.camp.CampManager;

public class Student extends User{
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    private ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
   
    public Student(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }
    
    public void setEnquiries(ArrayList<Enquiry> listOfEnquiry){
        enquiries = listOfEnquiry;
    }

    public void setRegisteredCamps(ArrayList<Camp> listOfCamp){
        registeredCamps = listOfCamp;
    }

    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }

    public ArrayList<Camp> getRegisteredCamps(){
        return registeredCamps;
    }

    public void addRegisteredCamp(Camp camp) {
        registeredCamps.add(camp);
    }

    public void removeRegisteredCamp(Camp camp) {
        registeredCamps.remove(camp);
    }
    
    public void viewRegisteredCamps() {
        if (registeredCamps.size() == 0) {
            System.out.println("You have not registered for any camps yet!");
        }
        else {
            System.out.println("Registered Camps:");
            System.out.println("====================================");
            for (Camp camps : registeredCamps){
                if(LocalDate.now().isAfter(camps.getCampDetails().getCloseDate())){

                }
                else{
                    CampManager.printCampDetails(camps.getCampDetails());
                    System.out.println("====================================");
                }
                
            }
        }
    }
}