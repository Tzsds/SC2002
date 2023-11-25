package controller.suggestion;

import java.util.ArrayList;

import controller.account.LoginManager;
import controller.camp.CampManager;
import controller.file.FileWriting;
import controller.file.suggestion.WriteSuggestion;
import controller.file.user.WriteUser;
import controller.utils.InputScanner;
import entity.Camp;
import entity.CampCommittee;
import entity.Suggestion;
import entity.Suggestion.Status;
import repository.SuggestionRepository;
/**
 * This class provides functionalities related to suggestions,
 * allowing Camp Committee members to add, edit, delete and view suggestions.
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class SuggestionManager {
    /**
     * Adds a new suggestion to the system
     */

    public static void addSuggestion(){
        CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
        String content = InputScanner.promptForString("What do you want to suggest?: ");
        Suggestion temp = new Suggestion(User, content);
        User.addSuggestion(temp);
        SuggestionRepository.addSuggestionToRepo(temp);
        CampManager.addSuggestion(User.getCommitteeOf(), temp);
        User.addPoints(); //Add one point for a suggestion given
        WriteSuggestion.writeSuggestion(); //Add Suggestion to the CSV
        WriteUser.FileWriteCampCommittee(); //To reflect the points in the CampComm CSV
        System.out.println("Suggestion successfully added");
    }

    /**
     * Edits a selected suggestion's content
     * @param suggestions - Camp Committee User list of suggestions
     */

    public static void editSuggestion(ArrayList<Suggestion> suggestions){
        int count = 1;
        ArrayList<Suggestion> temporaryList = getSuggestions(suggestions, true);
        if (temporaryList.size() == 0){
            System.out.println("You have no pending suggestions.");
            return;
        }
        System.out.println("========================================");
        for (Suggestion s : temporaryList){
            System.out.println(count + ". " + s.getContent());
            count += 1;
        }
        System.out.println("========================================");
        while (true){
            int prompt = 0;
            int size = temporaryList.size();
            prompt = InputScanner.promptForInt("Enter the suggestion no. you wish to edit (Enter " 
                                                + (size+1) + " to exit): ");
            
            if (prompt <= 0 || prompt > size+1){
                System.out.println("Invalid number! Please try again");
                continue;
            }

            if (prompt == size + 1){
                System.out.println("Returning to Suggestion main menu...");
                return;
            }

            String content = InputScanner.promptForString("What is your editted Suggestion? : ");
            Suggestion temp = temporaryList.get(prompt-1);
            temp.setContent(content);
            WriteSuggestion.writeSuggestion();
            System.out.println("Suggestion successfully editted!");
            return;
        }   
    }

    /**
     * Deletes a Suggestion from the system
     * @param suggestions - Camp Committee User list of suggestions
     */

    public static void deleteSuggestion(ArrayList<Suggestion> suggestions){
        int count = 1;
        ArrayList<Suggestion> temporaryList = getSuggestions(suggestions, true);
        if (temporaryList.size() == 0){
            System.out.println("You have no pending suggestions.");
            return;
        }
        System.out.println("========================================");
        for (Suggestion s : temporaryList){
            System.out.println(count + ". " + s.getContent());
            count += 1;
        }
        System.out.println("========================================");
        while (true){
            int prompt = 0;
            int size = temporaryList.size();
            prompt = InputScanner.promptForInt("Enter the suggestion no. you wish to delete (Input " +
                                                (size+1) + " to exit): ");
        
            if (prompt <= 0 || prompt > size + 1){
                System.out.println("Invalid number! Please try again");
                continue;
            }
            if (prompt == size+1){
                System.out.println("Returning to Suggestion main menu...");
                return;
            }
            Suggestion temp = suggestions.get(prompt-1);
            CampCommittee User = temp.getProposer();
            
            Camp c1 = User.getCommitteeOf();
            User.getSuggestions().remove(temp);
            c1.getListOfSuggestions().remove(temp);
            SuggestionRepository.getListOfSuggestions().remove(temp);
            FileWriting.FileWriteSuggestion();

            System.out.println("Suggestion successfully deleted");
            return;
        }
    }

    /**
     * Print list of suggestions, differntiating between pending and processed ones
     * @param suggestions - The list of suggestions to display
     */

    public static void printSuggestions(ArrayList<Suggestion> suggestions){
        System.out.println("========================================");
        if (suggestions.size() == 0){
            System.out.println("No suggestions found!");
            return;
        }
        int i = 1;
        if (suggestions.get(0).getStatus() == Status.PENDING){
            for (Suggestion temp : suggestions){
                System.out.println(i + ". " + temp.getContent());
                i += 1;
            }
        }
        else{
            for (Suggestion temp : suggestions){
                System.out.println(temp.getContent() + " (" + temp.getStatus() +")");
                // System.out.println("=================================");
            }
        }
        System.out.println("========================================");
    }

    /**
     * Retrives a filtered list of suggestions based on the pending status
     * @param suggestion - The list of suggestions to filter
     * @param pending - If true, returns list of pending instructions, else return 
     * list of processed instructions
     * @return - The filtered list of suggestions
     */
    public static ArrayList<Suggestion> getSuggestions(ArrayList<Suggestion> suggestion, boolean pending){
        ArrayList<Suggestion> tempList = new ArrayList<>(); //Pending
        ArrayList<Suggestion> tempList1 = new ArrayList<>(); //Processed

        for (Suggestion s : suggestion){
            if (s.getStatus() == Status.PENDING){
                tempList.add(s);
            }
            else{
                tempList1.add(s);
            }
        }
        if (pending){
            return tempList;
        }
        return tempList1;
    }
}
