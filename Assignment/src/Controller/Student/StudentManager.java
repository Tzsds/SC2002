package Controller.Student;

import java.util.ArrayList;

import Controller.Account.LoginManager;
import Entity.Camp;
import Entity.Student;
import Repository.CampRepository;

public class StudentManager {
    //Register For Camp (based on school and visibility)
    //Withdraw From Camp 
    //

    public static void registerForCamps(){
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        if (listOfCamps.size() == 0){
            System.out.println("There is currently no available camp");
        }
    }

    public static void viewAvailableCamps(){
        Student s = (Student)LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        if (listOfCamps.size() == 0){
            System.out.println("There is no available camp");
        }
        else{
            //Print those Camps that are open and visbile to the current Student s
            
                
        }

    }
}


