package Entity;

public class Enquiry {
    public enum Status {
        REPLIED, PENDING
    };

    private String sender;
    private Status status;
    private String content;
    private String replier;

    public Enquiry(String sender, String content) {
        this.sender = sender;
        this.content = content;
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

}
