
// this file is solely for debug purposes

package Controller.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import Entity.Camp;
import Repository.CampRepository;

public class localDateTest {

    // this code is working

    // public static void main(String[] args) {
    //     ArrayList<LocalDate> array = new ArrayList<>();

    //     LocalDate test = LocalDate.of(1000, 11, 18); // Year, Month, Day

    //     LocalDate future = LocalDate.of(2024, 11, 18); // Year, Month, Day
    //     LocalDate past = LocalDate.of(2024, 4, 18); // Year, Month, Day
    //     LocalDate now = LocalDate.now();

    //     array.add(future);
    //     array.add(test);
    //     array.add(now);
    //     array.add(past);
    //     array.add(LocalDate.now());

    //     // Convert ArrayList to array, sort, and then update the ArrayList with sorted elements
    //     LocalDate[] sortedArray = array.toArray(new LocalDate[0]);
    //     insertionSort(sortedArray);
    //     array.clear();
    //     array.addAll(Arrays.asList(sortedArray));

    //     for (LocalDate a : array) {
    //         System.out.print(a + " ");
    //     }
    // }

    public static void localDateTest() {

        ArrayList<Camp> array = CampRepository.getListOfCamps();

        System.out.println(array.size());

        // ArrayList<Camp> array = new ArrayList<>();

        // LocalDate test = LocalDate.of(1000, 11, 18); // Year, Month, Day

        // LocalDate future = LocalDate.of(2024, 11, 18); // Year, Month, Day
        // LocalDate past = LocalDate.of(2024, 4, 18); // Year, Month, Day
        // LocalDate now = LocalDate.now();

        // array.add(future);
        // array.add(test);
        // array.add(now);
        // array.add(past);
        // array.add(LocalDate.now());

        // Convert ArrayList to array, sort, and then update the ArrayList with sorted elements
        Camp[] sortedArray = array.toArray(new Camp[10]);
        insertionSort(sortedArray);

        System.out.println(sortedArray);
        array.clear();
        array.addAll(Arrays.asList(sortedArray));
        System.out.println("AA");

        for (Camp a : array) {
            System.out.print("S");
            System.out.print(a.getCampDetails().getStartDate() + " ");
        }
    }

    public static void insertionSort(Comparable[] list) {
        for (int index = 1; index < list.length; index++) {
            Comparable key = list[index];
            int position = index;

            while (position > 0 && key.compareTo(list[position - 1]) < 0) {
                list[position] = list[position - 1];
                position--;
            }
            list[position] = key;
        }
    }
}







