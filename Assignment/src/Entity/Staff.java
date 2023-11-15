package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.File.FileWriting;
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

        String dateString = InputScanner.promptForString("Enter camp start date in format (dd/mm/yyyy): ");
        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate startDate = LocalDate.of(year, month, day);

        dateString = InputScanner.promptForString("Enter camp end date in format (dd/mm/yyyy): ");
        parts = dateString.split("/");
        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
        LocalDate endDate = LocalDate.of(year, month, day);

        dateString = InputScanner.promptForString("Enter camp registration closing date in format (dd/mm/yyyy): ");
        parts = dateString.split("/");
        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
        LocalDate registrationClosingDate = LocalDate.of(year, month, day);

        int a = InputScanner.promptForInt("Which user group is this camp open to? (Enter \"1\" for Everyone or \"0\" for " + getFaculty() + "): ");
        String userGroup;
        if(a == 1)
            userGroup = "Everyone";
        else
            userGroup = getFaculty();

        String location = InputScanner.promptForString("Enter location of the camp: ");

        int totalSlots = InputScanner.promptForInt("Enter the total number of slots open for the camp: ");
        
        int campCommitteeSlots = InputScanner.promptForInt("Enter the number of camp committee slots open for the camp: ");   

        int visibility = InputScanner.promptForInt("Visibility of camp to the targetted students? (Enter \"1\" for On, or \"0\" for Off): ");
        
        boolean bool = false;
        if (visibility == 1) {
            bool = true;
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
