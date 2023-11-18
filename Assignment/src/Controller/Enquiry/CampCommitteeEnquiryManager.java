package Controller.Enquiry;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.Enquiry.WriteEnquiry;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.Enquiry;
import Repository.CampRepository;
import Repository.EnquiryRepository;
import UI.InputScanner;

public class CampCommitteeEnquiryManager {
    // functions to be used by camp com

    // Only can view enquiries for their own camp
    // get user object ->committeeOf

    public static void viewAllEnquiriesCampCommittee(CampCommittee campCommittee) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        if (campCommittee.getCommitteeOf() == null) {
            System.out.println("Camp Committee not assigned to a camp.");
            return;
        }

        int index = 1;
        for (Enquiry enquiry : allEnquiries) {
            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            if (camp == null) {
                System.out.println("Camp not found in CampCSV");
                continue;
            }

            // Check if the CampCommittee is associated with the camp
            if (campCommittee.getCommitteeOf().equals(camp)) {
                System.out.println(index + ":");
                System.out.println("Camp: " + enquiry.getCampName());
                System.out.println("Sender: " + enquiry.getSender());
                System.out.println("Content: " + enquiry.getContent());
                System.out.println("Status: " + enquiry.getStatus());
                System.out.println("------------------------------");
                index++;
            }
        }
    }

    public static void replyEnquiry(CampCommittee campCommittee) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        // Filter pending enquiries for the associated camp of the CampCommittee
        List<Enquiry> pendingEnquiries = allEnquiries.stream()
                .filter(enquiry -> campCommittee.getCommitteeOf() != null &&
                        campCommittee.getCommitteeOf().equals(CampRepository.getCampByCampName(enquiry.getCampName()))
                        && enquiry.getStatus() != Enquiry.Status.REPLIED)
                .collect(Collectors.toList());

        // Check if there are any pending enquiries for the specific camp committee
        if (pendingEnquiries.isEmpty()) {
            System.out.println("No Pending Enquiries!");
            return;
        }

        int index = 1;
        for (Enquiry enquiry : pendingEnquiries) {
            System.out.println(index + ":");
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Sender: " + enquiry.getSender());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
            index++;

            // Prompt camp committee to select an enquiry to reply
            int selectedIndex = InputScanner
                    .promptForInt("Enter the index of the enquiry you want to reply to (0 to cancel): ");

            // Retrieve the selected enquiry
            Enquiry selectedEnquiry = getEnquiryByIndex(campCommittee, selectedIndex);

            if (selectedEnquiry != null) {
                // Display details of the selected enquiry
                System.out.println("Selected Enquiry:");
                System.out.println("Camp: " + selectedEnquiry.getCampName());
                System.out.println("Sender: " + selectedEnquiry.getSender());
                System.out.println("Content: " + selectedEnquiry.getContent());
                System.out.println("Status: " + selectedEnquiry.getStatus());

                // Prompt camp committee to enter the reply content
                String replyContent = InputScanner.promptForString("Enter your reply: ");

                // Update the selected enquiry
                selectedEnquiry.setRepliedContent(replyContent);
                selectedEnquiry.setStatus(Enquiry.Status.REPLIED);
                selectedEnquiry.setReplier(campCommittee.getUserID());

                // Update the Enquiry CSV
                WriteEnquiry.replyEnquiryInCSV(selectedEnquiry);

                // Add points for the camp committee
                CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
                User.addPoints();

                System.out.println("Enquiry replied successfully!");
            } else {
                System.out.println("Invalid index. No enquiry selected.");
            }
        }
    }

    public static Enquiry getEnquiryByIndex(CampCommittee campCommittee, int selectedIndex) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        int index = 1;
        for (Enquiry enquiry : allEnquiries) {
            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            if (campCommittee.getCommitteeOf() != null &&
                    campCommittee.getCommitteeOf().equals(camp)
                    && enquiry.getStatus() != Enquiry.Status.REPLIED) {
                if (index == selectedIndex) {
                    return enquiry; // Found the selected enquiry
                }
                index++;
            }
        }

        return null; // No enquiry found for the given index
    }

}
