package ca.ulaval.glo4003.user_login.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ca.ulaval.glo4003.user_login.model.User;
import ca.ulaval.glo4003.user_login.persistence.UserRepository;
import ca.ulaval.glo4003.user_login.web.view_models.LoginFormViewModel;

@Controller
@SessionAttributes("email")
public class HomeController 
{
	private Logger logger = Logger.getLogger(HomeController.class);
	private UserRepository repository;

	@Autowired
	public HomeController(UserRepository repository) {
		this.repository = repository;
		repository.addUser(new User("test@test.com", "1234"));
	}
	
	@ModelAttribute("loginForm")
	public LoginFormViewModel defaultUser() {
		return new LoginFormViewModel();
	}

	@RequestMapping("/")
	public String displayLoginForm() {
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(LoginFormViewModel form) {
		if(repository.validateCredentials(form.email, form.password)) {
			logger.info("User " + form.email + " successfully logged in");
			ModelAndView homeRedirection = redirectHome();
			homeRedirection.addObject("email", form.email);
			return homeRedirection;
		}
		logger.error("User login failed for " + form.email);
		
		ModelAndView homeView = new ModelAndView("home");
		homeView.addObject("alert", "Email and/or password invalid");
		homeView.addObject("loginForm", form);
		return homeView;
	}

	private ModelAndView redirectHome() {
		RedirectView redirect = new RedirectView("/");
		redirect.setExposeModelAttributes(false);
		return new ModelAndView(redirect);
	}
	
	@RequestMapping(value = "/logout") 
	public String logout(SessionStatus sessionStatus, ModelMap model) {
		sessionStatus.setComplete();
		model.clear();
		return "redirect:/";
	}
}
