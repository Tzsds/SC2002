package Controller.Utilities;

import java.util.ArrayList;

import Entity.Camp;
import UI.InputScanner;

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
            String location = c.getCampDetails().getLocation();
            //System.out.println(location + ", " + filterLocation);
            if (location.equals(filterLocation)){
                camps.add(c);
            }
        }
        return camps;
    }

    public static ArrayList<Camp> filterByCampName(ArrayList<Camp> listOfCamps, String filterName){
        ArrayList <Camp> camps = new ArrayList<>();
        for (Camp c : listOfCamps){
            String campName = c.getCampDetails().getCampName();
            if (campName.contains(filterName)){
                camps.add(c);
            }
        }
        return camps;
    }


}
