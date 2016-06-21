package gov.mo.dolir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
