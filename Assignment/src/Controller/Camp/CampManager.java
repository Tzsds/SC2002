package Controller.Camp;

import java.util.ArrayList;

import Entity.Camp;
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
}
