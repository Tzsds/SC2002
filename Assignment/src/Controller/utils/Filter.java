package Controller.utils;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Camp;
import entity.Enquiry;
import entity.Student;
import repository.EnquiryRepository;

public class Filter {
    public static int promptForFilter(){
        System.out.println("How do you want to view the camps?\n"
                        + "1. No filter\n"
                        + "2. View by filter");

        int filterType;
        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
    
        if (filterType == 1) {
            return 0;
        }
        System.out.println("\nHow do you want to filter by?\n"
                        + "1. Camp Name\n"
                        + "2. Location\n"
                        + "3. After a certain date");

        filterType = InputScanner.promptForInt("Enter your option (1/2/3): ");
        while (filterType != 1 && filterType != 2 && filterType != 3){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2/3): ");
        }

        return filterType;
    }

    
    public static ArrayList<Camp> filterByLocation(ArrayList<Camp> listOfCamps, String filterLocation){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            String location = c.getCampDetails().getLocation().toLowerCase();
            if (location.equals(filterLocation.toLowerCase())){
                camps.add(c);
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByCampName(ArrayList<Camp> listOfCamps, String filterName){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            String campName = c.getCampDetails().getCampName().toLowerCase();
            if (campName.contains(filterName.toLowerCase())){
                camps.add(c);
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByDate(ArrayList<Camp> listOfCamps, LocalDate filterDate){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            LocalDate campDate = c.getCampDetails().getStartDate();
            if (campDate.isAfter(filterDate)){
                camps.add(c);
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByStudent(ArrayList<Camp> listOfCamps, String name){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            ArrayList<Student> committeeList = c.getCampCommittee();
            ArrayList<Student> participantList = c.getParticipants();

            int i=0;
            for (Student committee :committeeList) {
                String studentName = committee.getName();
                if (studentName.contains(name)) {
                    camps.add(c);
                    i=1;
                    break;
                }
            }

            if (i!=0) {continue;};
            for (Student participant :participantList) {
                String studentName = participant.getName();
                if (studentName.contains(name)) {
                    camps.add(c);
                    break;
                }
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByCommitteeMember(ArrayList<Camp> listOfCamps, String name){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            ArrayList<Student> committeeList = c.getCampCommittee();
            for (Student committee :committeeList) {
                String studentName = committee.getName();
                if (studentName.contains(name)) {
                    camps.add(c);
                    break;
                }
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByEnquiryReplier(ArrayList<Camp> listOfCamps, String name){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            ArrayList<Enquiry> enquiries = EnquiryRepository.getEnquiriesByCamp(c);
            if (enquiries.size() == 0) {
                continue;
            }

            for (Enquiry e : enquiries) {
                String replier = e.getReplier();
                if (replier.contains(name)) {
                    camps.add(c);
                    break;
                }
            }
        }
        return camps;
    }
}
