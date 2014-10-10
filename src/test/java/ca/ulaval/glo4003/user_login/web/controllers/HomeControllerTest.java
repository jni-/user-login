package ca.ulaval.glo4003.user_login.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ca.ulaval.glo4003.user_login.persistence.UserRepository;
import ca.ulaval.glo4003.user_login.web.view_models.LoginFormViewModel;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	private static final String VALID_PASSWORD = "valid password";
	private static final String VALID_EMAIL = "valid@email.com";

	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private HomeController controller;

	private SessionStatus status = new SimpleSessionStatus();
	
	@Before
	public void setUpAccounts() {
		given(repository.validateCredentials(VALID_EMAIL, VALID_PASSWORD)).willReturn(true);
	}
	
	@Test
	public void displayLoginFormReturnsLoginForm() {
		String response = controller.displayLoginForm();
		
		assertEquals("home", response);
	}
	
	@Test
	public void loginReturnsLoginFormIfCannotLogin() {
		ModelAndView response = controller.login(getInvalidForm());
		
		assertEquals("home", response.getViewName());
	}
	
	@Test
	public void loginAddsAlertIfCannotLogin() {
		ModelAndView model = controller.login(getInvalidForm());
		
		assertEquals("Email and/or password invalid", model.getModel().get("alert"));
	}
	
	@Test
	public void loginAddsEmailToSessionIfLoginSuccessful() {
		ModelAndView model = controller.login(getValidForm());
		
		assertEquals(VALID_EMAIL, model.getModel().get("email"));
	}
	
	@Test
	public void loginRedirectsToHomeIfLoginSuccessful() {
		ModelAndView response = controller.login(getValidForm());
		
		assertEquals("/", ((RedirectView) response.getView()).getUrl());
	}
	
	@Test
	public void logoutClearsTheModelMap() {
		ModelMap model = new ModelMap();
		model.addAttribute("junk", "junk");
		controller.logout(status, model);
		
		assertTrue(model.isEmpty());
	}
	
	@Test
	public void logoutTerminatesSession() {
		controller.logout(status, new ModelMap());
		
		assertTrue(status.isComplete());
	}
	
	private LoginFormViewModel getValidForm() {
		LoginFormViewModel form = new LoginFormViewModel();
		form.email = VALID_EMAIL;
		form.password = VALID_PASSWORD;
		return form;
	}
	
	private LoginFormViewModel getInvalidForm() {
		return new LoginFormViewModel();
	}
}