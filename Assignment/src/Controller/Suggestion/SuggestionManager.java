package Controller.Suggestion;

import java.util.ArrayList;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Controller.File.Suggestion.WriteSuggestion;
import Entity.CampCommittee;
import Entity.Suggestion;
import Entity.Suggestion.Status;
import Repository.SuggestionRepository;
import UI.InputScanner;

public class SuggestionManager {

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

    public static void editSuggestion(Suggestion suggestion){
        String content = InputScanner.promptForString("What is your editted Suggestion?: ");
        suggestion.setContent(content);
    }

    public static void printSuggestions(ArrayList<Suggestion> suggestions){
        if (suggestions.size() == 0){
            System.out.println("You have no Pending Suggestions");
            return;
        }
        for (Suggestion temp : suggestions){
            System.out.println(temp.getContent());
            System.out.println();
        }
    }

    public static void acceptSuggestion(Suggestion temp){
        temp.setStatus(Status.APPROVED);
        CampCommittee member = temp.getProposer();
        member.addPoints(); //Additional points for accepted Suggestion
        CampManager.removeSuggestion(member.getCommitteeOf(), temp);
        member.getSuggestions().remove(temp);
        SuggestionRepository.getListOfSuggestions().remove(temp);
        FileWriting.FileWriteSuggestion();
    }

    public static void rejectSuggestion(Suggestion temp){
        temp.setStatus(Status.REJECTED);
        CampCommittee member = temp.getProposer();
        CampManager.removeSuggestion(member.getCommitteeOf(), temp);
        member.getSuggestions().remove(temp);
        SuggestionRepository.getListOfSuggestions().remove(temp);
        FileWriting.FileWriteSuggestion();
    }


}
