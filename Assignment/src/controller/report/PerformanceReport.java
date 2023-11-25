package controller.report;
import java.util.ArrayList;
import entity.Camp;
import entity.CampCommittee;
import entity.CampDetails;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;

/**
 * The PerformanceReport class generates a report for a specific camp with information
 * about its committee members and their corresponding points. Total number of committee members
 * are also computed
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */


public class PerformanceReport extends Report{
    /**
     * camp which report is to be generated
     */
    private Camp camp;
    /**
     * campDetails is the camp details of the camp report to be generated
     */
    private CampDetails campDetails;
    /**
     * reportheader contains camp name and the number of committee members in the camp
     */
    private String reportHeader;
    /**
     * reportBody contains the committee members and their corresponding points
     */
    private String reportBody;

    /**
     * Constructor for PerformanceReport
     * @param camp - represents the camp of performance report to be generated
     */

    public PerformanceReport(Camp camp) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        setFilePath();
        setHeader();
        setBody();
        this.content = reportHeader + reportBody;
    }

    /**
     * Sets the file path for the report
     * This function will override the function in the Report class
     */

    public void setFilePath() {
        String fileName = campDetails.getCampName() + "PerformanceReport.txt";
        this.filePath = filePath.concat(fileName);
    }

    /**
     * Sets the header for the report
     */

    public void setHeader() {
        this.reportHeader = "Camp Name: " + campDetails.getCampName() + "\n" +
                            "Number of Committee Members: " + camp.getCampCommittee().size();

        if (camp.getCampCommittee().size() != 0) {
            this.reportHeader += String.format("%-22s Points\n", "\n\nCommittee Member");
        }
    }
    
    /**
     * Sets the body of the report based on the report type
     */

    public void setBody() {
        String reportBody = "";

        ArrayList<Student> committeeList = camp.getCampCommittee();
        for (Student student : committeeList) {
            String name = student.getName();
            String id = student.getUserID();
            CampCommittee campCommittee = CampCommitteeRepository.getCommitteeByID(id);
            int points = campCommittee.getPoints();
            reportBody += String.format("%-20s " + points + "\n", name);
        }
        this.reportBody = reportBody;
    }
}