package repos;

import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Student;

public class CampRepository {
    private static ArrayList<Camp> listOfCamps = new ArrayList<Camp>();

    public static ArrayList<Camp> getListOfCamps() {
        return listOfCamps;
    }

    public static void setListOfCamps(ArrayList<Camp> newListOfCamps){
        listOfCamps = newListOfCamps;
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

    public static void removeCamp(Camp camp) {
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

    public static ArrayList<Camp> getAvailableCampsForStudent(Student student) {
        ArrayList<Camp> availableCamps = new ArrayList<>();

        for (Camp camp : listOfCamps) {
            CampDetails campDetails = camp.getCampDetails();

            if (!campDetails.getVisibility()) {
                continue;
            } else {
                if (campDetails.getUserGroup().equals("Everyone")
                        || (campDetails.getUserGroup().equals(student.getFaculty())
                                && (campDetails.getTotalSlots() > 0 || campDetails.getCampCommitteeSlots() > 0))) {
                    availableCamps.add(camp);
                }
            }
        }

        return availableCamps;
    }
}