package repository.userrepository;

import java.util.ArrayList;

import entity.Staff;

/**
 * Represents a repository for storing and managing staff records
 * This class provides methods to retrieve, add and query staff information stored in the repository
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StaffRepository {

    /** The list of staff members stored in the repository */
    private static ArrayList<Staff> listOfStaff = new ArrayList<Staff>();
    
    /**
     * Gets the list of staff members stored in the repository
     * 
     * @return The list of staff members
     */
    public static ArrayList<Staff> getListOfStaff(){
        return listOfStaff;
    }

    /**
     * Adds a new staff member to the repository
     * 
     * @param s The staff member to be added
     */
    public static void addStaff(Staff s){
        listOfStaff.add(s);
    }

    /**
     * Gets a staff member by their ID
     * 
     * @param id The ID of the staff member
     * @return The staff member with the specified ID, or null if not found
     */
    public static Staff getStaffByID(String id){
        for (Staff s : listOfStaff){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
