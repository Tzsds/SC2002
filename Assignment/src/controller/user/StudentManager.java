package controller.user;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.account.LoginManager;
import controller.camp.CampManager;
import controller.file.FileWriting;
import controller.utils.Filter;
import controller.utils.InputScanner;
import controller.utils.Sort;
import entity.Camp;
import entity.CampCommittee;
import entity.CampDetails;
import entity.Student;
import repository.CampRepository;

/**
 * This class provides methods to allow Student to 
 * perform actions related to camp such as
 * registering for camps, withdrawing from camps and 
 * viewing available camps based on filter
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StudentManager {
    
    /**
     * Displays a list of available camps to the student depending on various conditions
     * such as whether they are already registered, if they have withdrawn, 
     * remaining slots and date clash 
     * The student can choose to register for a camp from the displayed list
     */
    public static void registerForCamps() {
        System.out.println("=================================");
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        int userChoice;
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        int count = 0; // list of available camps to the student
        for (Camp c : listOfCamps) {
            boolean printCamp = true;
            boolean studentRegistered = false;
            CampDetails detail = c.getCampDetails();
            LocalDate campEndDate = c.getCampDetails().getEndDate();
            LocalDate campStartDate = c.getCampDetails().getStartDate();
            LocalDate campCloseDate = c.getCampDetails().getCloseDate();
            for (Camp camp : s.getRegisteredCamps()) {
                if (camp == c) {
                    studentRegistered = true;
                    break;
                }
            }
            if (c.getCampDetails().getTotalSlots() <= 0) {
                printCamp = false;
            }
            if (studentRegistered) {
                printCamp = false;
            } else {
                if (!detail.getVisibility()) {
                    continue;
                } else {
                    if (detail.getUserGroup().equals("Everyone")) {
                        if (!s.getRegisteredCamps().isEmpty()) {
                            for (Camp ca : s.getRegisteredCamps()) {
                                LocalDate registeredEndDate = ca.getCampDetails().getEndDate();
                                LocalDate registeredStartDate = ca.getCampDetails().getStartDate();
                                if (!campStartDate.isBefore(registeredStartDate) && !campStartDate.isAfter(registeredEndDate)) {
                                    printCamp = false;
                                } else if (!campEndDate.isBefore(registeredStartDate) && !campEndDate.isAfter(registeredEndDate)) {
                                    printCamp = false;
                                } else if (LocalDate.now().isAfter(campCloseDate)) {
                                    printCamp = false;
                                } else if (!registeredStartDate.isBefore(campStartDate) && !registeredEndDate.isAfter(campEndDate)) {
                                    printCamp = false;
                                } else {
                                    System.out.println(campCloseDate);
                                }
                            }
                        } else {
                            if (LocalDate.now().isAfter(campCloseDate)) {
                                    printCamp = false;
                                }
                        }

                    } else if (detail.getUserGroup().equals(faculty)) {
                        if (!s.getRegisteredCamps().isEmpty()) {
                            for (Camp ca : s.getRegisteredCamps()) {
                                LocalDate registeredEndDate = ca.getCampDetails().getEndDate();
                                LocalDate registeredStartDate = ca.getCampDetails().getStartDate();
                                if (!campStartDate.isBefore(registeredStartDate)
                                        && !campStartDate.isAfter(registeredEndDate)) {
                                    printCamp = false;
                                } else if (!campEndDate.isBefore(registeredStartDate)
                                        && !campEndDate.isAfter(registeredEndDate)) {
                                    printCamp = false;
                                } else if (LocalDate.now().isAfter(campCloseDate)) {
                                    printCamp = false;
                                } else if (!registeredStartDate.isBefore(campStartDate)
                                        && !registeredEndDate.isAfter(campEndDate)) {
                                    printCamp = false;
                                } else {
                                    System.out.println(campCloseDate);
                                }
                            }
                        } else {
                            if (LocalDate.now().isAfter(campCloseDate)) {
                                    printCamp = false;
                                }
                        }
                    }

                }

            }
            if (!c.getWithdrawnStudents().isEmpty()) {
                for (Student withdrawn : c.getWithdrawnStudents()) {
                    if (s == withdrawn) {
                        printCamp = false;
                    } else {

                    }
                }
            } else {

            }
            if (printCamp) {
                System.out.println("Camp " + ++count);
                CampManager.printCampRegistrationForStudents(detail);
                list.add(c);
                System.out.println("=================================");
            }
        }
        if (count == 0) {
            System.out.println("There is currently no available camps for you to register.");
        } else {
            System.out.println("If you want to exit please type " + ++count);
            while (true) {
                userChoice = InputScanner
                        .promptForInt("Please input the number of the camp you would like to register for \n");
                if (userChoice <= 0 || userChoice > count)
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
                    int committeeDecision = 0;
                    while (committeeDecision <= 0 || committeeDecision > 2) {
                        committeeDecision = InputScanner.promptForInt(
                                "Do you want to be part of the committee for this camp? Enter 1 for Yes or 2 for No\n");
                        System.out.println("Enter a valid option");
                    }
                    if (committeeDecision == 1) {
                        CampCommittee tempStudent = CampCommitteeManager.toCampCommittee(s);
                        tempStudent.setCommitteeOf(registeredCamp);
                        registeredCamp.addCampCommittee(tempStudent);
                        FileWriting.FileWriteCampStudentList();
                        tempStudent.addRegisteredCamp(registeredCamp);
                        registeredCamp
                                .editCampCommitteeSlots(registeredCamp.getCampDetails().getCampCommitteeSlots() - 1);
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                        FileWriting.FileWriteCampDetails();
                        System.out.println("Camp Successfully Registered as Camp Committee!");
                    } else {
                        registeredCamp.addParticipants(s);
                        FileWriting.FileWriteCampStudentList();
                        registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                        s.addRegisteredCamp(registeredCamp);
                        FileWriting.FileWriteCampDetails();
                        System.out.println("Camp Successfully Registered as Camp Attendee!");
                    }

                } else {
                    registeredCamp.addParticipants(s);
                    FileWriting.FileWriteCampStudentList();
                    registeredCamp.editTotalSlots(registeredCamp.getCampDetails().getTotalSlots() - 1);
                    s.addRegisteredCamp(registeredCamp);
                    FileWriting.FileWriteCampDetails();
                    System.out.println("Camp Successfully Registered as Camp Attendee!");
                }

            }
        }
    }

    /**
     * Displays a list of camps the student is currently registered and 
     * allows the student to withdraw from a selected camp
     */
    public static void withdrawFromCamp() {
        System.out.println("=================================");
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        int userChoice;
        int userConfirm = 0;
        int count = 0;
        ArrayList<Camp> registeredCampList = s.getRegisteredCamps();
        for (Camp c : registeredCampList) {
            boolean printCamp = true;
            CampDetails detail = c.getCampDetails();
            if (!c.getCampCommittee().isEmpty()) {
                for (Student stud : c.getCampCommittee()) {
                    if (s == stud) {
                        printCamp = false;
                    } else {
                        if (!c.getWithdrawnStudents().isEmpty()) {
                            for (Student withdrawn : c.getWithdrawnStudents()) {
                                if (s == withdrawn) {
                                    printCamp = false;
                                } else {

                                }
                            }
                        } else {

                        }
                    }
                }
            } else {
                if (!c.getWithdrawnStudents().isEmpty()) {
                    for (Student withdrawn : c.getWithdrawnStudents()) {
                        if (s == withdrawn) {
                            printCamp = false;
                        } else {

                        }
                    }
                } else {

                }
            }
            if (printCamp) {
                System.out.println("Camp " + ++count);
                CampManager.printCampRegistrationForStudents(detail);
                list.add(c);
                System.out.println("=================================");
            }
        }

        if (count == 0) {
            System.out.println("There are no camps for you to withdraw from.");
        } else {
            System.out.println("If you would like to return please input " + ++count);
            while (true) {
                userChoice = InputScanner
                        .promptForInt("Please input the number of the camp you would like to withdraw from \n");
                if (userChoice <= 0 || userChoice > count)
                    System.out.println("Invalid Input");
                else
                    break;
            }
            if (userChoice == count) {

            } else {
                Camp registeredCamp = list.get(userChoice - 1);
                System.out.println(
                        "The camp you are withdrawing from is " + registeredCamp.getCampDetails().getCampName());
                while (true) {
                    userConfirm = InputScanner
                            .promptForInt("Are you sure you want to withdraw? Enter 1 for Yes or 2 for No\n");
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
                    FileWriting.FileWriteCampDetails();
                    System.out.println("Camp Successfully Withdrawn!");
                } else {

                }
            }
        }
    }
    /**
     * Displays a sorted and filtered list of availabe camps to the student based on
     * their faculty and if the camp visibility is open
     */
    public static void viewAvailableCamps() {
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();

        // Default alphabetically sorted
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        listOfCamps = Sort.insertionSortByName(listOfCamps);
        CampRepository.setListOfCamps(listOfCamps); // So that subsequent sort is faster

        // Choosing filter method
        int filter = Filter.promptForFilter();
        switch (filter) {
            case 1: // name
                String filterName = InputScanner.promptForString("What name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterName);
                break;
            case 2: // location
                String filterLocation = InputScanner.promptForString("What location do you want to filter by?: ");
                listOfCamps = Filter.filterByLocation(listOfCamps, filterLocation);
                break;
            case 3: // date
                LocalDate filterDate = InputScanner
                        .promptForDate("What date do you want to filter by in format (dd/mm/yyyy)? : ", 1);
                listOfCamps = Filter.filterByDate(listOfCamps, filterDate);
                break;
        }
        System.out.println();
        if (listOfCamps.size() == 0) {
            System.out.println("No available camps found.");
            return;
        }

        int count = 0; // list of available camps to the student
        System.out.println("=================================");
        for (Camp c : listOfCamps) {
            CampDetails detail = c.getCampDetails();
            if (!detail.getVisibility()) {
                continue;
            } else {
                if (detail.getUserGroup().equals("Everyone")) {
                    // print
                    if (LocalDate.now().isAfter(c.getCampDetails().getCloseDate())) {

                    } else {
                        CampManager.printCampDetailsForStudents(detail);
                        System.out.println("=================================");
                        list.add(c);
                        count++;
                    }
                } else {
                    if (detail.getUserGroup().equals(faculty)) {
                        // print
                        if (LocalDate.now().isAfter(c.getCampDetails().getCloseDate())) {

                        } else {
                            CampManager.printCampDetailsForStudents(detail);
                            System.out.println("=================================");
                            list.add(c);
                            count++;
                        }
                    }
                }
            }
        }

        if (count == 0) {
            System.out.println("There is currently no available camps.");
        }
    }
}