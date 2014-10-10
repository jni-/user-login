package ca.ulaval.glo4003.user_login.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void canValidateRightPassword() {
		String password = "password";
		User user = new User("email", password);
		
		assertTrue(user.validatePassword(password));
	}
	
	@Test
	public void cannotValidateWrongPassword() {
		String password = "password";
		User user = new User("email", password);
		
		assertFalse(user.validatePassword("wrong"));
	}

}