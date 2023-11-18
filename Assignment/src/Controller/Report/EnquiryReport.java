// package Controller.Report;

// import java.util.ArrayList;

// import Entity.Camp;
// import Entity.CampDetails;
// import Entity.Staff;
// import Entity.Student;
// import Repository.UserRepository.StaffRepository;

// public class EnquiryReport extends Report {
//     protected Camp camp;
//     protected CampDetails campDetails;
//     protected String reportHeader;
//     protected String reportBody;

//     public EnquiryReport(Camp camp, int reportType) {
//         this.camp = camp;
//         this.campDetails = camp.getCampDetails();
//         setFilePath();
//         setHeader();
//         setBody(reportType);
//         this.content = reportHeader + reportBody;
//     }

//     public void setFilePath() {
//         String fileName = campDetails.getCampName() + "CampReport.txt";
//         this.filePath = filePath.concat(fileName);
//     }

//     public void setHeader() {
//         String staffId = campDetails.getStaffInCharge();
//         Staff staff = StaffRepository.getStaffByID(staffId);
//         String staffName = staff.getName();
//         int totalAttendees = camp.getParticipants().size() + camp.getCampCommittee().size();

//         this.reportHeader =
//             "Camp Name: " + campDetails.getCampName() + "\n" +
//             "Description: " + campDetails.getDescription() + "\n" +
//             "Camp Dates: " + formatDate(campDetails.getStartDate()) + " - " +
//                             formatDate(campDetails.getEndDate()) + "\n" +
//             "Registration Close Date: " + formatDate(campDetails.getCloseDate()) + "\n" +
//             "Open To: " + campDetails.getUserGroup() + "\n" +
//             "Location: " + campDetails.getLocation() + "\n" +
//             "Staff In Charge: " + staffName + "\n" +
//             "Total Slots Available: " + campDetails.getTotalSlots() + "\n" +
//             "Total Camp Committee Slots Available: " + campDetails.getCampCommitteeSlots() + "\n" +
//             "Current Number of Attendees: " + totalAttendees + "\n" +
//             "Current Number of Committee Members: " + camp.getCampCommittee().size() + "\n\n";
//     }

//     public void setBody(int reportType) {
//         String reportBody = String.format("%-15s Role\n", "Name");

//         if (reportType == 1 || reportType == 2) {
//             // print committee members
//             ArrayList<Student> committeeList = camp.getCampCommittee();
//             for (Student committee : committeeList) {
//                 String name = committee.getName();
//                 reportBody += String.format("%-15s Committee Member\n", name);
//             }
//         }

//         if (reportType == 1 || reportType == 3) {
//             // print non-committee members
//             ArrayList<Student> participantList = camp.getParticipants();
//             for (Student participant : participantList) {
//                 String name = participant.getName();
//                 reportBody += String.format("%-15s Participant\n", name);
//             }
//         }
//         this.reportBody = reportBody;
//     }

// }
