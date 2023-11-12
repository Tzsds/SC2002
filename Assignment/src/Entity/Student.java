package Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Controller.Camp.CampManager;
import Controller.Suggestion.SuggestionManager;

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

    public void addRegisteredCamp(Camp camp) {
        registeredCamps.add(camp);
    }


    public static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

    public void viewRegisteredCamps() {
        if (registeredCamps.size() == 0) {
            System.out.println("You have not registered for any camps yet!");
        }
        else {
            System.out.println("Registered Camps:");
            System.out.println("====================================");
            CampManager.printCampsForStudent(registeredCamps);
        }
    }
}