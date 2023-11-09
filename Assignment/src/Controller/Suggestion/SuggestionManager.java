package Controller.Suggestion;

import Controller.Account.LoginManager;
import Entity.Suggestion;
import UI.InputScanner;

public class SuggestionManager {
    public static Suggestion createSuggestion(){
        String content = InputScanner.promptForString("What do you want to suggest?");
        String proposer = LoginManager.getCurrentUser().getUserID();
        return new Suggestion(proposer, content);
    }

    public static void editSuggestion(Suggestion suggestion){
        String content = InputScanner.promptForString("What is your editted Suggestion?: ");
        suggestion.setContent(content);
    }


}
