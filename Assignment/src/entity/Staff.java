package entity;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.file.FileWriting;
import controller.report.EnquiryReport;
import controller.report.PerformanceReport;
import controller.report.ReportManager;
import controller.utils.InputScanner;
import repository.CampRepository;

/**
 * This class represents a staff member in the system, inheriting basic user attributes
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class Staff extends User {
    /** The list of camps created by the staff member */
    private ArrayList<Camp> campsCreatedList = new ArrayList<>();

    /**
     * Constructs a new Staff object with the specified user ID, name, faculty, and password
     *
     * @param userID - the user ID of the staff member
     * @param name - the name of the staff member
     * @param faculty - the faculty of the staff member
     * @param password - the password of the staff member
     */
    public Staff(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }

    /**
     * Retrieves the list of camps created by the staff member
     * @return the list of camps created
     */
    public ArrayList<Camp> getListOfCampsCreated(){
        return campsCreatedList;
    }

     /**
     * Adds a camp to the list of camps created by the staff member
     * @param camp - the camp to add to the list
     */
    public void addCampToList(Camp camp) {
        campsCreatedList.add(camp);
    }

    /**
     * Creates a new camp by prompting the staff member for various details
     * The new camp is added to the list of camps created by the staff member
     */
    public void createNewCamp() {
        String campName = InputScanner.promptForString("Enter new camp name: ");
        
        String description = InputScanner.promptForString("Write in your camp description: ");
        
        LocalDate startDate = InputScanner.promptForStartDate("Enter camp start date in format (dd/mm/yyyy): ");

        LocalDate endDate = InputScanner.promptForEndDate("Enter camp end date in format (dd/mm/yyyy): ", startDate);

        LocalDate registrationClosingDate = InputScanner.promptForCloseDate("Enter camp registration closing date in format (dd/mm/yyyy): ", startDate);

        String userGroup;
        while (true){
            int a = InputScanner.promptForInt("Which user group is this camp open to? (Enter \"1\" for Everyone or \"0\" for " + getFaculty() + "): ");
            if (a <0 || a > 1)
                System.out.println("Invalid input. Please try again");
            else{
                if(a == 1){
                    userGroup = "Everyone";
                    break;
                }
                else{
                    userGroup = getFaculty();
                    break ;
                }
            }
        }
        
        String location = InputScanner.promptForString("Enter location of the camp: ");

        int totalSlots = InputScanner.promptForInt("Enter the total number of slots open for the camp: ");
        while (totalSlots < 0){
            System.out.println("Total slots of a camp cannot be a negative integer. Please try again.");
            totalSlots = InputScanner.promptForInt("Enter the total number of slots open for the camp: ");
        }
        
        int campCommitteeSlots;
        while (true){
            campCommitteeSlots = InputScanner.promptForInt("Enter the number of camp committee slots open for the camp: ");
            if (campCommitteeSlots < 0){
                System.out.println("Camp Committee slots cannot be a negative integer. Please try again");
            }
            else if (campCommitteeSlots > 10){
                System.out.println("Camp Committee slots is limited to 10 slots only. Please try again");
            }
            else if (campCommitteeSlots > totalSlots){
                System.out.println("Camp Committee slots cannot be more than total slots of camp. Please try again");
            }
            else{
                break;
            }
        }   
        
        boolean bool;
        while(true){
            int visibility = InputScanner.promptForInt("Visibility of camp to the targetted students? (Enter \"1\" for On, or \"0\" for Off): ");
            if (visibility <0 || visibility > 1)
                System.out.println("Invalid input. Please try again");
            else{
                if(visibility == 1){
                    bool = true;
                    break;
                }
                else{
                    bool = false;
                    break ;
                }
            }
        }   

        String id = getUserID();

        CampDetails newCampDetails = new CampDetails(campName, startDate, endDate, 
                                    registrationClosingDate, userGroup, location, 
                                    totalSlots, campCommitteeSlots, description, 
                                    id, bool);
    
        Camp newCamp = new Camp(newCampDetails);
        campsCreatedList.add(newCamp);
        CampRepository.addCampToRepo(newCamp); //NEED TO ADD TO REPOSITORY

        FileWriting.FileWriteCampDetails(); //update csv
        System.out.println("Camp Created!");
    }

    /**
     * Generates an enquiry report for the staff member's chosen camp
     * If the staff member has not created any camps, a message is displayed
     */
    public void generateEnquiryReport() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        } else {
            Camp campChosen = ReportManager.promptWhichCampForStaff();
            EnquiryReport report = new EnquiryReport(campChosen);
            report.generate();
        }
    }

    /**
     * Generates a performance report for the staff member's chosen camp
     * If the staff member has not created any camps, a message is displayed
     */
    public void generatePerformanceReport() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        } else {
            Camp campChosen = ReportManager.promptWhichCampForStaff();
            PerformanceReport report = new PerformanceReport(campChosen);
            report.generate();
        }
    }

    /**
     * Deletes a camp from the list of camps created by the staff member
     * If the staff member has not created any camps, a message is displayed
     * The staff member is prompted to choose a camp to delete, and the camp is removed
     * from the list and the repository if there are no registered participants
     */
    public void deleteCamp(){
        int size = campsCreatedList.size();
        int choice = 0;
        if (size == 0){
            System.out.println("You have not created any camp yet!");
            return;
        }
        for (int i = 0; i<size; i++){
            String campName = campsCreatedList.get(i).getCampDetails().getCampName();
            System.out.println(i+1 + ". " + campName);
        }
        while (true){
            choice = InputScanner.promptForInt("Which camp do you want to delete (Enter " + (size+1) + " to exit)?: ");
            
            if (choice <= 0 || choice > size + 1){
                System.out.println("Invalid number! Please try again.");
                continue;
            }
            if (choice == (size + 1)){
                System.out.println("Back to main menu...");
                return;
            }
            Camp temp = campsCreatedList.get(choice-1);
            //Check if there are any Students or Camp Committee registered
            size = temp.getCampCommittee().size();
            if (size > 0){
                break;
            }
            size = temp.getParticipants().size();
            if (size > 0){
                break;
            }
            CampRepository.removeCamp(temp);
            campsCreatedList.remove(temp);

            FileWriting.FileWriteCampDetails(); //update csv
            System.out.println("Camp successfully removed");
            return;
        }
        System.out.println("Cannot remove camp as there are already registered participants");
    }

}
