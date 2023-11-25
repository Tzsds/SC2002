package entity;

/**
 * This class represents a suggestion made by a camp committee member
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class Suggestion {
    /** Enumerated type representing the status of the suggestion */
    public enum Status {
        /** The suggestion has been approved */
        APPROVED,
        
        /** The suggestion is pending and has not been processed yet */
        PENDING,
        
        /** The suggestion has been rejected */
        REJECTED
    };

    /** The camp committee member who made the suggestion */
    private CampCommittee proposer;
    /** The status of the suggestion (approved, pending, or rejected) */
    private Status status;
    /** The content of the suggestion. */
    private String content;

    /**
     * Constructs a new Suggestion object with the specified proposer and content
     * The status is set to {@code PENDING} by default
     *
     * @param proposer - the camp committee member who made the suggestion
     * @param content - the content of the suggestion
     */
    public Suggestion(CampCommittee proposer, String content){
        this.proposer = proposer;
        this.content = content;
        this.status = Status.PENDING;
    }

     /**
     * Sets the status of the suggestion
     * @param status - the new status of the suggestion
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * Retrieves the status of the suggestion
     * @return the status of the suggestion
     */
    public Status getStatus(){
        return status;
    }

    /**
     * Sets the content of the suggestion
     * @param status - the new content of the suggestion
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * Retrieves the content of the suggestion
     * @return the content of the suggestion
     */
    public String getContent(){
        return content;
    }

    /**
     * Retrieves the camp committee member who made the suggestion
     * @return the proposer of the suggestion
     */
    public CampCommittee getProposer(){
        return proposer;
    }

}
