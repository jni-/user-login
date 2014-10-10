package ca.ulaval.glo4003.user_login.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4003.user_login.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

	private static final String INVALID_EMAIL = "invalid@email.com";
	private static final String VALID_EMAIL = "valid@email.com";
	private static final String VALID_PASSWORD = "valid password";
	
	@Mock
	private User user;
	private UserRepository repository;

	@Before
	public void setUp() {
		given(user.getEmail()).willReturn(VALID_EMAIL);
		given(user.validatePassword(VALID_PASSWORD)).willReturn(true);
		repository = new UserRepository();
		repository.addUser(user);
	}

	@Test
	public void validatingCredentialsForAnEmailThatDoesntExistReturnsFalse() {
		assertFalse(repository.validateCredentials(INVALID_EMAIL, VALID_PASSWORD));
	}
	
	@Test
	public void validatingCredentialsForAnExistingEmailWithRightPasswordReturnsTrue() {
		assertTrue(repository.validateCredentials(VALID_EMAIL, VALID_PASSWORD));
	}
}