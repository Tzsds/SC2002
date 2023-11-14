package Controller.Users;


import java.util.ArrayList;
import java.util.Scanner;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Entity.Camp;
import Entity.CampDetails;
import Entity.Student;
import Repository.CampRepository;

public class StudentManager {

    //WITHDRAW FROM CAMP

    public static void registerForCamps(){
        Scanner sc = new Scanner(System.in);
        ArrayList <Camp> list = new ArrayList<>();
        Student s = (Student)LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        int userChoice;
        //System.out.println(faculty);
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        int count = 0; //list of available camps to the student
        for (Camp c : listOfCamps){
            boolean studentRegistered = false;
            for (Camp camp : s.getRegisteredCamps()) {
                if (camp == c) {
                    studentRegistered = true;
                    break;
                }
            }
            //c.getCampDetails().getCloseDate().isBefore(LocalDate.now())  (Check for date before but for testing purposes commented)
            if(studentRegistered){

            }
            else{
                CampDetails detail = c.getCampDetails();
                if (!detail.getVisibility()){
                    continue;
                }
                else{
                    System.out.println("==========================");
                    if (detail.getUserGroup().equals("Everyone")){
                        //print
                        System.out.println("Camp " + ++count);
                        CampManager.printCampRegistrationForStudents(detail);
                        list.add(c);
                        
                    }
                    else{
                        if (detail.getUserGroup().equals(faculty)){
                            //print
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                        }
                    }
                    System.out.println("==========================");
                    
                }
            }
            
            
        }
        if (count == 0){
            System.out.println("There is currently no available camps.");
        }
        else{
            System.out.println("If you want to exit please type " + ++count); 
            System.out.println("Please input the number of the camp you would like to register for");
            userChoice = sc.nextInt();
            if(userChoice == count){

            }
            else{
                Camp registeredCamp = list.get(userChoice-1);
                System.out.println("The camp you are registering for is " + registeredCamp.getCampDetails().getCampName());
                if(registeredCamp.getCampDetails().getCampCommitteeSlots()>0){
                    System.out.println("Do you want to be part of the committee for this camp? Enter 1 for Yes or 2 for No");
                    int committeeDecision;
                    committeeDecision = sc.nextInt();
                    if(committeeDecision == 1){
                        registeredCamp.addCampCommittee(s);
                        FileWriting.FileWriteCampStudentList();
                        s.addRegisteredCamp(registeredCamp);
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getCampCommitteeSlots()-1);
                        System.out.println("Camp Successfully Registered as Camp Committee!");
                    }
                    else{
                        registeredCamp.addParticipants(s);
                        FileWriting.FileWriteCampStudentList();
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots()-1);
                        s.addRegisteredCamp(registeredCamp);
                        System.out.println("Camp Successfully Registered as Camp Attendee!");
                    }
                }
                else{
                    registeredCamp.addParticipants(s);
                    FileWriting.FileWriteCampStudentList();
                    registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots()-1);
                    s.addRegisteredCamp(registeredCamp);
                    System.out.println("Camp Successfully Registered as Camp Attendee!");
                }
                
            }
        }
    }
    
    public static void withdrawFromCamp(){
        
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



