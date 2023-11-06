public class MyDate {
    
    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;

    public MyDate(int day, int month, int year, int hour, int min){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.min = min;
    }

    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setHour(int hour){
        this.hour = hour;
    }
    public void setMin(int min){
        this.min = min;
    }

    public void getDate(){
        System.out.printf("%2d/%02d/%03d %02d:%02d\n", day, month, year, hour, min);
    }
}
