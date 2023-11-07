import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
    
    public static void FileWrite(){
        String allUserCSV = "database/users.csv";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(allUserCSV))){

            //Header of CSV File
            String header = "name,role,faculty,userID,password\n";
            String role;
            writer.write(header);

            for (int i=0; i < UserRepository.getSizeOfUsers(); i++){
                User temp = UserRepository.get(i);
                if (temp instanceof Staff){
                    role = "Staff";
                }
                else if (temp instanceof CampCommitteeMem){
                    role = "CampCommitteeMem";
                }
                else{
                    role = "Student";
                }
                String data = temp.getName() + "," + role + "," + temp.getFaculty() + "," + temp.getUserID() + "," + temp.getPassword() + "\n";
                writer.write(data);
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void FileWrite(CampDetails campDetails) {
        String campDetailsCSV = "Assignment//database//camp_details.csv"; 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(campDetailsCSV))){

            String header = "campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n";
            writer.write(header);

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
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void FileWrite(Camp camp) {
        String campDetailsCSV = "Assignment//database//camp_student_list.csv"; 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(campDetailsCSV))){

            String header = "campName,userID,Role\n";
            writer.write(header);

            // Write campDetails to the CSV file
            CampDetails campDetails = camp.getCampDetails();
            String campName = campDetails.getCampName();
            ArrayList<Student> attendeesList = camp.getParticipants();
            ArrayList<Student> committeeList = camp.getCampComittee();

            if (attendeesList != null) {
                for (int i=0; i < attendeesList.size(); i++){
                    User user = attendeesList.get(i);
                    String data = campName + "," + user.getUserID() + "," + "Attendee" + "\n";
                    writer.write(data);
                }
            }

            if (committeeList != null) {
                for (int i=0; i < committeeList.size(); i++){
                    User user = committeeList.get(i);
                    String data = campName + "," + user.getUserID() + "," + "Committee" + "\n";
                    writer.write(data);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}