package Entity;

import Controller.Account.LoginManager;

public class Suggestion {
    public enum Status {APPROVED, PENDING, REJECTED};

    private CampCommittee proposer;
    private Status status;
    private String content;

    public Suggestion(String content){
        this.proposer = (CampCommittee)LoginManager.getCurrentUser();
        this.content = content;
        this.status = Status.PENDING;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Status geStatus(){
        return status;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public CampCommittee getProposer(){
        return proposer;
    }

}
