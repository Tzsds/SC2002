package Controller.Report;

import java.util.ArrayList;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.CampDetails;
import Entity.Student;

public class PerformanceReport extends Report{
    Camp camp;
    CampDetails campDetails;
    String reportHeader;
    String reportBody;

    public PerformanceReport(Camp camp) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        setFilePath();
        setHeader();
        setBody();
        this.content = reportHeader + reportBody;
    }

    public void setFilePath() {
        String fileName = campDetails.getCampName() + "PerformanceReport.txt";
        this.filePath = filePath.concat(fileName);
    }

    public void setHeader() {
        this.reportHeader = "Camp Name: " + campDetails.getCampName() + "\n" +
                            "Number of Committee Members: " + camp.getCampCommittee().size() + "\n\n";
    }

    public void setBody() {
        String reportBody = String.format("%-20s Points\n", "Committee Member");

        ArrayList<Student> committeeList = camp.getCampCommittee();
        for (Student student : committeeList) {
            String name = student.getName();
            CampCommittee campCommittee = (CampCommittee)student;
            int points = campCommittee.getPoints();
            reportBody += String.format("%-20s " + points + "\n", name);
        }
        this.reportBody = reportBody;
    }
}