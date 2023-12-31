package com.microblogging.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microblogging.dao.User;
import com.microblogging.service.UsersService;

@Controller
public class LoginController {
	
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		
		return "login";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "createaccount";
	}
	
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount( User user, BindingResult result) {
	
		usersService.create(user);
	
		
		return "accountcreated";
	}

}