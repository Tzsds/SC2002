package Controller.Report;

import java.util.ArrayList;

import Controller.Account.LoginManager;
import Entity.Camp;
import Entity.Staff;
import UI.InputScanner;

public class ReportManager {

    public static Camp promptWhichCampForStaff() {
        // given that number of camps  created > 0
        Staff staff = (Staff)LoginManager.getCurrentUser();
        ArrayList<Camp> campsCreatedList = staff.getListOfCampsCreated();

        int i = 1;
        for (Camp camp : campsCreatedList) {
            System.out.println(i + ". " + camp.getCampDetails().getCampName());
            i++;
        }
        
        int campNumber = InputScanner.promptForInt("Enter the number of camp which report is to be generated for: ");
        Camp campChosen = campsCreatedList.get(campNumber-1);
        return campChosen;
    }

    public static int promptCampReportType() {
        System.out.println("How do want the report to be generated?\n" +
                            "1. All Attendees\n" +
                            "2. Only Camp Committees\n" +
                            "3. Only Non-Committee Participants\n");
        int reportType = InputScanner.promptForInt("Enter the integer of the report type: ");
        return reportType;
    }
}