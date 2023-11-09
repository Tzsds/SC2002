package Controller.Suggestion;

import Controller.Account.LoginManager;
import Entity.CampCommittee;
import Entity.Suggestion;
import Repository.SuggestionRepository;
import UI.InputScanner;

public class SuggestionManager {
    public void addSuggestion(){
        CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
        Suggestion temp = createSuggestion();
        User.getSuggestions().add(temp);
        SuggestionRepository.addSuggestionToRepo(temp);
    }

    private static Suggestion createSuggestion(){
        String content = InputScanner.promptForString("What do you want to suggest?");
        String proposer = LoginManager.getCurrentUser().getUserID();
        return new Suggestion(proposer, content);
    }

    public static void editSuggestion(Suggestion suggestion){
        String content = InputScanner.promptForString("What is your editted Suggestion?: ");
        suggestion.setContent(content);
    }


}
