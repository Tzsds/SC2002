package controller.report;
import java.util.ArrayList;

import controller.utils.FormatDate;
import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import entity.Student;
import repository.userrepository.StaffRepository;

public class CampReport extends Report{
    private Camp camp;
    private CampDetails campDetails;
    private String reportHeader;
    private String reportBody;
    private int reportType;

    public CampReport(Camp camp, int reportType) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        this.reportType = reportType;
        setFilePath();
        setHeader();
        setBody();
        this.content = reportHeader + reportBody;
    }

    public void setFilePath() {
        String fileName = campDetails.getCampName() + "CampReport.txt";
        this.filePath = filePath.concat(fileName);
    }

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