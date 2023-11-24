package controller.report;

import java.util.ArrayList;

import entity.Camp;
import entity.CampCommittee;
import entity.CampDetails;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;

public class PerformanceReport extends Report{
    private Camp camp;
    private CampDetails campDetails;
    private String reportHeader;
    private String reportBody;

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
                            "Number of Committee Members: " + camp.getCampCommittee().size();

        if (camp.getCampCommittee().size() != 0) {
            this.reportHeader += String.format("%-22s Points\n", "\n\nCommittee Member");
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