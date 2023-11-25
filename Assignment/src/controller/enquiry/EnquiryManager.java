package controller.enquiry;

import java.util.InputMismatchException;
import java.util.List;

import controller.account.LoginManager;
import controller.file.enquiry.WriteEnquiry;
import controller.utils.InputScanner;
import entity.Camp;
import entity.Enquiry;
import entity.Student;
import entity.User;
import repository.CampRepository;
import repository.EnquiryRepository;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;

public class EnquiryManager {
    public static Enquiry createEnquiry(Student student) {

        System.out.println("Which Camp do you want to enquiry on?");

        List<Camp> allAvailCamps = CampRepository.getAvailableCampsForStudent(student);

        for (int i = 0; i < allAvailCamps.size(); i++) {
            System.out.println((i + 1) + ": " + allAvailCamps.get(i).getCampDetails().getCampName());
        }

        // Prompt for camp index
        int campIndex = InputScanner.promptForInt("Choose a camp (Enter the index, 0 to cancel): ");

        while (campIndex == -1 || campIndex < 1 || campIndex > allAvailCamps.size()) {
            if (campIndex == 0) {
                return null;
            } else if (campIndex < 1 || campIndex > allAvailCamps.size()) {
                System.out.println("Invalid index. Please choose a valid index.");
                campIndex = InputScanner.promptForInt("Choose a camp (Enter the index, 0 to cancel): ");
            }
        }

        // Get the selected camp name based on the index
        String campChoice = allAvailCamps.get(campIndex - 1).getCampDetails().getCampName();

        String content = InputScanner.promptForString("What enquiries do you have?: ");
        String sender = LoginManager.getCurrentUser().getUserID();
        System.out.println("This is the content written: " + content);

        return new Enquiry(sender, content, campChoice);
    }

    public static void addEnquiry() {
        Student User = (Student) LoginManager.getCurrentUser();
        Enquiry enquiry = createEnquiry(User);

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

    public static void deleteEnquiry(User currentUser) {
        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());

        if (studentEnquiries.isEmpty()) {
            System.out.println("No enquiries to remove!");
            return;
        }

        System.out.println("Your Enquiries:");
        viewStudentEnquiries(currentUser);

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

    public static void editEnquiry(User currentUser) {

        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());

        if (studentEnquiries.isEmpty()) {
            System.out.println("No enquiries to edit!");
            return;
        }

        System.out.println("Your Enquiries:");
        viewStudentEnquiries(currentUser);

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

    public static void viewStudentEnquiries(User currentUser) {
        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());
        if (studentEnquiries.size() == 0) {
            System.out.println("No Enquiries!");
        }
        for (int i = 0; i < studentEnquiries.size(); i++) {
            System.out.println((i + 1) + ":");
            Enquiry enquiry = studentEnquiries.get(i);
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());

            // Check if the status is "REPLIED"
            if (enquiry.getStatus() == Enquiry.Status.REPLIED) {
                User sender = CampCommitteeRepository.getCommitteeByID(enquiry.getReplier());
                if (sender == null) {
                    sender = StaffRepository.getStaffByID(enquiry.getReplier());
                }
                System.out.println("Reply: " + enquiry.getRepliedContent());
                System.out.println("Replier: " + sender.getName());
            }
            System.out.println("================================");
        }
    }

}
