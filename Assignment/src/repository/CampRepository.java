package repository;

import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Student;

/**
 * Represent a repository for storing and managing camps
 * This class provides methods to retrieve, add, remove and query camps stored in the repository
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampRepository {
    /** The list of camps stored in the repository */
    private static ArrayList<Camp> listOfCamps = new ArrayList<Camp>();
    
    /**
     * Gets the lsit of camps stored in the repository
     * 
     * @return The list of camps
     */
    public static ArrayList<Camp> getListOfCamps() {
        return listOfCamps;
    }

    /**
     * Sets the list of camps in the repository to a new list
     * 
     * @param newListOfCamps - the new list of camp
     */
    public static void setListOfCamps(ArrayList<Camp> newListOfCamps){
        listOfCamps = newListOfCamps;
    }

    /**
     * Gets the number of camps in the repository
     * 
     * @return The number of camps
     */
    public static int getSizeOfCamps() {
        return listOfCamps.size();
    }

    /**
     * Gets the camp at a specified index in the repository
     * 
     * @param i The index of the camp
     * @return The camp at the specified index
     */
    public static Camp get(int i) {
        return listOfCamps.get(i);
    }

    /**
     * Adds a new camp to the repository
     * 
     * @param camp The camp to be added
     */
    public static void addCampToRepo(Camp camp) {
        listOfCamps.add(camp);
    }

    /**
     * Removes a camp from the repository
     * 
     * @param camp The camp to be removed
     */
    public static void removeCamp(Camp camp) {
        listOfCamps.remove(camp);
    }

    /** Gets a camp by its name
     * 
     * @param campName The name of the camp
     * @return The camp with the specified name, or null if not found
     */
    public static Camp getCampByCampName(String campName) {
        for (int j = 0; j < getSizeOfCamps(); j++) {
            Camp tempCamp = get(j);
            CampDetails tempCampDetails = tempCamp.getCampDetails();
            String tempCampName = tempCampDetails.getCampName();
            if (campName.equals(tempCampName)) {
                return tempCamp;
            }
        }
        return null;
    }

    /**
     * Gets a list of available camps for a specific student
     * 
     * @param student The student for whom available camps are retrieved
     * @return The list of available camps for the specified student
     */
    public static ArrayList<Camp> getAvailableCampsForStudent(Student student) {
        ArrayList<Camp> availableCamps = new ArrayList<>();

        for (Camp camp : listOfCamps) {
            CampDetails campDetails = camp.getCampDetails();

            if (!campDetails.getVisibility()) {
                continue;
            } else {
                if (campDetails.getUserGroup().equals("Everyone")
                        || (campDetails.getUserGroup().equals(student.getFaculty())
                                && (campDetails.getTotalSlots() > 0 || campDetails.getCampCommitteeSlots() > 0))) {
                    availableCamps.add(camp);
                }
            }
        }

        return availableCamps;
    }
}