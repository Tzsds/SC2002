import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {

    public static ArrayList<User> read(int reset){
        if (reset == 1){
            return readWithReset();
        }
        else{
            return readWithoutReset();
        }
    }

    private static ArrayList<User> readWithReset(){
        ArrayList <User> entity = new ArrayList<User>();
        String studentCSV = "database/student_list.csv";
        String staffCSV = "database/staff_list.csv";

        //Reading in the studentCSV
        try (BufferedReader br = new BufferedReader(new FileReader(studentCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String name = words[0].trim();
                    String email = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = email.substring(0, email.indexOf("@"));
                    entity.add(new Student(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Reading in the staffCSV
        try (BufferedReader br = new BufferedReader(new FileReader(staffCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String name = words[0].trim();
                    String email = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = email.substring(0, email.indexOf("@"));
                    entity.add(new Staff(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return entity;
    
    }

    private static ArrayList<User> readWithoutReset(){
        ArrayList <User> entity = new ArrayList<User>();
        String allUserCSV = "database/users.csv";

        //Reading in the CSV that has all the updated passwords
        try (BufferedReader br = new BufferedReader(new FileReader(allUserCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 5){
                    String name = words[0].trim();
                    String role = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = words[3].trim();
                    String password = words[4].trim();
                    if (role.equals("Staff")){
                        entity.add(new Staff(userID, name, faculty, password));
                    }
                    else if (role.equals("CampCommitteeMem")){
                        entity.add(new CampCommitteeMem(userID, name, faculty, password));
                    }
                    else{
                        entity.add(new Student(userID, name, faculty, password));
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return entity;

    }
}
