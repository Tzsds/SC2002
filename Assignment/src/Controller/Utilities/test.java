// this file is solely for debug purposes

package Controller.Utilities;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        
        LocalDate future = LocalDate.of(4000, 11, 18); // Year, Month, Day
        LocalDate past = LocalDate.of(1000, 4, 18); // Year, Month, Day
        LocalDate now = LocalDate.now();

        boolean comparisonResult = past.isAfter(future);
        System.out.println(comparisonResult);
    }
}