package Controller.Enquiry;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Controller.Camp.CampManager;
import Controller.File.Enquiry.WriteEnquiry;
import Entity.Camp;
import Entity.Enquiry;
import Repository.CampRepository;
import Repository.EnquiryRepository;
import UI.InputScanner;

public class CampCommitteeEnquiryManager {
    // functions to be used by camp com

    // Only can view enquiries for their own camp
    public static void viewAllEnquiriesStaff(String staffID) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();
        // System.out.println("Staff ID: " + staffID);

        // Check if there are any enquiries for the specific staff
        boolean hasEnquiriesForStaff = allEnquiries.stream()
                .anyMatch(enquiry -> CampManager.isCampCreatedByStaff(enquiry.getCampName(),
                        staffID));

        if (!hasEnquiriesForStaff) {
            System.out.println("No Enquiries!");
            return;
        }

        int index = 1;
        for (Enquiry enquiry : allEnquiries) {
            // Start of Debug lines to check the camp name and staff ID
            /*
             * System.out.println("Enquiry Camp Name: " + enquiry.getCampName());
             * System.out.println("Staff ID: " + staffID);
             * 
             * System.out.println("Original Camp Name from EnquiryCSV: " +
             * enquiry.getCampName());
             * System.out.println("LowerCase Camp Name from EnquiryCSV: " +
             * enquiry.getCampName().toLowerCase());
             */

            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            // Print the camp names from the Camp CSV
            if (camp != null) {
                // System.out.println("Camp Name from CampCSV: " +
                // camp.getCampDetails().getCampName());

                // Check if the camp of the enquiry is created by the staff
                if (camp != null &&
                        CampManager.isCampCreatedByStaff(enquiry.getCampName(),
                                staffID)) {
                    System.out.println(index + ":");
                    System.out.println("Camp: " + enquiry.getCampName());
                    System.out.println("Sender: " + enquiry.getSender());
                    System.out.println("Content: " + enquiry.getContent());
                    System.out.println("Status: " + enquiry.getStatus());
                    System.out.println("------------------------------");
                    index++;
                }
            } else {
                System.out.println("Camp not found in CampCSV");
            }

        }
    }

    public static void replyEnquiry(String staffID) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();
        // System.out.println("Staff ID: " + staffID);

        // Filter pending enquiries (status is not "REPLIED")
        List<Enquiry> pendingEnquiries = allEnquiries.stream()
                .filter(enquiry -> CampManager.isCampCreatedByStaff(enquiry.getCampName(), staffID)
                        && enquiry.getStatus() != Enquiry.Status.REPLIED)
                .collect(Collectors.toList());

        // Check if there are any pending enquiries for the specific staff
        if (pendingEnquiries.isEmpty()) {
            System.out.println("No Pending Enquiries!");
            return;
        }

        int index = 1;
        for (Enquiry enquiry : pendingEnquiries) {
            // Start of Debug lines to check the camp name and staff ID
            /*
             * System.out.println("Enquiry Camp Name: " + enquiry.getCampName());
             * System.out.println("Staff ID: " + staffID);
             * 
             * System.out.println("Original Camp Name from EnquiryCSV: " +
             * enquiry.getCampName());
             * System.out.println("LowerCase Camp Name from EnquiryCSV: " +
             * enquiry.getCampName().toLowerCase());
             */

            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            // Print the camp names from the Camp CSV
            if (camp != null) {
                System.out.println(index + ":");
                System.out.println("Camp: " + enquiry.getCampName());
                System.out.println("Sender: " + enquiry.getSender());
                System.out.println("Content: " + enquiry.getContent());
                System.out.println("Status: " + enquiry.getStatus());
                System.out.println("------------------------------");
                index++;

                // Prompt staff to select an enquiry to reply
                int selectedIndex = InputScanner
                        .promptForInt("Enter the index of the enquiry you want to reply to (0 to cancel): ");

                // Retrieve the selected enquiry
                Enquiry selectedEnquiry = getEnquiryByIndex(staffID, selectedIndex);

                if (selectedEnquiry != null) {
                    // Display details of the selected enquiry
                    System.out.println("Selected Enquiry:");
                    System.out.println("Camp: " + selectedEnquiry.getCampName());
                    System.out.println("Sender: " + selectedEnquiry.getSender());
                    System.out.println("Content: " + selectedEnquiry.getContent());
                    System.out.println("Status: " + selectedEnquiry.getStatus());

                    // Prompt staff to enter the reply content
                    String replyContent = InputScanner.promptForString("Enter your reply: ");

                    // Update the selected enquiry
                    selectedEnquiry.setRepliedContent(replyContent);
                    selectedEnquiry.setStatus(Enquiry.Status.REPLIED);
                    selectedEnquiry.setReplier(staffID);

                    // Update the Enquiry CSV
                    WriteEnquiry.replyEnquiryInCSV(selectedEnquiry);

                    System.out.println("Enquiry replied successfully!");
                } else {
                    System.out.println("Invalid index. No enquiry selected.");
                }
            } else {
                System.out.println("Camp not found in CampCSV");
            }
        }

    }

    public static Enquiry getEnquiryByIndex(String staffID, int selectedIndex) {
        System.out.println("selected index: " + selectedIndex);
        List<Enquiry> staffEnquiries = EnquiryRepository.getListOfEnquiries().stream()
                .filter(enquiry -> CampManager.isCampCreatedByStaff(enquiry.getCampName(), staffID))
                .collect(Collectors.toList());

        // Check if the index is within the valid range
        if (selectedIndex > 0 && selectedIndex <= staffEnquiries.size()) {
            return staffEnquiries.get(selectedIndex - 1);
        } else {
            return null; // Invalid index
        }
    }

}
