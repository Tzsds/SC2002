package Controller.Users;

import java.util.ArrayList;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.Suggestion.StaffSuggestionManager;
import Entity.Camp;
import Entity.CampDetails;
import Entity.Staff;
import Repository.CampRepository;

public class StaffManager {

    public static void viewAllCamps(){
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        if (listOfCamps.size() == 0){
            System.out.println("There is no existing camps.");
        }
        else{
            for (Camp c : listOfCamps){
                System.out.println("==========================");
                CampDetails detail = c.getCampDetails();
                CampManager.printCampDetails(detail);
            }
            System.out.println("==========================");
        }
    }

    public static void viewCampsCreated(ArrayList<Camp> campCreated){
        for (Camp c: campCreated){
            System.out.println("==========================");
            CampDetails detail = c.getCampDetails();
            CampManager.printCampDetails(detail);
        }
        System.out.println("==========================");
    }

    public static void editCamp(){
        
    }

    public static void viewSuggestions(){
        Staff currentStaff = (Staff)LoginManager.getCurrentUser();
        StaffSuggestionManager.printSuggestions(currentStaff.getListOfCampsCreated());
    }

}
