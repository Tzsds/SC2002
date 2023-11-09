package Entity;

import java.util.ArrayList;

public class CampCommittee extends Student {
    private int points;
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    private Camp committeeOf;

    public CampCommittee(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
        points = 0;
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
    
    public void setCommitteeOf(Camp camp){
        committeeOf = camp; //Need this function when a Student registers for Camp Committee
    }

    public Camp getCommitteeOf(){
        return committeeOf;
    }
}
