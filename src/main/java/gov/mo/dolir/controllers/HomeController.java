package gov.mo.dolir.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.mo.dolir.models.JsonStatusModel;
import gov.mo.dolir.services.AddressService;
import gov.mo.dolir.services.ContactService;
import gov.mo.dolir.services.CustomerService;

@Controller
public class HomeController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ContactService contactService;

	@Autowired
	AddressService addressService;
	
	@RequestMapping(value="/")
	public String mainIndex(ModelMap map){
		map.addAttribute("customerCount", customerService.getCustomerCount());
		map.addAttribute("contactCount", contactService.getContactCount());
		map.addAttribute("addressCount", addressService.getAddressCount());
		
		return "main.index";
	}
	
	@RequestMapping(value="/changeSkin/{skin}")
	public @ResponseBody JsonStatusModel changeSkin(@PathVariable String skin, HttpServletRequest request) {
		request.getSession().setAttribute("skin", skin);
		return new JsonStatusModel(skin, HttpStatus.OK.toString());
	}
}
