package ca.ulaval.glo4003.user_login.web.view_models;
public class LoginFormViewModel {

	public String email;
	public String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}