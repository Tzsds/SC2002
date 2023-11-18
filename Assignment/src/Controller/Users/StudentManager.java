package Controller.Users;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.CampDetails;
import Entity.Student;
import Repository.CampRepository;
import UI.InputScanner;

public class StudentManager {

    // WITHDRAW FROM CAMP

    public static void registerForCamps() {
        System.out.println("=================================");
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        int userChoice;
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        int count = 0; // list of available camps to the student
        for (Camp c : listOfCamps) {
            boolean studentRegistered = false;
            for (Camp camp : s.getRegisteredCamps()) {
                if (camp == c) {
                    studentRegistered = true;
                    break;
                }
            }
            // c.getCampDetails().getCloseDate().isBefore(LocalDate.now()) (Check for date
            // before but for testing purposes commented)
            if (studentRegistered) {

            } else {
                CampDetails detail = c.getCampDetails();
                if (!detail.getVisibility()) {
                    continue;
                } else {
                    if (detail.getUserGroup().equals("Everyone") && c.getCampDetails().getTotalSlots()>0 && c.getCampDetails().getCampCommitteeSlots()>0) {
                        // print
                        System.out.println("Camp " + ++count);
                        CampManager.printCampRegistrationForStudents(detail);
                        list.add(c);
                    } else {
                        if (detail.getUserGroup().equals(faculty) && c.getCampDetails().getTotalSlots()>0 && c.getCampDetails().getCampCommitteeSlots()>0) {
                            // print
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                        }
                    }
                    System.out.println("=================================");

                }
            }

        }
        if (count == 0) {
            System.out.println("There is currently no available camps for you to register.");
        } else {
            System.out.println("If you want to exit please type " + ++count);
            while(true){
                userChoice = InputScanner.promptForInt("Please input the number of the camp you would like to register for \n");
                if(userChoice<=0 ||userChoice>count)
                    System.out.println("Invalid Input");
                else    
                    break;
            }
            
            if (userChoice == count) {

            } else {
                Camp registeredCamp = list.get(userChoice - 1);
                System.out.println(
                        "The camp you are registering for is " + registeredCamp.getCampDetails().getCampName());
                if (registeredCamp.getCampDetails().getCampCommitteeSlots() > 0 && !(s instanceof CampCommittee)) {
                    int committeeDecision =0 ;
                    while(committeeDecision<=0 ||committeeDecision>2){
                        committeeDecision = InputScanner.promptForInt("Do you want to be part of the committee for this camp? Enter 1 for Yes or 2 for No\n");
                        System.out.println("Enter a valid option");
                    }
                     if (committeeDecision == 1) {
                        CampCommittee tempStudent = CampCommitteeManager.toCampCommittee(s);
                        tempStudent.setCommitteeOf(registeredCamp);
                        registeredCamp.addCampCommittee(tempStudent);
                        FileWriting.FileWriteCampStudentList();
                        tempStudent.addRegisteredCamp(registeredCamp);
                        registeredCamp.editCampCommitteeSlots(registeredCamp.getCampDetails().getCampCommitteeSlots() - 1);
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                        System.out.println("Camp Successfully Registered as Camp Committee!");
                    }else {
                        registeredCamp.addParticipants(s);
                        FileWriting.FileWriteCampStudentList();
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                        s.addRegisteredCamp(registeredCamp);
                        System.out.println("Camp Successfully Registered as Camp Attendee!");
                    }
                    
                } 
                else {
                    registeredCamp.addParticipants(s);
                    FileWriting.FileWriteCampStudentList();
                    registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                    s.addRegisteredCamp(registeredCamp);
                    System.out.println("Camp Successfully Registered as Camp Attendee!");
                }

            }
        }
    }

    public static void withdrawFromCamp() {
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        int userChoice ;
        int userConfirm = 0;
        int count = 0;
        ArrayList<Camp> registeredCampList = s.getRegisteredCamps();
        for (Camp c : registeredCampList) {
            if (!c.getCampCommittee().isEmpty()) {
                for (Student stud : c.getCampCommittee()) {
                    if (s == stud) {

                    } else {
                        if (!c.getWithdrawnStudents().isEmpty()) {
                            for (Student withdrawn : c.getWithdrawnStudents()) {
                                if (s == withdrawn) {

                                } else {
                                    CampDetails detail = c.getCampDetails();
                                    System.out.println("==========================");
                                    System.out.println("Camp " + ++count);
                                    CampManager.printCampRegistrationForStudents(detail);
                                    list.add(c);
                                    break;
                                }
                            }
                        } else {
                            CampDetails detail = c.getCampDetails();
                            System.out.println("==========================");
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                            break;
                        }
                    }
                }
            } else {
                if (!c.getWithdrawnStudents().isEmpty()) {
                            for (Student withdrawn : c.getWithdrawnStudents()) {
                                if (s == withdrawn) {

                                } else {
                                    CampDetails detail = c.getCampDetails();
                                    System.out.println("==========================");
                                    System.out.println("Camp " + ++count);
                                    CampManager.printCampRegistrationForStudents(detail);
                                    list.add(c);
                                    break;
                                }
                            }
                        } else {
                            CampDetails detail = c.getCampDetails();
                            System.out.println("==========================");
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                            break;
                        }
            }
        }

        if (count == 0) {
            System.out.println("You have not registered for any camps yet.");
        } else {
            System.out.println("If you would like to return please input " + ++count);
            while (true) {
                userChoice = InputScanner.promptForInt("Please input the number of the camp you would like to withdraw from \n");
                if(userChoice <= 0 || userChoice > count)
                    System.out.println("Invalid Input");
                else 
                    break;
            }
            if (userChoice == count) {

            } else {
                Camp registeredCamp = list.get(userChoice - 1);
                System.out.println("The camp you are withdrawing from is " + registeredCamp.getCampDetails().getCampName()+ 
                                    ". Are you sure you want to withdraw? Enter 1 for Yes or 2 for No ");
                while (true) {
                    userConfirm = InputScanner.promptForInt("Enter 1 for Yes or 2 for No \n");
                    if (userConfirm > 2 || userConfirm <= 0)
                        System.out.println("Invalid input. ");
                    else 
                        break;
                }
                if (userConfirm == 1) {
                    registeredCamp.withdrawStudent(s);
                    FileWriting.FileWriteCampStudentList();
                    registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() + 1);
                    s.removeRegisteredCamp(registeredCamp);
                    System.out.println("Camp Successfully Withdrawn!");
                } else {

                }
            }
        }
    }

    public static ArrayList<Camp> viewAvailableCamps() {
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        // System.out.println(faculty);
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        int count = 0; // list of available camps to the student
        System.out.println("=================================");
        for (Camp c : listOfCamps) {
            CampDetails detail = c.getCampDetails();
            if (!detail.getVisibility()) {
                continue;
            } else {
                if (detail.getUserGroup().equals("Everyone")) {
                    // print
                    CampManager.printCampDetailsForStudents(detail);
                    System.out.println("=================================");
                    list.add(c);
                    count++;
                } else {
                    if (detail.getUserGroup().equals(faculty)) {
                        // print
                        CampManager.printCampDetailsForStudents(detail);
                        System.out.println("=================================");
                        list.add(c);
                        count++;
                    }
                }

            }
        }
        if (count == 0) {
            System.out.println("There is currently no available camps.");
        }
        return list;
    }

}
