public class CampDetails {
    
    private String campname;
    private MyDate startDate;
    private MyDate endDate;
    private MyDate closeDate;
    private String openTo;
    private String location;
    private int slots;
    private int campComitteeSlots;
    private String description;
    private ArrayList<Staff> staffInCharge;
    private boolean visibility;

    public CampDetails(String campname, MyDate startDate, MyDate endDate, MyDate closeDate, String openTo, String location, int slots, int campComitteeSlots,
     String description, ArrayList<Staff> staffInCharge, boolean visibility){
        this.campname = campname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closeDate = closeDate;
        this.openTo = openTo;
        this. location = location;
        this.slots = slots;
        this.campComitteeSlots = campComitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
    }

    public void setCampName(String campname){
        this.campname = campname;
    }
    public String getCampName(){
        return campname;
    }

    //public void setStartDate(Date startDate){
        //this.startDate = startDate;
    //}
    public void getStartDate(){
        System.out.println("Start Date: ");
        startDate.getDate();
    }

    //public void setEndDate(Date endDate){
        //this.endDate = endDate;
    //}
    public void getEndDate(){
        System.out.println("End Date: ");
        EndDate.getDate();
    }

    //public void setCloseDate(Date closeDate){
        //this.CloseDate = closeDate;
    //}
    public void getCloseDate(){
        System.out.println("Close Date: ");
        closeDate.getDate();
    }

    public void setOpenTo(String openTo){
        this.openTo = openTo;
    }
    public String getOpenTo(){
        return openTo;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public void setSlot(int slot){
        this.slot = slot;
    }
    public int getSlot(){
        return slot;
    }

    public void setCampComitteeSlots(int campComitteeSlots){
        this.campComitteeSlots = campComitteeSlots;
    }
    public int getCampComitteeSlots(){
        return campComitteeSlots;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

}
