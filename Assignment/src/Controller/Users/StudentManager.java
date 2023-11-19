package Controller.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Controller.Utilities.Filter;
import Controller.Utilities.Sort;
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
                        if(!s.getRegisteredCamps().isEmpty()){
                            for(Camp ca : s.getRegisteredCamps()){
                                LocalDate campEndDate = c.getCampDetails().getEndDate();
                                LocalDate campStartDate = c.getCampDetails().getStartDate();
                                LocalDate campCloseDate = c.getCampDetails().getCloseDate();
                                LocalDate registeredEndDate = ca.getCampDetails().getEndDate();
                                LocalDate registeredStartDate = ca.getCampDetails().getStartDate();
                                if(!campStartDate.isBefore(registeredStartDate) && !campStartDate.isAfter(registeredEndDate)){
                                        
                                }
                                else if(!campEndDate.isBefore(registeredStartDate) && !campEndDate.isAfter(registeredEndDate)){

                                }
                                else if(LocalDate.now().isAfter(campCloseDate)){

                                }
                                else{
                                    System.out.println("Camp " + ++count);
                                    CampManager.printCampRegistrationForStudents(detail);
                                    list.add(c);
                                    System.out.println("=================================");
                                }
                            }
                        }
                        else{
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                            System.out.println("=================================");
                        }
                        
                    } 
                        else {
                        if (detail.getUserGroup().equals(faculty) && c.getCampDetails().getTotalSlots()>0 && c.getCampDetails().getCampCommitteeSlots()>0) {
                            if(!s.getRegisteredCamps().isEmpty()){
                            for(Camp ca : s.getRegisteredCamps()){
                                LocalDate campEndDate = c.getCampDetails().getEndDate();
                                LocalDate campStartDate = c.getCampDetails().getStartDate();
                                LocalDate campCloseDate = c.getCampDetails().getCloseDate();
                                LocalDate registeredEndDate = ca.getCampDetails().getEndDate();
                                LocalDate registeredStartDate = ca.getCampDetails().getStartDate();
                                if(!campStartDate.isBefore(registeredStartDate) && !campStartDate.isAfter(registeredEndDate)){
                                        
                                }
                                else if(!campEndDate.isBefore(registeredStartDate) && !campEndDate.isAfter(registeredEndDate)){

                                }
                                else if(LocalDate.now().isAfter(campCloseDate)){
                                    
                                }
                                else{
                                    System.out.println("Camp " + ++count);
                                    CampManager.printCampRegistrationForStudents(detail);
                                    list.add(c);
                                    System.out.println("=================================");
                                }
                            }
                        }
                        else{
                            System.out.println("Camp " + ++count);
                            CampManager.printCampRegistrationForStudents(detail);
                            list.add(c);
                            System.out.println("=================================");
                        }
                        }
                    }
                    

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


    // still can use this function to generate list of camps available, then put this list to sort
    public static void viewAvailableCamps() {
        ArrayList<Camp> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        
        //Default alphabetically sorted
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        listOfCamps = Sort.insertionSortByName(listOfCamps);
        CampRepository.setListOfCamps(listOfCamps); //So that subsequent sort is faster

        //Choosing filter method
        int filter = Filter.promptForFilter();
        switch(filter){
            case 1: //name
                String filterName = InputScanner.promptForString("What name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterName);
                break;
            case 2: //location
                String filterLocation = InputScanner.promptForString("What location do you want to filter by?: ");
                listOfCamps = Filter.filterByLocation(listOfCamps, filterLocation);
                break;
            case 3: //date
                LocalDate filterDate = InputScanner.promptForDate("What date do you want to filter by in format (dd/mm/yyyy)? : ", 1);
                listOfCamps = Filter.filterByDate(listOfCamps, filterDate);
                break;
        }
        System.out.println();
        if (listOfCamps.size() == 0){
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
    
    }


    ///////////////////////////////////////////////// from here onwards new code for filter
    // 
    // but still cannot work at getCampsFilteredByDate() cos of the sorting Comparable (maybe cos i changed from list to Arraylist)

    // can work
    // new viewAvailableCamps (to change name agn)
    public static void newviewAvailableCamps() {
        int filterType = promptForCampFilterType();
        printAvailableCamps(filterType);
    }

    // can work
    // same as the previous viewAvailableCamps() but removed all the print lines
    public static ArrayList<Object> getCampsAvailable() {
        ArrayList<Object> list = new ArrayList<>();
        Student s = (Student) LoginManager.getCurrentUser();
        String faculty = s.getFaculty();
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        for (Camp c : listOfCamps) {
            CampDetails detail = c.getCampDetails();
            if (!detail.getVisibility()) {
                continue;
            } else {
                if (detail.getUserGroup().equals("Everyone")) {
                    list.add(c);
                } else {
                    if (detail.getUserGroup().equals(faculty)) {
                        list.add(c);
                    }
                }
            }
        }
        return list;
    }

    // can work
    public static int promptForCampFilterType() {
        System.out.println("How do you want to view the camps?\n"
                        + "1. No filter\n"
                        + "2. View by filter\n\n");

        int filterType;
        do {
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        } while (filterType != 1 && filterType != 2);

        if (filterType == 1) {
            return 0;
        }

        System.out.println("How do you want to filter by?\n"
                        + "1. Camp Name\n"
                        + "2. Location\n"
                        + "3. After a certain date\n");

        do {
            filterType = InputScanner.promptForInt("Enter your option (1/2/3): ");
        } while (filterType != 1 && filterType != 2 && filterType != 3);

        return filterType;       
    }

    // can work
    public static void printAvailableCamps(int filterType) {

        ArrayList<Object> campsAvailable = getCampsAvailable();
        ArrayList<Object> campListToPrint;

        // no filter
        if (filterType == 0) {
            // display all camps in default alphabetical order
        }

        // filter by camp name
        else if (filterType == 1) {
            
        }

        // filter by location
        else if (filterType == 2) {

        }

        // filter by date
        else {
            // input date
            //campListToPrint = getCampsFilteredByDate(campsAvailable);
        }

        campListToPrint = getCampsFilteredByDate(campsAvailable);
        printCamps(campListToPrint);
    }

    // cannot work
    public static ArrayList<Object> getCampsFilteredByDate(ArrayList<Object> campList) {
        // the sorting of date is wrong currently
        ArrayList<Object> sortedCampList = Sort.insertionSort(new ArrayList<>(campList));

        // to remove camps which are over
        // this part is working
        // LocalDate now = LocalDate.now();
        // Iterator<Object> iterator = sortedCampList.iterator();
        // while (iterator.hasNext()) {
        //     Camp c = (Camp) iterator.next();
        //     LocalDate startDate = c.getCampDetails().getStartDate();
        //     System.out.println(startDate);
    
        //     if (startDate.compareTo(now) <= 0) {
        //         iterator.remove(); // Safely remove elements using iterator
        //     }
        // }
    
        return sortedCampList;
    }

    // can work
    public static void printCamps(ArrayList<Object> campListToPrint) {
        System.out.println("ff");
        if (campListToPrint.size() == 0) {
            System.out.println("There is currently no available camps.");
            return;
        }
        else {
            for (Object c : campListToPrint) {
                Camp camp = (Camp)c;
                CampDetails detail = camp.getCampDetails();
                CampManager.printCampDetailsForStudents(detail);
                System.out.println("=================================");
            }
        }
    }
}