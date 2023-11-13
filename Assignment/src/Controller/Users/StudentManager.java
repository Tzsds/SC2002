package Controller.Users;


import java.util.ArrayList;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Entity.Camp;
import Entity.CampDetails;
import Entity.Student;
import Repository.CampRepository;

public class StudentManager {

    //WITHDRAW FROM CAMP

    public static void registerForCamps(){
        ArrayList<Camp> listOfCamps = viewAvailableCamps();
        if (listOfCamps.size() == 0){
            return;
        }
        else{
            //Need to check remaining slots
            //Cannot let him register twice
        }
        //once register need add in registered list
        //update csv?
    }

    public static ArrayList<Camp> viewAvailableCamps(){
        ArrayList <Camp> list = new ArrayList<>();
        Student s = (Student)LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        //System.out.println(faculty);
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        int count = 0; //list of available camps to the student
        for (Camp c : listOfCamps){
            CampDetails detail = c.getCampDetails();
            if (!detail.getVisibility()){
                continue;
            }
            else{
                if (detail.getUserGroup().equals("Everyone")){
                    //print
                    System.out.println("==========================");
                    CampManager.printCampDetailsForStudents(detail);
                    System.out.println("==========================");
                    list.add(c);
                    count ++;
                }
                else{
                    if (detail.getUserGroup().equals(faculty)){
                        //print
                        System.out.println("==========================");
                        CampManager.printCampDetailsForStudents(detail);
                        System.out.println("==========================");
                        list.add(c);
                        count ++;
                    }
                }
                
            }
        }
        if (count == 0){
            System.out.println("There is currently no available camps.");
        }
        return list;
        
    }

}



