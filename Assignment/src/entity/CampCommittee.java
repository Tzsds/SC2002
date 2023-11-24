package entity;

import java.util.ArrayList;

import Controller.Report.CampReport;
import Controller.Report.EnquiryReport;
import Controller.Report.ReportManager;
import Controller.camp.CampManager;

public class CampCommittee extends Student {
    private int points;
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    private Camp committeeOf;

    //Constructor
    public CampCommittee(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
        points = 0;
    }

    public CampCommittee(String userID, String name, String faculty, String password, int points){
        super(userID, name, faculty, password);
        this.points = points;
    }

    //Accessor
    public int getPoints(){
        return points;
    }

    public ArrayList<Suggestion> getSuggestions(){
        return suggestions;
    }

    public Camp getCommitteeOf(){
        return committeeOf;
    }
    

    //Mutators
    public void setPoints(int points){
        this.points = points;
    }

    public void setCommitteeOf(Camp camp){
        committeeOf = camp; //Need this function when a Student registers for Camp Committee
    }

    public void addPoints(){
        points += 1;
    }

    public void addSuggestion(Suggestion s){
        suggestions.add(s);
    }
    
    //Functions
    public void generateCampReport() {
        int reportType = ReportManager.promptCampReportType();
        CampReport report = new CampReport(committeeOf, reportType);
        report.generate();
    }

    public void generateEnquiryReport() {
        EnquiryReport report = new EnquiryReport(committeeOf);
        report.generate();
    }

    public void viewRegisteredCamps(){
        System.out.println("Camp registered as camp committeee: ");
        System.out.println("----------------------------------");
        CampManager.printCampDetailsForStudents(committeeOf.getCampDetails());
        System.out.println();
        System.out.println("Camps regsitered as camp attendee:");
        System.out.println("----------------------------------");
        for (Camp camps : getRegisteredCamps()){
            if (camps != committeeOf){
                CampManager.printCampDetailsForStudents(camps.getCampDetails());
                System.out.println("=================================");
            }
        }
    }
}
