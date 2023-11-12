package Repository;

import java.util.ArrayList;

import Entity.Camp;
import Entity.CampDetails;
import Entity.User;

public class CampRepository {
    private static ArrayList<Camp> listOfCamps = new ArrayList<Camp>();

    public static ArrayList<Camp> getListOfCamps() {
        return listOfCamps;
    }

    public static int getSizeOfCamps() {
        return listOfCamps.size();
    }

    public static Camp get(int i) {
        return listOfCamps.get(i);
    }

    public static void addCampToRepo(Camp camp) {
        listOfCamps.add(camp);
    }

    public static void removeCamp(Camp camp){
        listOfCamps.remove(camp);
    }
    
    public static Camp getCampByCampName(String campName) {
        for (int j = 0; j < getSizeOfCamps(); j++) {
            Camp tempCamp = get(j);
            CampDetails tempCampDetails = tempCamp.getCampDetails();
            String tempCampName = tempCampDetails.getCampName();
            if (campName.equals(tempCampName)) {
                return tempCamp;
            }
        }
        return null;
    }
}