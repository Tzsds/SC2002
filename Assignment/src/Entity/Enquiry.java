package Entity;

public class Enquiry {
    public enum Status {
        REPLIED, PENDING
    };

    private String sender;
    private Status status;
    private String content;
    private String replier;
    private String campName;
    private String repliedContent;

    public Enquiry(String sender, String content, String campName) {
        this.sender = sender;
        this.content = content;
        this.campName = campName.toUpperCase();
        this.status = Status.PENDING;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public String getReplier() {
        return replier;
    }

    public boolean hasReplied() {
        return status == Status.REPLIED;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getRepliedContent(){
        return repliedContent;
    }

    public void setRepliedContent(String repliedContent){
        this.repliedContent = repliedContent;
    }

}
