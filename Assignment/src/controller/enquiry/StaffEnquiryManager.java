package controller.enquiry;

import java.util.List;
import java.util.stream.Collectors;

import controller.camp.CampManager;
import controller.file.enquiry.WriteEnquiry;
import controller.utils.InputScanner;
import entity.Camp;
import entity.Enquiry;
import entity.User;
import repository.CampRepository;
import repository.EnquiryRepository;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StudentRepository;

/**
 * This class manages Staff-specific interations and operations related to
 * enquiries
 * Provides methods for staff to view all enquiries, reply to pending enquiries,
 * and retrieve enquiries based on selected indices
 */
public class StaffEnquiryManager {
    /**
     * Displays all enquiries for a staff member, limited to camps created by them.
     *
     * @param staffID - The ID of the staff member.
     */
    public static void viewAllEnquiriesStaff(String staffID) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        // Check if there are any enquiries for the specific staff
        boolean hasEnquiriesForStaff = allEnquiries.stream()
                .anyMatch(enquiry -> CampManager.isCampCreatedByStaff(enquiry.getCampName(),
                        staffID));

        if (!hasEnquiriesForStaff) {
            return;
        }

        int index = 1;
        for (Enquiry enquiry : allEnquiries) {
            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());
            // Check if the camp of the enquiry is created by the staff
            if (camp != null &&
                    CampManager.isCampCreatedByStaff(enquiry.getCampName(),
                            staffID)) {
                User sender = StudentRepository.getStudentByID(enquiry.getSender());
                if (sender == null) {
                    sender = CampCommitteeRepository.getCommitteeByID(enquiry.getSender());
                }
                System.out.println(index + ":");
                System.out.println("Camp: " + enquiry.getCampName());
                System.out.println("Sender: " + sender.getName());
                System.out.println("Content: " + enquiry.getContent());
                System.out.println("Status: " + enquiry.getStatus());
                System.out.println("------------------------------");
                index++;
            }
        }
    }

    /**
     * Allows a staff member to reply to pending enquiries and updates the
     * status and replied content accordingly.
     *
     * @param staffID - The ID of the staff member.
     */
    public static void replyEnquiry(String staffID) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

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
            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            // Print the camp names from the Camp CSV
            if (camp == null) {
                System.out.println("Camp not found in CampCSV");
                break;
            }

            System.out.println(index + ":");
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Sender: " + enquiry.getSender());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
            index++;
        }

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

    }

    /**
     * Retrieves an enquiry based on the selected index requested by the staff
     *
     * @param staffID       - The ID of the staff member.
     * @param selectedIndex - The index of the selected enquiry.
     * @return - The selected enquiry or null if the index is invalid.
     */
    public static Enquiry getEnquiryByIndex(String staffID, int selectedIndex) {
        System.out.println("selected index: " + selectedIndex);

        // Fetches all pending enquiries related to camps created by the staff member.
        List<Enquiry> staffEnquiries = EnquiryRepository.getListOfEnquiries().stream()
                .filter(enquiry -> CampManager.isCampCreatedByStaff(enquiry.getCampName(), staffID)
                        && enquiry.getStatus() == Enquiry.Status.PENDING)
                .collect(Collectors.toList());

        // Check if the index is within the valid range
        if (selectedIndex >= 1 && selectedIndex <= staffEnquiries.size()) {
            return staffEnquiries.get(selectedIndex - 1);
        } else {
            return null; // Invalid index or no PENDING enquiries
        }
    }

}
