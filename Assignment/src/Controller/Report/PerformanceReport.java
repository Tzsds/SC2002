package Controller.Report;

import java.util.ArrayList;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.CampDetails;
import Entity.Student;
import Repository.UserRepository.CampCommitteeRepository;

public class PerformanceReport extends Report{
    protected Camp camp;
    protected CampDetails campDetails;
    protected String reportHeader;
    protected String reportBody;

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

        if (camp.getCampCommittee().size() != 0) {
            this.reportHeader += String.format("%-20s Points\n", "Committee Member");
        }
    }

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