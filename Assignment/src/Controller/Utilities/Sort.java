package Controller.Utilities;

import java.util.ArrayList;

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
}