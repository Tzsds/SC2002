public class User implements LoginInterface {

	private String userID;
	private String password = password;
	private String faculty;
	private String name;

	public String getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFaculty() {
		return this.faculty;
	}

	/**
	 * 
	 * @param faculty
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void login() {
		// TODO - implement User.login
		throw new UnsupportedOperationException();
	}

	public void changePassword() {
		// TODO - implement User.changePassword
		throw new UnsupportedOperationException();
	}

}