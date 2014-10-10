package ca.ulaval.glo4003.user_login.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.user_login.model.User;

@Repository
public class UserRepository {
	
	private Map<String, User> users = new HashMap<String, User>();

	public boolean validateCredentials(String email, String password) {
		User user = users.get(email);
		if(user == null) {
			return false;
		}
		return user.validatePassword(password);
	}

	public void addUser(User user) {
		users.put(user.getEmail(), user);
	}

}