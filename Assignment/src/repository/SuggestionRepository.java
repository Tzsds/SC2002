package repository;

import java.util.ArrayList;

import entity.Suggestion;
/**
 * Represent a repository for storing suggestions
 * This class provides methods to retrieve the list of suggestions and 
 * add a new suggestion to the repository
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class SuggestionRepository {
    /**The list of suggestions stored in the repository */
    private static ArrayList<Suggestion> listOfSuggestions = new ArrayList<Suggestion> ();
    /**
     * Gets the list of suggestions stored in the repository
     * 
     * @return the list of suggestions
     */
    public static ArrayList<Suggestion> getListOfSuggestions(){
        return listOfSuggestions;
    }
    /** 
     * Adds a new suggestion to the repository
     * 
     * @param s The suggestion to be added
     */
    public static void addSuggestionToRepo(Suggestion s){
        listOfSuggestions.add(s);
    }
}
