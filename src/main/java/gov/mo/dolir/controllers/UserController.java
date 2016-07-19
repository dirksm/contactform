package gov.mo.dolir.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.mo.dolir.constants.AppConstants;
import gov.mo.dolir.models.PasswordResetModel;
import gov.mo.dolir.models.UserModel;
import gov.mo.dolir.services.UserService;
import gov.mo.dolir.util.AppUtil;
import gov.mo.dolir.util.DateUtil;
import gov.mo.dolir.util.MailUtils;
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
	
	@RequestMapping(value="/changePasswd", method=RequestMethod.GET)
	public String changeUserPassword(ModelMap map, HttpServletRequest request) {
		map.addAttribute("usersForm", userService.getUserByUsername(request.getRemoteUser()));
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
			map.addAttribute("errMsg", "Please fix below errors before submitting.");
			map.addAttribute("usersForm", user);
			return "users.register";
		} else {
			user.setCreatedBy(user.getUsername());
			userService.adduser(user);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/remove/{id}")
	public String removeUser(@PathVariable String id) {
		userService.deleteUsers(new Integer(id));
		return "redirect:/users/list";
	}	
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
	public String forgotPasswordForm(ModelMap map) {
		map.addAttribute("usersForm", new UserModel());
		return "user.forgotPassword";
	}

	@RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
	public String sendForgotPasswordEmail(@RequestParam("email") String email, ModelMap map, HttpServletRequest request) {
		if (StringUtils.isBlank(email)) {
			map.addAttribute("msg", "Email address is required.");
			return "user.forgotPassword";
		} else if (!AppUtil.isValidEmail(email)) {
			map.addAttribute("msg", "Not a valid email address.");
			return "user.forgotPassword";
		}
		UserModel user = userService.getUserByEmail(email);
		if (user == null) {
			map.addAttribute("msg", "Email address not found.");
			return "user.forgotPassword";
		} else {
			String key = userService.setPasswordResetKey(user.getUsername());
			if (StringUtils.isNotBlank(key)) {
				try {
					String headerURL = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("/users/forgotPassword"));
					InternetAddress to = new InternetAddress(user.getEmail());
					InternetAddress from = new InternetAddress(AppConstants.FROM_EMAIL, AppConstants.FROM_NAME);
					MailUtils.sendMail(to, from, AppConstants.FORGOT_PASSWORD_SUBJECT, generateLostPasswordHtmlMsg(headerURL, user.getId(), key), generateLostPasswordTextMsg(headerURL, user.getId(), key, user.getFirstName()));
					return "user.emailText";
				} catch (UnsupportedEncodingException | AddressException e) {
					log.error("UnsupportedEncodingException | Address Exception sending email: " + e.getMessage(), e);
					map.addAttribute("msg", "There was an error resetting your password. Please try again.");
					return "user.forgotPassword";
				}
			} else {
				map.addAttribute("msg", "There was an error resetting your password. Please try again.");
				return "user.forgotPassword";
			}
		}
	}
	
	@RequestMapping(value="/resetPassword/{id}/{key}", method=RequestMethod.GET)
	public String getResetPasswordForm(@PathVariable String id, @PathVariable String key, Model map) {
		PasswordResetModel prm = new PasswordResetModel();
		prm.setId(new Integer(id));
		prm.setKey(key);
		boolean resetKeyValid = isResetKeyValid(new Integer(id), key);
		if (resetKeyValid) {
			map.addAttribute("prmForm", prm);
			return "user.resetPassword";
		} else {
			return "user.passwdResetInvalid";
		}
	}

	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public String resetPassword(@ModelAttribute("prmForm") PasswordResetModel prm, BindingResult result, Model map) {
		boolean resetKeyValid = isResetKeyValid(prm.getId(), prm.getKey());
		if (resetKeyValid) {
			if (prm.passwordsMatch()) {
				PasswordResetModel existing = userService.getResetInfo(prm.getId());
				userService.resetPassword(prm.getId(), existing.getUsername(), prm.getPassword());
				return "redirect:/";
			} else {
				map.addAttribute("errMsg", "Passwords don't match.");
				map.addAttribute("prmForm", prm);
				return "user.resetPassword";
			}
		} else {
			return "user.passwdResetInvalid";
		}
	}
	
	/**
	 * Check to see if the key matches for the user id and that the request date is not older than 14 days
	 * @param id user Id
	 * @param key generated key
	 * @return true if valid; false otherwise
	 */
	private boolean isResetKeyValid(Integer id, String key) {
		PasswordResetModel existing = userService.getResetInfo(new Integer(id));
		Date now = new Date();
		boolean isValid = false;
		if (existing != null) {
			Date expirationDate = DateUtil.addDays(existing.getRequestDate(), 14);
			if (StringUtils.isNotBlank(key) && key.equals(existing.getKey()) && expirationDate!=null && now.compareTo(expirationDate) < 0) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public String sendEmail(Model map, HttpServletRequest request) {
		UserModel user = userService.getUserByEmail("dirksm@gmail.com");
		String key = userService.setPasswordResetKey(user.getUsername());
		try {
			String headerURL = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("/auth/email"));
			InternetAddress to = new InternetAddress(user.getEmail());
			InternetAddress from = new InternetAddress(AppConstants.FROM_EMAIL, AppConstants.FROM_NAME);
			MailUtils.sendMail(to, from, AppConstants.FORGOT_PASSWORD_SUBJECT, generateLostPasswordHtmlMsg(headerURL, user.getId(), key), generateLostPasswordTextMsg(headerURL, user.getId(), key, user.getFirstName()));
			return "user.emailText";
		} catch (UnsupportedEncodingException | AddressException e) {
			log.error("UnsupportedEncodingException | Address Exception sending email: " + e.getMessage(), e);
		}		
		
		return "redirect:/";
	}
	
	
private String generateLostPasswordHtmlMsg(String headerURL, Integer userId, String key) {
		
		String msg = "";
		msg+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
		msg+="<html>";
		msg+="<head><title>Create a new password on Contact Form</title></head>";
		msg+="<body>";
		msg+="<div style=\"max-width: 800px; margin: 0; padding: 30px 0;\">";
		msg+="<table width=\"80%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		msg+="<tr>";
		msg+="<td width=\"5%\"></td>";
		msg+="<td align=\"left\" width=\"95%\" style=\"font: 13px/18px Arial, Helvetica, sans-serif;\">";
		msg+="<h2 style=\"font: normal 20px/23px Arial, Helvetica, sans-serif; margin: 0; padding: 0 0 18px; color: black;\">Create a new password</h2>";
		msg+="Forgot your password, huh? No big deal.<br />";
		msg+="To create a new password, just follow this link:<br />";
		msg+="<br />";
		msg+="<big style=\"font: 16px/18px Arial, Helvetica, sans-serif;\"><b><a href=\""+headerURL+"/users/resetPassword/"+userId+"/"+key+"\" style=\"color:#3366cc\" target=\"_blank\">Create a new password</a></b></big><br />";
		msg+="<br />";
		msg+="Link doesn't work? Copy the following link to your browser address bar:<br />";
		msg+="<nobr><a href=\""+headerURL+"/users/resetPassword/"+userId+"/"+key+"\" style=\"color:#3366cc\" target=\"_blank\">"+headerURL+"/users/<wbr>resetPassword/"+userId+"/"+key+"</a></nobr><br />";
		msg+="<br />";
		msg+="<br />";
		msg+="You received this email, because it was requested by a <a href=\""+headerURL+"/\" style=\"color:#3366cc\" target=\"_blank\">Contact Form</a> user. This is part of the procedure to create a new password on the system. If you DID NOT request a new password then please ignore this email and your password will remain the same.<br />";
		msg+="<br />";
		msg+="<br />";
		msg+="Thank you,<br />";
		msg+="The Contact Form Team";
		msg+="</td>";
		msg+="</tr>";
		msg+="</table>";
		msg+="</div>";
		msg+="</body>";
		msg+="</html>";
		return msg;
	}

	private String generateLostPasswordTextMsg(String headerURL, Integer userId, String key, String firstName) {
		String msg = "";
		msg+="Hi "+firstName+",\n";
		msg+="\n";
		msg+="Forgot your password, huh? No big deal.\n";
		msg+="To create a new password, just follow this link:\n";
		msg+="\n";
		msg+=headerURL+"/users/resetPassword/"+userId+"/"+key+"\n";
		msg+="\n";
		msg+="\n";
		msg+="You received this email, because it was requested by a Contact Form user. This is part of the procedure to create a new password on the system. If you DID NOT request a new password then please ignore this email and your password will remain the same.\n";
		msg+="\n";
		msg+="\n";
		msg+="Thank you,\n";
		msg+="The Contact Form Team\n";
		return msg;
	}	

}
