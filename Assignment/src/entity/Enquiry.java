package entity;

/**
 * This class represents an enquiry made by a user towards a certain camp
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class Enquiry {
     /** Status of the Enquiry */
    public enum Status {
        /** The enquiry has been replied to */
        REPLIED,
        
        /** The enquiry is pending and has not been replied to yet */
        PENDING
    };

    /** The sender of the enquiry */
    private String sender;
    /** The status of the enquiry (replied or pending) */
    private Status status;
     /** The content of the enquiry */
    private String content;
    /** The replier who replied to the enquiry */
    private String replier;
    /** The name of the camp related to the enquiry */
    private String campName;
    /** The content of the reply to the enquiry (if replied) */
    private String repliedContent;


    /**
     * Constructs a new Enquiry object with the specified sender, content, and camp name
     *
     * @param sender - the sender of the enquiry
     * @param content - the content of the enquiry
     * @param campName - the name of the camp related to the enquiry
     * @param status - the status of the enquiry default will be set to PENDING
     */
    public Enquiry(String sender, String content, String campName) {
        this.sender = sender;
        this.content = content;
        this.campName = campName;
        this.status = Status.PENDING;
    }

     /**
     * Sets the status of the enquiry
     * @param status - the new status of the enquiry
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retrieves the status of the enquiry
     * @return the status of the enquiry
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the content of the enquiry
     * @param content - the new content of the enquiry
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the content of the enquiry
     * @return the content of the enquiry
     */
    public String getContent() {
        return content;
    }

    /**
     * Retrieves the sender of the enquiry
     * @return the sender of the enquiry
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the replier of the enquiry
     * @param replier - the new replier of the enquiry
     */
    public void setReplier(String replier) {
        this.replier = replier;
    }

    /**
     * Retrieves the replier of the enquiry
     * @return the replier of the enquiry
     */
    public String getReplier() {
        return replier;
    }

    /**
     * Checks whether an enquiry has been replied or not
     * @return {@code true} if the enquiry has been replied, {@code false} otherwise
     */
    public boolean hasReplied() {
        return status == Status.REPLIED;
    }

    /**
     * Retrieves the camp name of the enquiry
     * @return the camp name of the enquiry
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Sets the camp name of the enquiry
     * @param campName - the new camp name of the enquiry
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Retrieves the replied content of the enquiry
     * @return the replied content of the enquiry
     */
    public String getRepliedContent(){
        return repliedContent;
    }

    /**
     * Sets the replied content of the enquiry
     * @param repliedContent - the new replied content of the enquiry
     */
    public void setRepliedContent(String repliedContent){
        this.repliedContent = repliedContent;
    }

}
