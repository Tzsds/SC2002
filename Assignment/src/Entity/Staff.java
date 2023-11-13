package Entity;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import Controller.File.FileWriting;
import Repository.CampRepository;

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

    public static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

    public void createNewCamp() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter new camp name: ");
        String campName = sc.nextLine();
        
        System.out.print("Write in your camp description: ");
        String description = sc.nextLine();

        System.out.print("Enter camp start date in format (dd/mm/yyyy): ");
        String dateString = sc.nextLine();
        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate startDate = LocalDate.of(year, month, day);

        System.out.print("Enter camp end date in format (dd/mm/yyyy): ");
        dateString = sc.nextLine();
        parts = dateString.split("/");
        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
        LocalDate endDate = LocalDate.of(year, month, day);

        System.out.print("Enter camp registration closing date in format (dd/mm/yyyy): ");
        dateString = sc.nextLine();
        parts = dateString.split("/");
        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
        LocalDate registrationClosingDate = LocalDate.of(year, month, day);

        System.out.print("Which user group is this camp open to? (Enter \"1\" for Everyone or \"0\" for " + getFaculty() + "): ");
        int a = sc.nextInt();
        String userGroup;
        if(a == 1)
            userGroup = "Everyone";
        else
            userGroup = getFaculty();

        System.out.print("Enter location of the camp: ");
        String location = sc2.nextLine();

        System.out.print("Enter the total number of slots open for the camp: ");
        int totalSlots = sc.nextInt();
        
        System.out.print("Enter the number of camp committee slots open for the camp: ");
        int campCommitteeSlots = sc.nextInt();    

        System.out.print("Visibility of camp to the targetted students? (Enter \"1\" for On, or \"0\" for Off): ");
        int visibility = sc.nextInt();
        
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


        FileWriting.FileWriteCamp(); //update csv
        System.out.println("Camp Created!");
    }

    public void deleteCamp(){
        Scanner sc = new Scanner(System.in);
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
                System.out.print("Which camp do you want to delete?: ");
                choice = sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again.");
                sc.nextLine();
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

            FileWriting.FileWriteCamp(); //update csv
            System.out.println("Camp successfully removed");
            return;
        }
        System.out.println("Cannot remove camp as there are already registered participants");
    }

}
