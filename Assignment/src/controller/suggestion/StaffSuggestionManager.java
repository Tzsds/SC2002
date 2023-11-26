package controller.suggestion;

import java.util.ArrayList;
import java.util.InputMismatchException;

import controller.file.FileWriting;
import controller.file.user.WriteUser;
import controller.utils.InputScanner;
import entity.Camp;
import entity.CampCommittee;
import entity.Suggestion;
import entity.Suggestion.SuggestionStatus;

/**
 * This class provides functionalities specifically for staffs to manage suggestion
 * related to the camps they have created. Staff can print, process, accept and reject
 * suggestions, updating the system accordingly
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class StaffSuggestionManager {
    /**
     * Prints the suggestion associated with the list of camps created by the staff
     * @param listOfCampsCreated - List of camps created by the staff
     */

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

    /**
     * Processes pending suggestions, allowing staff members to accept or reject them
     * @param listOfCampsCreated - List of camps created by the staff
     */

    public static void processSuggestions(ArrayList<Camp> listOfCampsCreated){
        if (listOfCampsCreated.size() == 0){
            System.out.println("You have not created any camp yet.");
        }
        else{
            ArrayList<Suggestion> pendingSuggestions = new ArrayList<>();
            for (Camp c : listOfCampsCreated){
                ArrayList<Suggestion> listOfSuggestion = c.getListOfSuggestions();
                for (Suggestion s : listOfSuggestion){
                    if (s.getStatus() == SuggestionStatus.PENDING){
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

    /**
     * Rejects a specific suggestion, updating its status and reflecting changes in the system
     * @param temp - The suggestion to be rejected
     */

    public static void rejectSuggestion(Suggestion temp){
        temp.setStatus(SuggestionStatus.REJECTED);
        FileWriting.FileWriteSuggestion();
        removeSuggestionFromCamp(temp);
        System.out.println("Suggestion rejected!");
    }

    /**
     * Accepts a specific suggestion, updating its status, rewarding additional points
     * to the proposer and reflecting the changes in the system
     * @param temp - The suggestion to be accepted
     */

    public static void acceptSuggestion(Suggestion temp){
        temp.setStatus(SuggestionStatus.APPROVED);
        CampCommittee member = temp.getProposer();
        member.addPoints(); //Additional points for accepted Suggestion
        FileWriting.FileWriteSuggestion();
        WriteUser.FileWriteCampCommittee(); //Reflect the additional points given in CSV
        removeSuggestionFromCamp(temp);
        System.out.println("Suggestion accepted!");
    }
    
    /**
     * Removes a suggestion from the associated camp after it has been processed
     * @param temp - The suggestion to be removed
     */

    private static void removeSuggestionFromCamp(Suggestion temp){
        Camp camp = temp.getProposer().getCommitteeOf();
        camp.getListOfSuggestions().remove(temp);
    }

}
