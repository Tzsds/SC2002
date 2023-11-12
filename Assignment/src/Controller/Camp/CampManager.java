package Controller.Camp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Camp;
import Entity.CampDetails;
import Entity.Suggestion;

public class CampManager {
    public static void addSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.add(tempSuggestion);
    }
    public static void removeSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.remove(tempSuggestion);
    }

    public static void printCampsForStudent(ArrayList<Camp> campList) {
        if (campList.size() != 0) {
            for (Camp camp : campList) {
                CampDetails campDetails = camp.getCampDetails();
                System.out.println("Camp: " + campDetails.getCampName());
                System.out.println("Camp Description: " + campDetails.getDescription());
                System.out.println("Start Date: " + campDetails.getStartDate());
                System.out.println("End Date: " + campDetails.getEndDate());
                System.out.println("Registration Close Date: " + campDetails.getCloseDate());
                System.out.println("Open To: " + campDetails.getUserGroup());
                System.out.println("Location: " + campDetails.getLocation());
                System.out.println("Total Slots: " + campDetails.getTotalSlots());
                System.out.println("Total Camp Committee Slots: " + campDetails.getCampComitteeSlots());
                System.out.println("=====================================");
            }
        }
    }

    public static void printCampsForStaff(ArrayList<Camp> campList) {
        if (campList.size() != 0) {
            for (Camp camp : campList) {
                CampDetails campDetails = camp.getCampDetails();
                System.out.println("Camp: " + campDetails.getCampName());
                System.out.println("Camp Description: " + campDetails.getDescription());
                System.out.println("Start Date: " + campDetails.getStartDate());
                System.out.println("End Date: " + campDetails.getEndDate());
                System.out.println("Registration Close Date: " + campDetails.getCloseDate());
                System.out.println("Open To: " + campDetails.getUserGroup());
                System.out.println("Location: " + campDetails.getLocation());
                System.out.println("Total Slots: " + campDetails.getTotalSlots());
                System.out.println("Total Camp Committee Slots: " + campDetails.getCampComitteeSlots());
                System.out.println("Visibility: " + campDetails.getVisibility());
                System.out.println("=====================================");
            }
        }
    }

    /* public static void viewAvailableCamps() {
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
                //boolean check = row[10].equalsIgnoreCase("true") ? true : false;
                String visibilityCheck = row[10];
                String userGroupCheck = row[4];
                if (visibilityCheck.equals("true"))
                    if(userGroupCheck.equals("Everyone")  || userGroupCheck.equals(getFaculty)){
                    System.out.println("===========================================");
                    //for (int i = 0; i < row.length; i++) {
                        System.out.println("Camp Name: " + row[0]);
                        System.out.println("Start Date: " + row[1]);
                        System.out.println("End Date: " + row[2]);
                        System.out.println("Registration Closing Date: " + row[3]);
                        System.out.println("Group: " + row[4]);
                        System.out.println("Location: " + row[5]);
                        System.out.println("Slots: " + row[6]);
                        System.out.println("Camp Committee Slots: " + row[7]);
                        System.out.println("Description: " + row[8]);
                        System.out.println("Staff In Charge: " + row[9]);
                        System.out.println("===========================================");
                        //System.out.print(padRight(row[i], columnWidths[i] + 2)); // Add 2 for extra spacing
                   // }
                    //System.out.println();
                    
                }
            }

            
        }

        catch (IOException e){
            e.printStackTrace();
        }
    } */
}
