package Entity;
public class CampCommittee extends Student {
    private int points;

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
}
