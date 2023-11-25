package controller.report;

import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Enquiry;
import repository.EnquiryRepository;

public class EnquiryReport extends Report {
    private Camp camp;
    private CampDetails campDetails;
    private String reportHeader;
    private String reportBody;
    private ArrayList<Enquiry> enquiries;

    public EnquiryReport(Camp camp) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        setFilePath();
        setEnquiries();
        setHeader();
        setBody();
        this.content = reportHeader + reportBody;
    }

    public void setFilePath() {
        String fileName = campDetails.getCampName() + "CampEnquiryReport.txt";
        this.filePath = filePath.concat(fileName);
    }

    public void setEnquiries() {
        ArrayList<Enquiry> enquiries = EnquiryRepository.getEnquiriesByCamp(camp);
        this.enquiries = enquiries;
    }

    public void setHeader() {
        this.reportHeader = "Camp Name: " + campDetails.getCampName() + "\n" +
                            "Number of Enquiries: " + enquiries.size();
        if (enquiries.size() != 0) {
            this.reportHeader += String.format("%-5s Enquiry Details\n", "\n\nNo.");
        }
    }

    public void setBody() {
        String reportBody = "";

        int i = 1;
        for (Enquiry enquiry : enquiries) {
            String question = enquiry.getContent();
            String reply = enquiry.getRepliedContent();
            String sender = enquiry.getSender();
            String replier = enquiry.getReplier();
            reportBody += String.format("%-3s " + "Question: " + question + "\n", i);
            reportBody += String.format("%-3s " + "Reply: " + reply + "\n", "");
            reportBody += String.format("%-3s " + "Enquirer: " + sender + "\n", "");
            reportBody += String.format("%-3s " + "Replier: " + replier + "\n\n", "");
            i++;
        }
        this.reportBody = reportBody;
    }
}