package gov.mo.dolir.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.mo.dolir.models.UserModel;
import gov.mo.dolir.services.UserService;
import gov.mo.dolir.validators.UserValidator;


@Controller
@RequestMapping(value="/users")
public class UserController {

private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value="/list")
	public String getUserList(ModelMap map) {
		map.addAttribute("users", userService.getUsersList());
		return "users.list";
	}

	@RequestMapping(value="/view/{id}")
	public String displayUser(@PathVariable String id, ModelMap map) {
		map.addAttribute("user", userService.getUsers(new Integer(id)));
		return "users.view";
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editUserForm(@PathVariable String id, ModelMap map) {
		map.addAttribute("usersForm", userService.getUsers(new Integer(id)));
		return "users.edit";
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String changeUserForm(@ModelAttribute("usersForm") UserModel user, BindingResult result, ModelMap map, HttpServletRequest request) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			map.addAttribute("errMsg", "Please fix belows errors before submitting.");
			map.addAttribute("usersForm", user);
			return "users.edit";
		} else {
			user.setModifiedBy(request.getUserPrincipal().getName());
			userService.updateUsers(user);
			return "redirect:/users/list";
		}
	}
	
	@RequestMapping(value="/changePasswd/{username}", method=RequestMethod.GET)
	public String changePassword(@PathVariable String username, ModelMap map) {
		map.addAttribute("usersForm", userService.getUserByUsername(username));
		return "users.changePasswd";
	}
	
	@RequestMapping(value="/changePasswd", method=RequestMethod.POST)
	public String savePasswordChanges(@ModelAttribute("usersForm") UserModel user, BindingResult result, ModelMap map, HttpServletRequest request) {
		userValidator.validatePasswordChange(user, result);
		if (!userService.validateUser(user.getUsername(), user.getOldPassword())) {
			result.rejectValue("oldPassword", "required", null, "Invalid password.");
		}
		if (result.hasErrors()) {
			map.addAttribute("errMsg", "Please fix belows errors before submitting.");
			map.addAttribute("usersForm", user);
			return "users.changePasswd";
		} else {
			userService.updatePassword(user.getUsername(), user.getPassword(),request.getUserPrincipal().getName());
			if (request.getUserPrincipal().getName().equals(user.getUsername())) {
				return "redirect:/users/profile";
			} else {
				return "redirect:/users/view/"+user.getId();
			}
		}
	}

	
	@RequestMapping(value="/profile")
	public String getProfile(ModelMap map, HttpServletRequest request) {
		map.addAttribute("user", userService.getUserByUsername(request.getUserPrincipal().getName()));
		return "users.profile";
	}

	@RequestMapping(value="/editProfile", method=RequestMethod.GET)
	public String editProfile(ModelMap map, HttpServletRequest request) {
		map.addAttribute("usersForm", userService.getUserByUsername(request.getUserPrincipal().getName()));
		return "users.profile.edit";
	}

	@RequestMapping(value="/editProfile", method=RequestMethod.POST)
	public String saveProfileChanges(@ModelAttribute("usersForm") UserModel user, BindingResult result, ModelMap map, HttpServletRequest request) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			map.addAttribute("errMsg", "Please fix belows errors before submitting.");
			map.addAttribute("usersForm", user);
			return "users.profile.edit";
		} else {
			user.setModifiedBy(request.getUserPrincipal().getName());
			userService.updateProfile(user);
			return "redirect:/users/profile";
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String createUser(ModelMap map) {
		map.addAttribute("usersForm", new UserModel());
		return "users.register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveUsersForm(@ModelAttribute("usersForm") UserModel user, BindingResult result, ModelMap map, HttpServletRequest request) {
		userValidator.validateCreation(user, result);
		if(userService.getUserByUsername(user.getUsername())!=null) {
			result.rejectValue("username", "required", null, "This username is already taken.");
		}
		if (result.hasErrors()) {
			System.out.println("result has errors: " + result.getFieldErrors());
			map.addAttribute("errMsg", "Please fix belows errors before submitting.");
			map.addAttribute("usersForm", user);
			return "users.register";
		} else {
//			user.setCreatedBy(request.getUserPrincipal().getName());
//			userService.adduser(user);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/remove/{id}")
	public String removeUser(@PathVariable String id) {
		userService.deleteUsers(new Integer(id));
		return "redirect:/users/list";
	}	
}
