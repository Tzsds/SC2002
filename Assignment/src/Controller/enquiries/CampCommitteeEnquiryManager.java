package Controller.enquiries;

import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import Controller.File.Enquiry.WriteEnquiry;
import Controller.File.User.WriteUser;
import Controller.account.LoginManager;
import Controller.utils.InputScanner;
import entity.Camp;
import entity.CampCommittee;
import entity.Enquiry;
import repository.CampRepository;
import repository.EnquiryRepository;

public class CampCommitteeEnquiryManager {
    // functions to be used by camp com

    // Only can view enquiries for their own camp
    // only can create enquiries that they are not a camp comittee for.
    // get user object ->committeeOf

    public static Enquiry createEnquiryCampCommittee(CampCommittee campCommittee) {

        System.out.println("Which Camp do you want to enquiry on?");

        List<Camp> allAvailCamps = CampRepository.getAvailableCampsForStudent(campCommittee);

        // Display the list of camps only once
        for (int i = 0; i < allAvailCamps.size(); i++) {
            System.out.println((i + 1) + ": " + allAvailCamps.get(i).getCampDetails().getCampName());
        }

        while (true) {
            // Prompt for camp index
            int campIndex = InputScanner.promptForInt("Choose a camp (Enter the index, 0 to cancel): ");

            if (campIndex == 0) {
                return null; // Cancelled
            } else if (campIndex < 1 || campIndex > allAvailCamps.size()) {
                System.out.println("Invalid index. Please choose a valid index.");
                continue;
            }

            // Get the selected camp
            Camp selectedCamp = allAvailCamps.get(campIndex - 1);
            String campChoice = selectedCamp.getCampDetails().getCampName();

            // Check if the current camp committee member is a camp committee member for the
            // selected camp
            if (campCommittee.getCommitteeOf() != null && campCommittee.getCommitteeOf().equals(selectedCamp)) {
                System.out.println("You cannot submit an enquiry for a camp you are a committee member of.");
                continue;
            }

            String content = InputScanner.promptForString("What enquiries do you have?: ");
            String sender = campCommittee.getUserID();
            System.out.println("This is the content written: " + content);

            return new Enquiry(sender, content, campChoice);
        }
    }

    public static void addEnquiry() {
        CampCommittee User = (CampCommittee) LoginManager.getCurrentUser();
        Enquiry enquiry = createEnquiryCampCommittee(User);

        if (enquiry == null) {
            System.out.println("Enquiry creation cancelled!");
            return; // Exit the method without adding the enquiry
        }

        User.getEnquiries().add(enquiry);
        EnquiryRepository.addEnquiryToRepo(enquiry);

        // Write the new enquiry to CSV
        WriteEnquiry.FileWriteEnquiry(enquiry);
        System.out.println("Enquiry added successfully!");
    }

        public static void deleteEnquiry(CampCommittee currentUser) {
        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());

        if (studentEnquiries.isEmpty()) {
            System.out.println("No enquiries to remove!");
            return;
        }

        System.out.println("Your Enquiries:");
        viewAllEnquiriesCampCommittee(currentUser);

        while (true) {
            try {
                int choice = InputScanner.promptForInt("Enter the number of the enquiry to DELETE (0 to cancel): ");

                if (choice == 0) {
                    System.out.println("Deletion cancelled.");
                    return;
                }

                if (choice < 1 || choice > studentEnquiries.size()) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                Enquiry selectedEnquiry = studentEnquiries.get(choice - 1);

                if (selectedEnquiry.getStatus() != Enquiry.Status.REPLIED) {
                    // Perform the deletion logic here
                    WriteEnquiry.deleteEnquiryFromCSV(selectedEnquiry);
                    studentEnquiries.remove(selectedEnquiry);
                    System.out.println("Enquiry deleted successfully.");
                } else {
                    System.out.println("You cannot delete a replied enquiry.");
                }

                return;

            } catch (InputMismatchException e) {
               InputScanner.promptForInt("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void editEnquiry(CampCommittee currentUser) {

        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());

        if (studentEnquiries.isEmpty()) {
            System.out.println("No enquiries to edit!");
            return;
        }

        System.out.println("Your Enquiries:");
        viewAllEnquiriesCampCommittee(currentUser);

        while (true) {
            try {
                int choice = InputScanner.promptForInt("Enter the number of the enquiry to edit (0 to cancel): ");

                if (choice == 0) {
                    System.out.println("Editing cancelled.");
                    return;
                }

                if (choice < 1 || choice > studentEnquiries.size()) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                Enquiry selectedEnquiry = studentEnquiries.get(choice - 1);

                if (selectedEnquiry.getStatus() != Enquiry.Status.REPLIED) {
                    String oldContent = selectedEnquiry.getContent(); // Keep a copy of the original content

                    String newContent = InputScanner.promptForString("Enter the new Enquiry content: ");
                    // String newContent = InputScanner.waitForUserInputString();

                    // Update the selected enquiry
                    Enquiry updatedSelectedEnquiry = new Enquiry(
                            selectedEnquiry.getSender(),
                            newContent,
                            selectedEnquiry.getCampName());

                    // Update the enquiry in the CSV file
                    WriteEnquiry.updateEnquiryInCSV(selectedEnquiry, updatedSelectedEnquiry, oldContent);

                    System.out.println("Enquiry edited successfully.");
                } else {
                    System.out.println("You cannot edit a replied enquiry.");
                }

                return;

            } catch (InputMismatchException e) {
                InputScanner.promptForInt("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void viewAllEnquiriesCampCommittee(CampCommittee campCommittee) {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();

        int indexCC = 1;
        int indexMem = 1;

        // Display camp committee's camp's enquiries
        System.out.println("Enquiries from Other Users on Your Camps:");
        boolean foundCCEnquiry = false;
        for (Enquiry enquiry : allEnquiries) {
            Camp camp = CampRepository.getCampByCampName(enquiry.getCampName());

            if (camp == null) {
                System.out.println("Camp not found in CampCSV");
                continue;
            }

            // Check if the CampCommittee is associated with the camp
            if (campCommittee.getCommitteeOf().equals(camp)) {
                System.out.println(indexCC + ":");
                System.out.println("Camp: " + enquiry.getCampName());
                System.out.println("Sender: " + enquiry.getSender());
                System.out.println("Content: " + enquiry.getContent());
                System.out.println("Status: " + enquiry.getStatus());
                System.out.println("===========================\n");
                indexCC++;
                foundCCEnquiry = true;
            }
        }

        if (!foundCCEnquiry) {
            System.out.println("No Enquiries!\n");
        }

        // Display the camp committee's own enquiries
        System.out.println("Your Enquiries for Other Camps:");
        List<Enquiry> campCommitteeEnquiries = EnquiryRepository.getEnquiriesBySender(campCommittee.getUserID());
        boolean foundMemEnquiry = false;
        for (Enquiry enquiry : campCommitteeEnquiries) {
            System.out.println(indexMem + ":");
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("================================");
            indexMem++;
            foundMemEnquiry = true;
        }
        if (!foundMemEnquiry) {
            System.out.println("No Enquiries!");
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
        }

        // Prompt camp committee to select an enquiry to reply
        int selectedIndex;
        do {
            selectedIndex = InputScanner
                    .promptForInt("Enter the index of the enquiry you want to reply to (0 to cancel): ");

            if (selectedIndex < 0 || selectedIndex > pendingEnquiries.size()) {
                System.out.println("Invalid index. Please enter a valid index.");
            }

        } while (selectedIndex < 0 || selectedIndex > pendingEnquiries.size());

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
            CampCommittee User = (CampCommittee) LoginManager.getCurrentUser();
            User.addPoints();
            WriteUser.FileWriteCampCommittee(); // To reflect the points in the CampComm CSV

            System.out.println("Enquiry replied successfully!");
        } else {
            System.out.println("Invalid index. No enquiry selected.");
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
