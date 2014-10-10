package ca.ulaval.glo4003.user_login.model;
public class User {
	
	private String email;
	private String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

}