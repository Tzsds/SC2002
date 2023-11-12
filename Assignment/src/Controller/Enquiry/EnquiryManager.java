package Controller.Enquiry;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Controller.Account.LoginManager;
import Controller.File.Enquiry.ReadEnquiry;
import Controller.File.Enquiry.WriteEnquiry;
import Entity.CampDetails;
import Entity.Enquiry;
import Entity.Student;
import Entity.User;
import Repository.EnquiryRepository;

import UI.InputScanner;

public class EnquiryManager {
    public static Enquiry createEnquiry(Student student) {
        System.out.println("Which Camp do you want to enquiry on?");
        Student.viewAvailableCamps();
        InputScanner.promptForString("Choose a camp(Enter Camp Name)");
        String campChoice = InputScanner.waitForUserInputString();
        // Validate if the chosen camp name exists in the available camps
        if (!CampDetails.campExists(campChoice)) {
            System.out.println("Invalid camp name. Please choose a valid camp.");
            return null; // Return null to indicate an error
        }
        String content = InputScanner.promptForString("What enquiries do you have?");
        String sender = LoginManager.getCurrentUser().getUserID();
        System.out.println("This is the content written: " + content);
        return new Enquiry(sender, content, campChoice);
    }

    public static void addEnquiry() {
        Student User = (Student) LoginManager.getCurrentUser();
        Enquiry enquiry = createEnquiry(User);
        User.getEnquiries().add(enquiry);
        EnquiryRepository.addEnquiryToRepo(enquiry);

        // Write the new enquiry to CSV
        WriteEnquiry.FileWriteEnquiry(enquiry);
        System.out.println("Enquiry added successfully!");
    }

    public static void deleteEnquiry(User currentUser) {
        String enquiryCSV = "Assignment/database/enquiries.csv";

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
                System.out.println("Invalid input! Please enter a valid number.");
                InputScanner.waitForUserInput();
            }
        }
    }

    /*
     * public static void editEnquiry(User currentUser) {
     * List<Enquiry> studentEnquiries =
     * EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());
     * 
     * if (studentEnquiries.isEmpty()) {
     * System.out.println("No enquiries to edit!");
     * return;
     * }
     * 
     * // Display student's enquiries
     * System.out.println("Your Enquiries:");
     * viewStudentEnquiries(currentUser);
     * 
     * // Choose which enquiry to edit
     * int choice = InputScanner.
     * promptForInt("Enter the number of the enquiry to edit (0 to cancel): ");
     * 
     * if (choice < 0 || choice > studentEnquiries.size()) {
     * System.out.println("Invalid choice. Editing cancelled.");
     * return;
     * } else if (choice == 0) {
     * System.out.println("Editing cancelled.");
     * return;
     * }
     * 
     * // Get the selected enquiry
     * Enquiry selectedEnquiry = studentEnquiries.get(choice - 1);
     * 
     * // Display current content and prompt for new content
     * System.out.println("Current Content: " + selectedEnquiry.getContent());
     * InputScanner.promptForString("Enter the new Enquiry content: ");
     * String newContent = InputScanner.waitForUserInputString();
     * // Update the selected enquiry
     * selectedEnquiry.setContent(newContent);
     * System.out.println(newContent);
     * 
     * // Update the enquiry in the repository
     * // EnquiryRepository.setE(selectedEnquiry);
     * // Read the enquiries from the CSV file.
     * ArrayList<Enquiry> enquiriesFromCSV = ReadEnquiry.readEnquiriesFromCSV();
     * 
     * // Edit the enquiries and write them back to the CSV file.
     * // WriteEnquiry.editEnquiries(studentEnquiries, enquiriesFromCSV);
     * 
     * System.out.println("Enquiry edited successfully!");
     * }
     */

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
                    // Display current content and prompt for new content
                    System.out.println("Current Content: " + selectedEnquiry.getContent());
                    String oldContent = selectedEnquiry.getContent(); // Keep a copy of the original content
                    System.out.println("Old Content: " + oldContent);

                    InputScanner.promptForString("Enter the new Enquiry content: ");
                    String newContent = InputScanner.waitForUserInputString();

                    // Update the selected enquiry
                    Enquiry updatedSelectedEnquiry = new Enquiry(
                            selectedEnquiry.getSender(),
                            newContent,
                            selectedEnquiry.getCampName());
                    System.out.println("New Content: " + newContent);

                    // Update the enquiry in the CSV file
                    WriteEnquiry.updateEnquiryInCSV(selectedEnquiry, updatedSelectedEnquiry, oldContent);

                    System.out.println("Enquiry edited successfully.");
                } else {
                    System.out.println("You cannot edit a replied enquiry.");
                }

                return;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                InputScanner.waitForUserInput();
            }
        }
    }

    public static void viewStudentEnquiries(User currentUser) {
        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());
        for (int i = 0; i < studentEnquiries.size(); i++) {
            System.out.println((i + 1) + ":");
            Enquiry enquiry = studentEnquiries.get(i);
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
        }
    }

    // View all enquiries for staff
    public static void viewAllEnquiries() {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();
        System.out.println("Number of enquiries: " + allEnquiries.size());
        for (Enquiry enquiry : allEnquiries) {
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Sender: " + enquiry.getSender());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
        }
    }
}
