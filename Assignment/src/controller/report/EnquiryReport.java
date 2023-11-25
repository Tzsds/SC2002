package controller.report;
import java.util.ArrayList;
import entity.Camp;
import entity.CampDetails;
import entity.Enquiry;
import repository.EnquiryRepository;

/**
 * The EnquiryReport class generates a report detailing enquiries associated with a specific camp
 * Total number of enquiries will be shown, and details regarding each enquiry will be shown.
 * 
 * EnquiryReport extends Report so as to perform polymorphism and override funcitons,
 * while using function in Report
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class EnquiryReport extends Report {
    /**
     * camp which report is to be generated
     */
    private Camp camp;
    /**
     * campDetails is the camp details of the camp report to be generated
     */
    private CampDetails campDetails;
    /**
     * reportheader contains camp name and the number of enquiries
     */
    private String reportHeader;
    /**
     * reportBody contains the details regarding each enquiry:
     * - question
     * - reply
     * - enquirer
     * - replier
     */
    private String reportBody;
    /**
     * enquiries represents the ArrayList of enquiries to be printed in this report
     */
    private ArrayList<Enquiry> enquiries;
    /**
     * Constructor for EnquiryReport
     * @param camp - camp which campReport is to be generated for
     */

    public EnquiryReport(Camp camp) {
        this.camp = camp;
        this.campDetails = camp.getCampDetails();
        setFilePath();
        setEnquiries();
        setHeader();
        setBody();
        this.content = reportHeader + reportBody;
    }

    /**
     * Sets the file path for the report
     * This function will override the function in the Report class
     */
    
    public void setFilePath() {
        String fileName = campDetails.getCampName() + "CampEnquiryReport.txt";
        this.filePath = filePath.concat(fileName);
    }

    /**
     * Intialise the enquiries for the report
     */

    public void setEnquiries() {
        ArrayList<Enquiry> enquiries = EnquiryRepository.getEnquiriesByCamp(camp);
        this.enquiries = enquiries;
    }

    /**
     * Sets the header for the report
     */    

    public void setHeader() {
        this.reportHeader = "Camp Name: " + campDetails.getCampName() + "\n" +
                            "Number of Enquiries: " + enquiries.size();
        if (enquiries.size() != 0) {
            this.reportHeader += String.format("%-5s Enquiry Details\n", "\n\nNo.");
        }
    }

    /**
     * Sets the body of the report based on the report type
     */

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