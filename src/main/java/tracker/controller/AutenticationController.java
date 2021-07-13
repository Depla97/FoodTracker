package tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tracker.model.entities.Role;
import tracker.model.entities.User;
import tracker.service.UserService;

@Controller
public class AutenticationController {
	@Autowired
	String appName;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login (Model model) {
		
		model.addAttribute("appName", appName);
		return "login";
		
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String register (Model model) {
		
		model.addAttribute("newUser", new User());
		return "registerForm";
		
	}
	
	@PostMapping(value = "/registerUser")
	public String submitRegistration (@ModelAttribute("newUser") User newUser ) {
		
		newUser.setEnabled(true);
		Role r = this.userService.createRole("user");
		this.userService.create(newUser.getUsername(),newUser.getPassword(),newUser.isEnabled());
		newUser.addRole(r);
		this.userService.encryptPassword(newUser);
		this.userService.update(newUser);
		return "redirect:/";
		
	}
	
	/*@PostMapping(value="/createRole")
	public String createRole(@ModelAttribute("roleName") String roleName)
	{
		this.userService.createRole(roleName);
		return "redirect:/";
	}*/
}
