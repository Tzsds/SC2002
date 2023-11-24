package Controller.Utilities;

import java.util.ArrayList;

import entities.Camp;

public class Sort {
    public static ArrayList<Camp> insertionSortByName(ArrayList<Camp> campList){
         for (int i = 1; i < campList.size(); i++){
            for (int j = i; j > 0; j--){
                Camp temp = campList.get(j);
                Camp temp1 = campList.get(j-1);
                String tempName = temp.getCampDetails().getCampName();
                String temp1Name = temp1.getCampDetails().getCampName();
                if (tempName.compareToIgnoreCase(temp1Name) < 0) {
                    campList.set(j, temp1);
                    campList.set(j - 1, temp);
                }
                else{
                    break;
                }
            }
        }
        return campList;
    }
}