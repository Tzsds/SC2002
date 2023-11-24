package Controller.report;

import java.time.LocalDate;
import java.util.ArrayList;

import Controller.account.LoginManager;
import Controller.utils.Filter;
import Controller.utils.InputScanner;
import Controller.utils.Sort;
import entity.Camp;
import entity.CampCommittee;
import entity.Staff;

public class ReportManager {

    public static Camp promptWhichCampForStaff() {
        Staff staff = (Staff)LoginManager.getCurrentUser();
        ArrayList<Camp> campsCreated = staff.getListOfCampsCreated();

        int i = 1;
        for (Camp camp : campsCreated) {
            System.out.println(i + ". " + camp.getCampDetails().getCampName());
            i++;
        }
        int campNumber;
        while (true){
            campNumber = InputScanner.promptForInt("Enter number of the camp for report to be generated: ");
            if(campNumber <= 0 || campNumber > campsCreated.size())
                System.out.println("Invalid input. Please try again.");
            else 
                break;
        }
        Camp campChosen = campsCreated.get(campNumber-1);
        return campChosen;
    }

    public static int promptCampReportType() {
        System.out.println("How do want the report to be generated?\n" +
                            "1. All Attendees\n" +
                            "2. Only Camp Committees\n" +
                            "3. Only Non-Committee Participants\n");
        int reportType;     
        while(true){
            reportType = InputScanner.promptForInt("Enter the integer of the report type: ");
            if(reportType <= 0 || reportType > 3)
                System.out.println("Invalid input. Please try again.");
            else   
                break;
        }                
        return reportType;
    }

    public static int promptForCampReportFilter() {
        System.out.println("How do you want to select the camp(s) for the report generated?\n"
                        + "1. Select from camp list\n"
                        + "2. Select by filter");

        int filterType;
        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
    
        if (filterType == 1) {
            return 0;
        }
        System.out.println("\nHow do you want to filter by?\n"
                        + "1. Camp Name\n"
                        + "2. Location\n"
                        + "3. After a certain date\n"
                        + "4. Student");

        filterType = InputScanner.promptForInt("Enter your option (1/2/3/4): ");
        while (filterType != 1 && filterType != 2 && filterType != 3 && filterType != 4){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2/3/4): ");
        }

        return filterType;
    }

    public static int promptForPerformanceReportFilter() {
        System.out.println("How do you want to select the camp(s) for the report generated?\n"
                        + "1. Select from camp list\n"
                        + "2. Select by filter");

        int filterType;
        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
    
        if (filterType == 1) {
            return 0;
        }
        System.out.println("\nHow do you want to filter by?\n"
                        + "1. Camp Name\n"
                        + "2. Student");

        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
        return filterType;
    }

    public static String getFilteredReportContent(String reportType, ArrayList<Camp> listOfCamps) {
        String content = "";
        if (reportType == "studentlist") {
            for (Camp camp : listOfCamps) {
                CampReport report = new CampReport(camp, 1);
                String singleReport = report.getContent();
                content += singleReport;
                content += "\n";
            }
        }
        else if (reportType == "performance") {
            for (Camp camp : listOfCamps) {
                PerformanceReport report = new PerformanceReport(camp);
                String singleReport = report.getContent();
                content += singleReport;
                content += "\n";
            }
        }
        else {
            for (Camp camp : listOfCamps) {
                EnquiryReport report = new EnquiryReport(camp);
                String singleReport = report.getContent();
                content += singleReport;
                content += "\n";
            }
        }
        return content;
    }

    public static void generateCampReportForStaff() {
        Staff s = (Staff) LoginManager.getCurrentUser();

        //Default alphabetically sorted
        ArrayList<Camp> listOfCamps = s.getListOfCampsCreated();
        listOfCamps = Sort.insertionSortByName(listOfCamps);

        //Choosing filter method
        int filter = promptForCampReportFilter();
        switch(filter){
            case 0:
                Camp campChosen = promptWhichCampForStaff();
                int reportType = promptCampReportType();
                CampReport report = new CampReport(campChosen, reportType);
                report.generate();
                return;
            case 1: //name
                String filterCampName = InputScanner.promptForString("What camp name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterCampName);
                break;
            case 2: //location
                String filterLocation = InputScanner.promptForString("What location do you want to filter by?: ");
                listOfCamps = Filter.filterByLocation(listOfCamps, filterLocation);
                break;
            case 3: //date
                LocalDate filterDate = InputScanner.promptForDate("What date do you want to filter by in format (dd/mm/yyyy)? : ", 1);
                listOfCamps = Filter.filterByDate(listOfCamps, filterDate);
                break;
            case 4: //student
                String filterStudent = InputScanner.promptForString("What student name do you want to filter by?: ");
                listOfCamps = Filter.filterByStudent(listOfCamps, filterStudent);
                break;
        }
        System.out.println();
        if (listOfCamps.size() == 0){
            System.out.println("No available camps found.");
            return;
        }
        Report filteredReport = new Report();
        String filteredContent = getFilteredReportContent("studentlist", listOfCamps);
        filteredReport.setContent(filteredContent);
        filteredReport.inputAndSetFilePath();
        filteredReport.generate();
    }

    public static void generatePerformanceReportForStaff() {
        Staff s = (Staff) LoginManager.getCurrentUser();

        //Default alphabetically sorted
        ArrayList<Camp> listOfCamps = s.getListOfCampsCreated();
        listOfCamps = Sort.insertionSortByName(listOfCamps);

        //Choosing filter method
        int filter = promptForPerformanceReportFilter();
        switch(filter){
            case 0:
                Camp campChosen = promptWhichCampForStaff();
                PerformanceReport report = new PerformanceReport(campChosen);
                report.generate();
                return;
            case 1: //name
                String filterCampName = InputScanner.promptForString("What camp name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterCampName);
                break;
            case 2: //student
                String filterStudent = InputScanner.promptForString("What student name do you want to filter by?: ");
                listOfCamps = Filter.filterByCommitteeMember(listOfCamps, filterStudent);
                break;
        }
        System.out.println();
        if (listOfCamps.size() == 0){
            System.out.println("No available camps found.");
            return;
        }
        Report filteredReport = new Report();
        String filteredContent = getFilteredReportContent("performance", listOfCamps);
        filteredReport.setContent(filteredContent);
        filteredReport.inputAndSetFilePath();
        filteredReport.generate();
    }

    public static int promptForEnquiryReportFilter() {
        System.out.println("How do you want to select the camp(s) for the report generated?\n"
                        + "1. Select from camp list\n"
                        + "2. Select by filter");

        int filterType;
        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
    
        if (filterType == 1) {
            return 0;
        }
        System.out.println("\nHow do you want to filter by?\n"
                        + "1. Camp Name\n"
                        + "2. Replier Name");

        filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        while (filterType != 1 && filterType != 2){
            System.out.println("Wrong input entered. Please try again.");
            filterType = InputScanner.promptForInt("Enter your option (1/2): ");
        }
        return filterType;
    }

    public static void generateEnquiryReportForStaff() {
        Staff s = (Staff) LoginManager.getCurrentUser();

        //Default alphabetically sorted
        ArrayList<Camp> listOfCamps = s.getListOfCampsCreated();
        listOfCamps = Sort.insertionSortByName(listOfCamps);

        //Choosing filter method
        int filter = promptForEnquiryReportFilter();
        switch(filter){
            case 0:
                Camp campChosen = promptWhichCampForStaff();
                EnquiryReport report = new EnquiryReport(campChosen);
                report.generate();
                return;
            case 1: //name
                String filterCampName = InputScanner.promptForString("What camp name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterCampName);
                break;
            case 2: //student
                String filterStudent = InputScanner.promptForString("What student name do you want to filter by?: ");
                listOfCamps = Filter.filterByEnquiryReplier(listOfCamps, filterStudent);
                break;
        }
        System.out.println();
        if (listOfCamps.size() == 0){
            System.out.println("No available camps found.");
            return;
        }
        Report filteredReport = new Report();
        String filteredContent = getFilteredReportContent("enquiry", listOfCamps);
        filteredReport.setContent(filteredContent);
        filteredReport.inputAndSetFilePath();
        filteredReport.generate();
    }

    public static void viewCampReportForStaff() {
        Camp campChosen = promptWhichCampForStaff();
        CampReport report = new CampReport(campChosen, 1);
        report.printInTerminal();
    }

    public static void viewCampReportForCampCommittee() {
        CampCommittee committee = (CampCommittee)LoginManager.getCurrentUser();
        Camp camp = committee.getCommitteeOf();
        CampReport report = new CampReport(camp, 1);
        report.printInTerminal();
    }
}