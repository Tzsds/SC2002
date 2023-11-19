package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.File.FileWriting;
import Controller.Report.CampReport;
import Controller.Report.EnquiryReport;
import Controller.Report.PerformanceReport;
import Controller.Report.ReportManager;
import Repository.CampRepository;
import UI.InputScanner;

public class Staff extends User {
    private ArrayList<Camp> campsCreatedList = new ArrayList<>();

    public Staff(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }

    public ArrayList<Camp> getListOfCampsCreated(){
        return campsCreatedList;
    }

    public void addCampToList(Camp camp) {
        campsCreatedList.add(camp);
    }

    public void createNewCamp() {
        String campName = InputScanner.promptForString("Enter new camp name: ");
        
        String description = InputScanner.promptForString("Write in your camp description: ");
        
        LocalDate startDate = InputScanner.promptForDate("Enter camp start date in format (dd/mm/yyyy): ", 0);

        LocalDate endDate = InputScanner.promptForDate("Enter camp end date in format (dd/mm/yyyy): ", 0);

        LocalDate registrationClosingDate = InputScanner.promptForDate("Enter camp registration closing date in format (dd/mm/yyyy): ", 0);

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
        
        int campCommitteeSlots = InputScanner.promptForInt("Enter the number of camp committee slots open for the camp: ");   
        
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
    
        Camp newCamp = new Camp();
        newCamp.setCampDetails(newCampDetails);
        campsCreatedList.add(newCamp);
        CampRepository.addCampToRepo(newCamp); //NEED TO ADD TO REPOSITORY

        FileWriting.FileWriteCampDetails(); //update csv
        System.out.println("Camp Created!");
    }

    public void generateCampReport() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        } else {
            Camp campChosen = ReportManager.promptWhichCampForStaff();
            int reportType = ReportManager.promptCampReportType();
            CampReport report = new CampReport(campChosen, reportType);
            report.generate();
            report.printInTerminal();
        }
    }

    public void generateEnquiryReport() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        } else {
            Camp campChosen = ReportManager.promptWhichCampForStaff();
            EnquiryReport report = new EnquiryReport(campChosen);
            report.generate();
            report.printInTerminal();
        }
    }

    public void generatePerformanceReport() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        } else {
            Camp campChosen = ReportManager.promptWhichCampForStaff();
            PerformanceReport report = new PerformanceReport(campChosen);
            report.generate();
            report.printInTerminal();
        }
    }

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
            try{
                choice = InputScanner.promptForInt("Which camp do you want to delete?: ");
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again.");
                InputScanner.clear();
                continue;
            }
            if (choice <= 0 || choice > size){
                System.out.println("Invalid input! Please try again.");
                continue;
            }
            Camp temp = campsCreatedList.get(choice-1);
            //Check if there are any Students or Camp Committee registered
            size = temp.getCampCommittee().size();
            if (size < 0){
                break;
            }
            size = temp.getParticipants().size();
            if (size < 0){
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
