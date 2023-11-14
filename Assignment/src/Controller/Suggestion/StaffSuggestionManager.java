package Controller.Suggestion;

import java.util.ArrayList;

import Controller.File.FileWriting;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.Suggestion;
import Entity.Suggestion.Status;

public class StaffSuggestionManager {

    //Functions to be used by the Staff

    public static void printSuggestions(ArrayList<Camp> listOfCampsCreated){
        if (listOfCampsCreated.size() == 0){
            System.out.println("You have not created any camps yet.");
        }
        else{
            boolean empty = true;
            for (Camp camp : listOfCampsCreated){
                ArrayList<Suggestion> listOfSuggestions = camp.getListOfSuggestions();
                if (listOfSuggestions.size() != 0) {
                    empty = false;
                    for (Suggestion suggestion : listOfSuggestions) {
                        System.out.println("Camp: " + camp.getCampDetails().getCampName());
                        System.out.println("Proposer: " + suggestion.getProposer().getName());
                        System.out.println("Content: " + suggestion.getContent());
                        System.out.println("Status: " + suggestion.getStatus());
                        System.out.println("---------------------------------------------");
                    }
                }
            }
            if (empty){
                System.out.println("There is no existing suggestions.");
            }
        }

    }

    public static void rejectSuggestion(Suggestion temp){
        temp.setStatus(Status.REJECTED);
        FileWriting.FileWriteSuggestion();
    }

    public static void acceptSuggestion(Suggestion temp){
        temp.setStatus(Status.APPROVED);
        CampCommittee member = temp.getProposer();
        member.addPoints(); //Additional points for accepted Suggestion
        FileWriting.FileWriteSuggestion();
    }

}
