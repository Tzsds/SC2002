package Controller.Utilities;

import java.util.ArrayList;
import java.util.Collection;

import Entity.Camp;

public class Sort {
    public static ArrayList<Object> insertionSort(ArrayList<Object> campList) {
        for (int index = 1; index < campList.size(); index++) {
            Comparable key = (Comparable)campList.get(index);
            int position = index;

            while (position > 0 && key.compareTo(campList.get(position - 1)) < 0) {
                campList.set(position, campList.get(position - 1));
                position--;
            }
            campList.set(position, key);
        }
        return campList;
        
    }

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