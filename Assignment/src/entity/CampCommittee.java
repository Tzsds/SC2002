package entity;

import java.util.ArrayList;

import controller.camp.CampManager;
import controller.report.CampReport;
import controller.report.EnquiryReport;
import controller.report.ReportManager;

/**
 * The class represents a student who is part of the camp committee, 
 * inheriting basic student attributes. This class includes functionality to manage 
 * committee-specific information such as points, suggestions, and the camp committee they belong to
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class CampCommittee extends Student {
    /** The points earned by the camp committee member */
    private int points;
    /** The list of suggestions provided by the camp committee member */
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    /** The camp in which the student is a committee of */
    private Camp committeeOf;

    /**
     * Constructs a new CampCommittee object with the specified user ID, name, faculty, and password
     * Initializes points to zero
     *
     * @param userID - the user ID of the camp committee member
     * @param name - the name of the camp committee member
     * @param faculty - the faculty of the camp committee member
     * @param password - the password of the camp committee member
     */
    public CampCommittee(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
        points = 0;
    }

    /**
     * Constructs a new CampCommittee object with the specified user ID, name, faculty, password, 
     * and initial points
     *
     * @param userID - the user ID of the camp committee member
     * @param name - the name of the camp committee member
     * @param faculty - the faculty of the camp committee member
     * @param password - the password of the camp committee member
     * @param points - the initial points of the camp committee member
     */
    public CampCommittee(String userID, String name, String faculty, String password, int points){
        super(userID, name, faculty, password);
        this.points = points;
    }

    /**
     * Retrieves the points earned by the camp committee member
     * @return the points earned
     */
    public int getPoints(){
        return points;
    }

    /**
     * Retrieves the list of suggestions provided by the camp committee member
     * @return the suggestion list
     */
    public ArrayList<Suggestion> getSuggestions(){
        return suggestions;
    }

     /**
     * Retrieves the camp in which the student is a committee of
     *
     * @return the camp
     */
    public Camp getCommitteeOf(){
        return committeeOf;
    }
    

    /**
     * Sets the points earned by the camp committee member
     * @param points - the new points value
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Sets the camp in which the student is a committee of
     * @param camp - the camp
     */
    public void setCommitteeOf(Camp camp){
        committeeOf = camp; //Need this function when a Student registers for Camp Committee
    }

    /**
     * Increments the points earned by the camp committee member by one
     */
    public void addPoints(){
        points += 1;
    }

    /**
     * Adds a suggestion provided by the camp committee member to the list of suggestions
     * @param s - the suggestion to add
     */
    public void addSuggestion(Suggestion s){
        suggestions.add(s);
    }
    
    /**
     * Generates a camp report based on the committee's preferences
     */
    public void generateCampReport() {
        int reportType = ReportManager.promptCampReportType();
        CampReport report = new CampReport(committeeOf, reportType);
        report.generate();
    }

    /**
     * Generates an enquiry report related to the camp that the the student is a committee of
     */
    public void generateEnquiryReport() {
        EnquiryReport report = new EnquiryReport(committeeOf);
        report.generate();
    }

    /**
     * Displays information about the camps registered by the camp committee member
     * Differentiates between camps registered as committee members and camps registered as attendees
     */
    public void viewRegisteredCamps(){
        System.out.println("Camp registered as camp committeee: ");
        System.out.println("----------------------------------------");
        CampManager.printCampDetailsForStudents(committeeOf.getCampDetails());
        System.out.println();
        System.out.println("Camps registered as camp attendee:");
        System.out.println("----------------------------------------");
        for (Camp camps : getRegisteredCamps()){
            if (camps != committeeOf){
                CampManager.printCampDetailsForStudents(camps.getCampDetails());
                System.out.println("========================================");
            }
        }
    }
}
