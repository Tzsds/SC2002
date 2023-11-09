package Entity;

public class Suggestion {
    public enum Status {APPROVED, PENDING, REJECTED};

    private String proposer;
    private Status status;
    private String content;

    public Suggestion(String proposer, String content){
        this.proposer = proposer;
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

    public String getProposer(){
        return proposer;
    }

}
