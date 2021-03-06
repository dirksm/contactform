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
	public String getCustomers(ModelMap map) throws Exception {
		List<CustomerModel> list = customerService.getCustomerList();
		map.addAttribute("customers", list);
		throw new Exception("thrown exception.....");
//		return "customer.list";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String getcustomerForEdit(ModelMap map, @PathVariable String id) {
		map.addAttribute("customerForm", customerService.getCustomer(new Integer(id)));
		return "customer.edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customerForm") CustomerModel model, ModelMap map) {
		customerService.updateCustomer(model);
		return "redirect:/customers/list";
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String newCustomer(ModelMap map) {
		map.addAttribute("customerForm", new CustomerModel());
		return "customer.create";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveNewCustomer(@ModelAttribute("customerForm") CustomerModel model, ModelMap map) {
		customerService.createCustomer(model);
		return "redirect:/customers/list";
	}

	@RequestMapping(value="/delete/{id}")
	public String saveNewCustomer(@PathVariable String id, ModelMap map) {
		customerService.deleteCustomer(new Integer(id));
		return "redirect:/customers/list";
	}
	
	@RequestMapping(value="/view/{id}")
	public String viewCustomer(@PathVariable String id, ModelMap map) {
		map.addAttribute("customer", customerService.getCustomer(new Integer(id)));
		return "customer.view";
	}

}
