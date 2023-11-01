import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;

public class LoadDatabase {
    public HashMap<String, String> Load(){
        String studentFilecsv = "database/student_list.csv";
        String staffFilecsv = "database/staff_list.csv";
        String outputFilecsv = "database/output.csv";
        //ArrayList <User> database = new ArrayList<User>();
        HashMap<String, String> passwordManager = new HashMap<String, String>();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilecsv))){
            try(BufferedReader br = new BufferedReader(new FileReader(staffFilecsv))){
                bw.write("userID,password,role\n");
                String line;
                br.readLine();
                while ((line = br.readLine()) != null){
                    String[] words = line.split(",");
                    if (words.length >= 3){
                        String name = words[0].trim();
                        String email = words[1].trim();
                        String userID = email.substring(0, email.indexOf("@"));
                        String faculty = words[2].trim();
                        //database.add(new Staff(userID, name, faculty));
                        //System.out.println(userID);
                        passwordManager.put(userID, "password");
                        bw.write(userID + ",password,Staff\n");
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            
            try(BufferedReader br = new BufferedReader(new FileReader(studentFilecsv))){
                String line;
                br.readLine();
                while ((line = br.readLine()) != null){
                    String[] words = line.split(",");
                    if (words.length >= 3){
                        String name = words[0].trim();
                        String email = words[1].trim();
                        String userID = email.substring(0, email.indexOf("@"));
                        String faculty = words[2].trim();
                        //database.add(new Staff(userID, name, faculty));
                        //System.out.println(userID);
                        passwordManager.put(userID, "password");
                        bw.write(userID + ",password,Student\n");
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } 

        return passwordManager;
    }
}
