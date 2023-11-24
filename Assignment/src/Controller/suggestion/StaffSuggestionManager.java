package Controller.suggestion;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.File.FileWriting;
import Controller.File.user.WriteUser;
import Controller.utils.InputScanner;
import entity.Camp;
import entity.CampCommittee;
import entity.Suggestion;
import entity.Suggestion.Status;

public class StaffSuggestionManager {

    //Functions to be used by the Staff

    public static void printSuggestions(ArrayList<Camp> listOfCampsCreated){
        if (listOfCampsCreated.size() == 0){
            System.out.println("You have not created any camp yet.");
        }
        else{
            
            System.out.println("========================================");
            boolean empty = true;
            for (Camp camp : listOfCampsCreated){
                ArrayList<Suggestion> listOfSuggestions = camp.getListOfSuggestions();
                if (listOfSuggestions.size() != 0) {
                    empty = false;
                    for (Suggestion suggestion : listOfSuggestions) {
                        System.out.println("Camp: " + camp.getCampDetails().getCampName());
                        System.out.println("Proposer: " + suggestion.getProposer().getName());
                        System.out.println("Content: " + suggestion.getContent());
                        System.out.println("========================================");
                    }
                }
            }
            if (empty){
                System.out.println("There is no existing suggestions.");
            }
        }
    }

    public static void processSuggestions(ArrayList<Camp> listOfCampsCreated){
        if (listOfCampsCreated.size() == 0){
            System.out.println("You have not created any camp yet.");
        }
        else{
            ArrayList<Suggestion> pendingSuggestions = new ArrayList<>();
            for (Camp c : listOfCampsCreated){
                ArrayList<Suggestion> listOfSuggestion = c.getListOfSuggestions();
                for (Suggestion s : listOfSuggestion){
                    if (s.getStatus() == Status.PENDING){
                        pendingSuggestions.add(s);
                    }
                }
            }
            if (pendingSuggestions.size() == 0){
                System.out.println("There is no existing suggestions");
            }
            else{
                System.out.println("=================================");
                int choice = 0;
                while (true){
                    int count = 1;
                    for (Suggestion s : pendingSuggestions){
                        Camp camp = s.getProposer().getCommitteeOf();
                        System.out.println("Suggestion " + count);
                        System.out.println("Camp: " + camp.getCampDetails().getCampName());
                        System.out.println("Content: " + s.getContent());
                        System.out.println("=================================");
                        count += 1;
                    }
                    choice = InputScanner.promptForInt("Which suggestion do you want to process?: ");
                    
                    
                    while (choice <= 0 || choice > pendingSuggestions.size()){
                        System.out.println("Wrong value entered. Please try again");
                        choice = InputScanner.promptForInt("Which suggestion do you want to process?: ");
                    }

                    break;
                }
                int reject = 0;
                while (true){
                    try{
                        reject = InputScanner.promptForInt("Enter 0 to accept or 1 to reject suggestion: ");
                    }
                    catch(InputMismatchException e){
                        System.out.println("Wrong input entered. Please try again");
                        InputScanner.clear();
                        continue;
                    }
                    if (reject != 0 && reject != 1){
                        System.out.println("Wrong value entered. Please try again");
                        continue;
                    }
                    Suggestion s = pendingSuggestions.get(choice-1);
                    if (reject == 1){
                        StaffSuggestionManager.rejectSuggestion(s);
                    }
                    else{
                        StaffSuggestionManager.acceptSuggestion(s);
                    }
                    return;
                }
            }
        }   
    }

    public static void rejectSuggestion(Suggestion temp){
        temp.setStatus(Status.REJECTED);
        FileWriting.FileWriteSuggestion();
        removeSuggestionFromCamp(temp);
        System.out.println("Suggestion rejected!");
    }

    public static void acceptSuggestion(Suggestion temp){
        temp.setStatus(Status.APPROVED);
        CampCommittee member = temp.getProposer();
        member.addPoints(); //Additional points for accepted Suggestion
        FileWriting.FileWriteSuggestion();
        WriteUser.FileWriteCampCommittee(); //Reflect the additional points given in CSV
        removeSuggestionFromCamp(temp);
        System.out.println("Suggestion accepted!");
    }

    private static void removeSuggestionFromCamp(Suggestion temp){
        Camp camp = temp.getProposer().getCommitteeOf();
        camp.getListOfSuggestions().remove(temp);
    }

}
