package controller.utils;
import java.util.ArrayList;
import entity.Camp;

/**
 * Utility class for sorting camps.
 * Provides methods to perform sorting operations on lists of camps.
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class Sort {

    /**
     * Performs insertion sort on a list of camps based on their camp names.
     * 
     * @param campList - The ArrayList of camps to be sorted.
     * @return The sorted ArrayList of camps based on camp names.
     */

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