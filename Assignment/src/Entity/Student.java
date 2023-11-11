package Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    private ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
   
    public Student(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }

    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }

    public ArrayList<Camp> getRegisteredCamps(){
        return registeredCamps;
    }

    public void addRegisteredCamp(Camp camp) {
        registeredCamps.add(camp);
    }

    public static void viewAvailableCamps() {
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
                String check = row[10];
                if (check.equals("true")){
                    for (int i = 0; i < row.length; i++) {
                        System.out.print(padRight(row[i], columnWidths[i] + 2)); // Add 2 for extra spacing
                    }
                    System.out.println();
                }
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

}
