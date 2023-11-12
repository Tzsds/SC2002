package Entity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileRemove;
import Controller.File.FileWriting;
import Controller.Suggestion.StaffSuggestionManager;
import Controller.Suggestion.SuggestionManager;
import UI.InputScanner;

public class Staff extends User {
    private ArrayList<Camp> campsCreatedList = new ArrayList<>();

    public Staff(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }
    
    //not yet done
    public static void viewAllCamps() {
        String campDetailsCSV = "Assignment//database//camp_details.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(campDetailsCSV))){
            int numRows = 0;
            int numCols = 0;
            String line;
            while ((line = br.readLine()) != null) {
                numRows++;
                String[] row = line.split(",");
                if (numCols < row.length) {
                    numCols = row.length;
                }
            }

            // Create a 2D array to store the CSV data
            String[][] data = new String[numRows][numCols];

            // Reset the reader
            br.close();
            BufferedReader br1 = new BufferedReader(new FileReader(campDetailsCSV));
            //br = new BufferedReader(new FileReader(campDetailsCSV));

            int rowIdx = 0;
            while ((line = br1.readLine()) != null) {
                String[] row = line.split(",");
                for (int colIdx = 0; colIdx < row.length; colIdx++) {
                    data[rowIdx][colIdx] = row[colIdx];
                }
                rowIdx++;
            }

            //rename headers
            //campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility

            data[0][0] = "Camp Name";
            data[0][1] = "Duration";
            data[0][2] = "Registration Date";
            data[0][3] = "Staff";
            data[0][4] = "Total Slots";
            data[0][5] = "Attendee Slots";
            data[0][6] = "Committee Slots";
            data[0][7] = "Attendee Slots Left";
            data[0][8] = "Committee Slots Left";
            
            // Determine the maximum width for each column
            int[] columnWidths = new int[data[0].length];
            for (int i = 0; i < data[0].length; i++) {
                for (int j = 0; j < data.length; j++) {
                    int length = data[j][i].length();
                    if (length > columnWidths[i]) {
                        columnWidths[i] = length;
                    }
                }
            }

            // Print the table
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    System.out.print(padRight(row[i], columnWidths[i] + 2)); // Add 2 for extra spacing
                }
                System.out.println();
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

    //public static Camp createNewCamp() {
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

        System.out.println("Enter location of the camp: ");
        String location = sc2.nextLine();

        System.out.print("Enter the total number of slots open for the camp: ");
        int totalSlots = sc.nextInt();
        
        System.out.print("Enter the number of camp committee slots open for the camp: ");
        int campCommitteeSlots = sc.nextInt();    

        String staffInCharge = LoginManager.getCurrentUser().getName();
        System.out.print("Visibility of camp to the targetted students? (Enter \"1\" for On, or \"0\" for Off): ");
        int visibility = sc.nextInt();
        
        boolean bool = false;
        if (visibility == 1) {
            bool = true;
        }

        //CampDetails newCampDetails = new CampDetails(campName, description, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, staffInCharge, visibility);

        CampDetails newCampDetails = new CampDetails();
        newCampDetails.setCampName(campName);
        newCampDetails.setDescription(description);
        newCampDetails.setStartDate(startDate);
        newCampDetails.setEndDate(endDate);
        newCampDetails.setCloseDate(registrationClosingDate);
        newCampDetails.setUserGroup(userGroup);
        newCampDetails.setLocation(location);
        newCampDetails.setTotalSlots(totalSlots);
        newCampDetails.setCampComitteeSlots(campCommitteeSlots);
        newCampDetails.setStaffInCharge(staffInCharge);     //String or Staff
        newCampDetails.setVisibility(bool);

        Staff currentStaff = (Staff)LoginManager.getCurrentUser();
    
        Camp newCamp = new Camp();
        newCamp.setCampDetails(newCampDetails);
        currentStaff.campsCreatedList.add(newCamp);

        FileWriting fw = new FileWriting();
        fw.FileWrite(newCampDetails);
        fw.FileWrite(newCamp);

        System.out.println("Camp Created!");
    }

    public static void deleteCamp(String campName){
        String campDetailsCSV = "Assignment//database//camp_details.csv";
        String staffInCharge = LoginManager.getCurrentUser().getName();
       List<String[]> rows = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(campDetailsCSV))){
            String line;
            while ((line = br.readLine()) != null){
                String [] colums = line.split(",");
                if(!colums[0].equals(campName)){
                    rows.add(colums);
                }
                else if(!line.contains(staffInCharge)){
                    System.out.println("You did not create the camp");
                    rows.add(colums);
                }
                else
                    System.out.println("Camp deleted successfully");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(campDetailsCSV))){
            for (String [] row : rows) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i<row.length; i++){
                    line.append(row[i]);
                    if(i<row.length-1){
                        line.append(",");
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    
    // View list of camp created by the staff
    public void viewCampCreatedList() {
        if (campsCreatedList.size() == 0) {
            System.out.println("You have not created any camps yet!");
        }
        else {
            System.out.println("Camps created:");
            System.out.println("====================================");
            CampManager.printCampsForStaff(campsCreatedList);
        }
    }

    // View suggestions given by camp committee members
    public void viewSuggestions() {
        // if (campsCreatedList.size() == 0) {
        //     System.out.println("You have not created any camps yet!");
        // }
        // else {
        //     for (Camp camp : campsCreatedList) {
        //         ArrayList<Suggestion> listOfSuggestions = camp.getListOfSuggestions();
        //         if (listOfSuggestions.size() != 0) {
        //             for (Suggestion suggestion : listOfSuggestions) {
        //                 System.out.println("Camp: " + camp.getCampDetails().getCampName());
        //                 System.out.println("Proposer: " + suggestion.getProposer().getName());
        //                 System.out.println("Content: " + suggestion.getContent());
        //                 System.out.println("Status: " + suggestion.getStatus());
        //                 System.out.println("---------------------------------------------");
        //             }
        //         }
        //     }
        // }
        StaffSuggestionManager.printSuggestions(campsCreatedList);
    }
}
