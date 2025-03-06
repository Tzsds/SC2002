public class Enquiry {

	private String sender;
	private String respondent;
	private String content;
	private String status;

	public String getSender() {
		return this.sender;
	}

	/**
	 * 
	 * @param sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRespondent() {
		return this.respondent;
	}

	/**
	 * 
	 * @param respondent
	 */
	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}

	public String getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}