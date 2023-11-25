package controller.report;
import java.util.ArrayList;
import controller.utils.FormatDate;
import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import entity.Student;
import repository.userrepository.StaffRepository;

/**
 * The CampReport class generates a report for a specific camp with detailed information
 * about its participants, committee members, and camp details
 * 
 * CampReport extends Report so as to perform polymorphism and override funcitons,
 * while using function in Report
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class CampReport extends Report {

    /**
     * camp which report is to be generated
     */
    private Camp camp;
    /**
     * campDetails is the camp details of the camp report to be generated
     */
    private CampDetails campDetails;
    /**
     * reportheader contains detailed camp information
     */
    private String reportHeader;
    /**
     * reportBody contains the participants and their roles in the camp
     */
    private String reportBody;
    /**
     * reportType represents the type of report to be generated
     * 1 - all attendees
     * 2 - only camp committees
     * 3 - only non-camp committees
     */
    private int reportType;

    /**
     * Constructor for CampReport
     *
     * @param camp - The Camp object for which the report is generated
     * @param reportType - An integer representing the type of report to be generated
     */

    public CampReport(Camp camp, int reportType) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        this.reportType = reportType;
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
        String fileName = campDetails.getCampName() + "CampReport.txt";
        this.filePath = filePath.concat(fileName);
    }

    /**
     * Sets the header of the report with detailed camp information
     */

    public void setHeader() {
        String staffId = campDetails.getStaffInCharge();
        Staff staff = StaffRepository.getStaffByID(staffId);
        String staffName = staff.getName();
        int totalAttendees = camp.getParticipants().size() + camp.getCampCommittee().size();

        this.reportHeader =
            "Camp Name: " + campDetails.getCampName() + "\n" +
            "Description: " + campDetails.getDescription() + "\n" +
            "Camp Dates: " + FormatDate.formatDate(campDetails.getStartDate()) + " - " +
                            FormatDate.formatDate(campDetails.getEndDate()) + "\n" +
            "Registration Close Date: " + FormatDate.formatDate(campDetails.getCloseDate()) + "\n" +
            "Open To: " + campDetails.getUserGroup() + "\n" +
            "Location: " + campDetails.getLocation() + "\n" +
            "Staff In Charge: " + staffName + "\n" +
            "Total Slots Available: " + campDetails.getTotalSlots() + "\n" +
            "Total Camp Committee Slots Available: " + campDetails.getCampCommitteeSlots() + "\n" +
            "Current Number of Attendees: " + totalAttendees + "\n" +
            "Current Number of Committee Members: " + camp.getCampCommittee().size();

        if ((reportType == 2 && camp.getCampCommittee().size() == 0)
        || (reportType == 3 && camp.getParticipants().size() == 0) || totalAttendees == 0)  {
            return;
        }

        this.reportHeader += String.format("%-17s Role\n", "\n\nName");
    }

    /**
     * Sets the body of the report based on the report type
     */

    public void setBody() {
        String reportBody = "";
        if (reportType == 1 || reportType == 2) {
            // print committee members
            ArrayList<Student> committeeList = camp.getCampCommittee();
            for (Student committee : committeeList) {
                String name = committee.getName();
                reportBody += String.format("%-15s Committee Member\n", name);
            }
        }

        if (reportType == 1 || reportType == 3) {
            // print non-committee members
            ArrayList<Student> participantList = camp.getParticipants();
            for (Student participant : participantList) {
                String name = participant.getName();
                reportBody += String.format("%-15s Participant\n", name);
            }
        }
        this.reportBody = reportBody;
    }
}