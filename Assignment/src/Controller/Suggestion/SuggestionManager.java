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
import Entity.Suggestion.Status;
import Repository.SuggestionRepository;
import UI.InputScanner;

public class SuggestionManager {

    //Functions to be used by Camp Committee

    public static void addSuggestion(){
        CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
        String content = InputScanner.promptForString("What do you want to suggest?: ");
        Suggestion temp = new Suggestion(User, content);
        User.addSuggestion(temp);
        SuggestionRepository.addSuggestionToRepo(temp);
        CampManager.addSuggestion(User.getCommitteeOf(), temp);
        User.addPoints(); //Add one point for a suggestion given
        WriteSuggestion.writeSuggestion();
        System.out.println("Suggestion successfully added");
    }

    public static void editSuggestion(ArrayList<Suggestion> suggestions){
        int count = 1;
        ArrayList<Suggestion> temporaryList = getSuggestions(suggestions, true);
        if (temporaryList.size() == 0){
            System.out.println("You have no pending suggestions.");
            return;
        }
        for (Suggestion s : temporaryList){
            System.out.println(count + ". " + s.getContent());
            count += 1;
        }
        System.out.println("----------------------------------------");
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
            if (prompt <= 0 || prompt > temporaryList.size()){
                System.out.println("Invalid number! Please try again");
                continue;
            }
            String content = InputScanner.promptForString("What is your editted Suggestion?: ");
            Suggestion temp = temporaryList.get(prompt-1);
            temp.setContent(content);
            WriteSuggestion.writeSuggestion();
            System.out.println("Suggestion successfully editted!");
            return;
        }   
    }

    public static void deleteSuggestion(ArrayList<Suggestion> suggestions){
        int count = 1;
        ArrayList<Suggestion> temporaryList = getSuggestions(suggestions, true);
        if (temporaryList.size() == 0){
            System.out.println("You have no pending suggestions.");
            return;
        }
        for (Suggestion s : temporaryList){
            System.out.println(count + ". " + s.getContent());
            count += 1;
        }
        System.out.println("----------------------------------------");
        while (true){
            int prompt = 0;
            try{
                prompt = InputScanner.promptForInt("Enter the suggestion no. you wish to delete: ");
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again");
                InputScanner.waitForUserInput();
                continue;
            }
            if (prompt <= 0 || prompt > temporaryList.size()){
                System.out.println("Invalid number! Please try again");
                continue;
            }
            Suggestion temp = suggestions.get(prompt-1);
            CampCommittee User = temp.getProposer();
            
            //deleting from database
            Camp c1 = User.getCommitteeOf();
            User.getSuggestions().remove(temp);
            c1.getListOfSuggestions().remove(temp);
            SuggestionRepository.getListOfSuggestions().remove(temp);
            FileWriting.FileWriteSuggestion();

            System.out.println("Suggestion successfully deleted");
            return;
        }
    }

    public static void printSuggestions(ArrayList<Suggestion> suggestions){
        System.out.println("=================================");
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
                System.out.println("=================================");
            }
        }
    }

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
