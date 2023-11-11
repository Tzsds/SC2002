package Controller.Suggestion;

import java.util.ArrayList;

import Controller.File.FileWriting;
import Entity.CampCommittee;
import Entity.Suggestion;
import Entity.Suggestion.Status;

public class StaffSuggestionManager {

    //Functions to be used by the Staff

    public static void printSuggestions(ArrayList<Suggestion> suggestions){
        int size = suggestions.size();
        if (size == 0){
            System.out.println("There is no existing suggestions.");
        }
        else{
            for(int i=0; i<size; i++){
                Suggestion temp = suggestions.get(i);
                System.out.println(i + ". " + temp.getContent()); 
            }
        }
    }

    public static void rejectSuggestion(Suggestion temp){
        temp.setStatus(Status.REJECTED);
        SuggestionManager.deleteSuggestion(temp);
        FileWriting.FileWriteSuggestion();
    }

    public static void acceptSuggestion(Suggestion temp){
        temp.setStatus(Status.APPROVED);
        CampCommittee member = temp.getProposer();
        member.addPoints(); //Additional points for accepted Suggestion
        SuggestionManager.deleteSuggestion(temp);
        FileWriting.FileWriteSuggestion();
    }

}
