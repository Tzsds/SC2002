package Repository;

import java.util.ArrayList;

import Entity.Camp;

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
}