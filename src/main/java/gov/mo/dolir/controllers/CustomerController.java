package gov.mo.dolir.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.mo.dolir.models.CustomerModel;
import gov.mo.dolir.services.CustomerService;

@Controller
@RequestMapping(value="/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value={"/list", "/"})
	public String getContacts(ModelMap map) {
		List<CustomerModel> list = customerService.getCustomerList();
		map.addAttribute("customers", list);
		return "customer.list";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String getcustomerForEdit(ModelMap map, @PathVariable String id) {
		map.addAttribute("customerForm", customerService.getCustomer(new Integer(id)));
		return "customer.edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String saveContact(@ModelAttribute("customerForm") CustomerModel model, ModelMap map) {
		customerService.updateCustomer(model);
		return "redirect:/customers/list";
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String newContact(ModelMap map) {
		map.addAttribute("customerForm", new CustomerModel());
		return "customer.create";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveNewContact(@ModelAttribute("customerForm") CustomerModel model, ModelMap map) {
		customerService.createCustomer(model);
		return "redirect:/customers/list";
	}

	@RequestMapping(value="/delete/{id}")
	public String saveNewContact(@PathVariable String id, ModelMap map) {
		customerService.deleteCustomer(new Integer(id));
		return "redirect:/customers/list";
	}

}
