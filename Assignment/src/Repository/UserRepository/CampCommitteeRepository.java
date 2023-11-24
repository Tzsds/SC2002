package Repository.UserRepository;

import java.util.ArrayList;

import Entity.CampCommittee;

public class CampCommitteeRepository {
    private static ArrayList<CampCommittee> listOfCampCommittee = new ArrayList<CampCommittee>();

    public static ArrayList<CampCommittee> getListOfCampCommittee(){
        return listOfCampCommittee;
    }

    public static void addCampCommittee(CampCommittee s){
        listOfCampCommittee.add(s);
    }

    public static CampCommittee getCommitteeByID(String id){
        for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
