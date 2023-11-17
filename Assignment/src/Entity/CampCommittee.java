package Entity;

import java.util.ArrayList;

import Controller.Report.CampReport;
import Controller.Report.ReportManager;

public class CampCommittee extends Student {
    private int points;
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    private Camp committeeOf;

    public CampCommittee(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
        points = 0;
    }

    public CampCommittee(String userID, String name, String faculty, String password, int points){
        super(userID, name, faculty, password);
        this.points = points;
    }

    public int getPoints(){
        return points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }

    public void addPoints(){
        points += 1;
    }

    public ArrayList<Suggestion> getSuggestions(){
        return suggestions;
    }

    public void addSuggestion(Suggestion s){
        suggestions.add(s);
    }
    
    public void setCommitteeOf(Camp camp){
        committeeOf = camp; //Need this function when a Student registers for Camp Committee
    }

    public Camp getCommitteeOf(){
        return committeeOf;
    }

    public void generateCampReport() {
        int reportType = ReportManager.promptCampReportType();
        CampReport report = new CampReport(committeeOf, reportType);
        report.generate();
    }
}
