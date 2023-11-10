package Controller.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Entity.Camp;
import Entity.CampCommittee;
import Entity.CampDetails;
import Entity.Enquiry;
import Entity.Staff;
import Entity.Student;
import Entity.User;
import Repository.UserRepository;

public class FileWriting {

    public static void FileWriteUser() {
        String allUserCSV = "Assignment/database/users.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(allUserCSV))) {

            // Header of CSV File
            String header = "name,role,faculty,userID,password\n";
            String role;
            writer.write(header);

            for (int i = 0; i < UserRepository.getSizeOfUsers(); i++) {
                User temp = UserRepository.get(i);
                if (temp instanceof Staff) {
                    role = "Staff";
                } else if (temp instanceof CampCommittee) {
                    role = "CampCommittee";
                } else {
                    role = "Student";
                }
                String data = temp.getName() + "," + role + "," + temp.getFaculty() + "," + temp.getUserID() + ","
                        + temp.getPassword() + "\n";
                writer.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileWrite(CampDetails campDetails) {
        String campDetailsCSV = "Assignment/database/camp_details.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campDetailsCSV,true))) {

            // If the file is empty, write the header
            if (Files.size(Paths.get(campDetailsCSV)) == 0) {
                System.out.println("ininnini");
                writer.write("campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n");
            }

            // Write campDetails to the CSV file
            String data = campDetails.getCampName() + "," +
                    campDetails.getStartDate() + "," +
                    campDetails.getEndDate() + "," +
                    campDetails.getCloseDate() + "," +
                    campDetails.getOpenTo() + "," +
                    campDetails.getLocation() + "," +
                    campDetails.getTotalSlots() + "," +
                    campDetails.getCampComitteeSlots() + "," +
                    campDetails.getDescription() + "," +
                    campDetails.getStaffInCharge() + "," +
                    campDetails.getVisibility() + "\n";
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileWrite(Camp camp) {
        String campStudentListCSV = "Assignment/database/camp_student_list.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campStudentListCSV))) {
            
            // If the file is empty, write the header
            if (Files.size(Paths.get(campStudentListCSV)) == 0) {
                writer.write("campName,userID,Role\n");
            }

            // Write campDetails to the CSV file
            CampDetails campDetails = camp.getCampDetails();
            String campName = campDetails.getCampName();
            ArrayList<Student> attendeesList = camp.getParticipants();
            ArrayList<Student> committeeList = camp.getCampComittee();

            if (attendeesList != null) {
                for (int i = 0; i < attendeesList.size(); i++) {
                    User user = attendeesList.get(i);
                    String data = campName + "," + user.getUserID() + "," + "Attendee" + "\n";
                    writer.write(data);
                }
            }

            if (committeeList != null) {
                for (int i = 0; i < committeeList.size(); i++) {
                    User user = committeeList.get(i);
                    String data = campName + "," + user.getUserID() + "," + "Committee" + "\n";
                    writer.write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriteEnquiry(Enquiry enquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enquiryCSV, true))) {

            // If the file is empty, write the header
            if (Files.size(Paths.get(enquiryCSV)) == 0) {
                writer.write("sender,content,status\n");
            }

            // Write enquiry to the CSV file
            String data = enquiry.getSender() + "," +
                    enquiry.getContent() + "," +
                    enquiry.getStatus() + "," +
                    enquiry.getReplier() + "\n";
            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}