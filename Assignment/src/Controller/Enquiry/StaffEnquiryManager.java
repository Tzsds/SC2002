package Controller.Enquiry;

import java.util.List;
import java.util.Scanner;

import Controller.Camp.CampManager;
import Controller.File.Enquiry.WriteEnquiry;
import Entity.Camp;
import Entity.Enquiry;
import Repository.CampRepository;
import Repository.EnquiryRepository;

public class StaffEnquiryManager {
    // functions to be used by staff

    // View all enquiries for staff but can only view camps created by them.
    public static void viewAllEnquiriesStaff(String staffID) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        for (Enquiry enquiry : allEnquiries) {
            // Debug lines to check the camp name and staff ID
            /*
             * System.out.println("Enquiry Camp Name: " + enquiry.getCampName());
             * System.out.println("Staff ID: " + staffID);
             */

            /*
             * System.out.println("Original Camp Name from EnquiryCSV: " +
             * enquiry.getCampName());
             * System.out.println("LowerCase Camp Name from EnquiryCSV: " +
             * enquiry.getCampName().toLowerCase());
             */

            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName().toLowerCase());

            /*
             * // Print the camp names from the Camp CSV
             * if (camp != null) {
             * System.out.println("Camp Name from CampCSV: " +
             * camp.getCampDetails().getCampName());
             * } else {
             * System.out.println("Camp not found in CampCSV");
             * }
             */

            // Check if the camp of the enquiry is created by the staff
            if (camp != null && CampManager.isCampCreatedByStaff(enquiry.getCampName().toLowerCase(), staffID)) {
                System.out.println("Camp: " + enquiry.getCampName());
                System.out.println("Sender: " + enquiry.getSender());
                System.out.println("Content: " + enquiry.getContent());
                System.out.println("Status: " + enquiry.getStatus());
                System.out.println("------------------------------");
            } else {
                System.out.println("No Enquiries!");
            }
        }
    }

    public static void replyEnquiry(String staffID) {
        Scanner scanner = new Scanner(System.in);

        // Display enquiries for staff
        viewAllEnquiriesStaff(staffID);

        // Prompt staff to select an enquiry to reply
        System.out.print("Enter the Camp Name of the enquiry you want to reply to: ");
        String selectedCampName = scanner.nextLine().toUpperCase(); // Assuming the user enters the Camp Name

        // Check if the selected camp has any pending enquiries
        if (EnquiryRepository.hasPendingEnquiries(selectedCampName)) {
            // Prompt staff to enter the reply content
            System.out.print("Enter your reply: ");
            String replyContent = scanner.nextLine();

            // Get the pending enquiry to reply to
            Enquiry selectedEnquiry = EnquiryRepository.getPendingEnquiry(selectedCampName);

            // Update the selected enquiry
            selectedEnquiry.setRepliedContent(replyContent);
            selectedEnquiry.setStatus(Enquiry.Status.REPLIED);
            selectedEnquiry.setReplier(staffID);

            // Update the Enquiry CSV
            WriteEnquiry.FileWriteEnquiry(selectedEnquiry);

            System.out.println("Enquiry replied successfully!");
        } else {
            System.out.println("No pending enquiries for the selected camp.");
        }
    }
}
