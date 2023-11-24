package repos.userrepository;

import java.util.ArrayList;

import entity.Staff;

public class StaffRepository {
    private static ArrayList<Staff> listOfStaff = new ArrayList<Staff>();
    
    public static ArrayList<Staff> getListOfStaff(){
        return listOfStaff;
    }

    public static void addStaff(Staff s){
        listOfStaff.add(s);
    }

    public static Staff getStaffByID(String id){
        for (Staff s : listOfStaff){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
