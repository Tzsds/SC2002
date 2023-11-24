package Repository;

import java.util.ArrayList;

import entity.Suggestion;

public class SuggestionRepository {
    private static ArrayList<Suggestion> listOfSuggestions = new ArrayList<Suggestion> ();

    public static ArrayList<Suggestion> getListOfSuggestions(){
        return listOfSuggestions;
    }

    public static void addSuggestionToRepo(Suggestion s){
        listOfSuggestions.add(s);
    }
}
