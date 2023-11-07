import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
    
    public static void FileWrite(ArrayList<User> entity){
        String allUserCSV = "database/users.csv";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(allUserCSV))){

            //Header of CSV File
            String header = "name,role,faculty,userID,password\n";
            String role;
            writer.write(header);

            for (int i=0; i < entity.size(); i++){
                User temp = entity.get(i);
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
}
