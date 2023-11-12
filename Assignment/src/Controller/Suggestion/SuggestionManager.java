package Controller.Suggestion;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Controller.File.Suggestion.WriteSuggestion;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.Suggestion;
import Repository.SuggestionRepository;
import UI.InputScanner;

public class SuggestionManager {

    //Functions to be used by Camp Committee

    public static void addSuggestion(){
        CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
        Suggestion temp = createSuggestion();
        User.addSuggestion(temp);
        SuggestionRepository.addSuggestionToRepo(temp);
        CampManager.addSuggestion(User.getCommitteeOf(), temp);
        User.addPoints(); //Add one point for a suggestion given
        WriteSuggestion.writeSuggestion();
    }

    private static Suggestion createSuggestion(){
        String content = InputScanner.promptForString("What do you want to suggest?");
        return new Suggestion(content);
    }

    public static void editSuggestion(ArrayList<Suggestion> suggestions){
        printSuggestions(suggestions);
        while (true){
            int prompt = 0;
            try{
                prompt = InputScanner.promptForInt("Enter the suggestion no. you wish to edit: ");
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again");
                InputScanner.waitForUserInput();
                continue;
            }
            if (prompt <= 0 || prompt > suggestions.size()){
                System.out.println("Invalid number! Please try again");
                continue;
            }
            String content = InputScanner.promptForString("What is your editted Suggestion?: ");
            suggestions.get(prompt-1).setContent(content);
            return;
        }   
    }

    public static void deleteSuggestion(ArrayList<Suggestion> suggestions){
        printSuggestions(suggestions);
        while (true){
            int prompt = 0;
            try{
                prompt = InputScanner.promptForInt("Enter the suggestion no. you wish to edit: ");
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again");
                InputScanner.waitForUserInput();
                continue;
            }
            if (prompt <= 0 || prompt > suggestions.size()){
                System.out.println("Invalid number! Please try again");
                continue;
            }
            Suggestion temp = suggestions.get(prompt-1);
            deleteSuggestion(temp);
            System.out.println("Suggestion successfully deleted");
            return;
        }
    }

    public static void deleteSuggestion(Suggestion temp){
        CampCommittee User = temp.getProposer();
        Camp c1 = User.getCommitteeOf();
        User.getSuggestions().remove(temp);
        c1.getListOfSuggestions().remove(temp);
        SuggestionRepository.getListOfSuggestions().remove(temp);
        FileWriting.FileWriteSuggestion();
    }

    public static void printSuggestions(ArrayList<Suggestion> suggestions){
        if (suggestions.size() == 0){
            System.out.println("You have no Pending Suggestions");
            return;
        }
        int i = 1;
        for (Suggestion temp : suggestions){
            System.out.println(i + ". " + temp.getContent());
            i += 1;
        }
    }
}
