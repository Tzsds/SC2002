package repository.userrepository;

import java.util.ArrayList;

import entity.CampCommittee;

/**
 * Represents a repository for storing and managing camp committee records
 * This class provides methods to retrieve, add, and query camp committee information stored in the repository
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampCommitteeRepository {

    /** The list of camp committee members stored in the repository */
    private static ArrayList<CampCommittee> listOfCampCommittee = new ArrayList<CampCommittee>();

    /**
     * Gets the list of camp committee members stored in the repository
     * 
     * @return The list of camp committee members
     */
    public static ArrayList<CampCommittee> getListOfCampCommittee(){
        return listOfCampCommittee;
    }

    /**
     * Adds a new camp committee member to the repository
     * 
     * @param s The camp committee member to be added
     */
    public static void addCampCommittee(CampCommittee s){
        listOfCampCommittee.add(s);
    }

    /**
     * Gets a camp committee member by their ID
     * 
     * @param id The ID of the camp committee member
     * @return The camp committee member with the specified ID, or null if not found
     */
    public static CampCommittee getCommitteeByID(String id){
        for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
