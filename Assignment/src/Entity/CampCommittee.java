package Entity;

import java.util.ArrayList;

public class CampCommittee extends Student {
    private int points;
    private ArrayList<Suggestion> suggestions;

    public CampCommittee(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
        points = 0;
        suggestions = new ArrayList<Suggestion>();
    }

    public int getPoints(){
        return points;
    }
    
    public void addPoints(){
        points += 1;
    }

    public ArrayList<Suggestion> getSuggestions(){
        return suggestions;
    }
}
