package gov.mo.dolir.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.mo.dolir.models.ContactModel;
import gov.mo.dolir.services.ContactService;
import gov.mo.dolir.services.ContactStatusService;
import gov.mo.dolir.services.CustomerService;

@Controller
@RequestMapping(value="/contacts")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ContactStatusService contactStatusService;
	
	@RequestMapping(value={"/list", "/"})
	public String getContacts(ModelMap map) {
		List<ContactModel> list = contactService.getContactList();
		map.addAttribute("contacts", list);
		return "contact.list";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String getContactForEdit(ModelMap map, @PathVariable String id) {
		map.addAttribute("customers", customerService.getCustomerList());
		map.addAttribute("contactForm", contactService.getContact(new Integer(id)));
		map.addAttribute("statuses", contactStatusService.getContactStatusList());
		return "contact.edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String saveContact(@ModelAttribute("contactForm") ContactModel model, ModelMap map) {
		contactService.updateContact(model);
		return "redirect:/contacts/list";
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String newContact(ModelMap map) {
		map.addAttribute("contactForm", new ContactModel());
		map.addAttribute("customers", customerService.getCustomerList());
		map.addAttribute("statuses", contactStatusService.getContactStatusList());
		return "contact.create";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveNewContact(@ModelAttribute("contactForm") ContactModel model, ModelMap map) {
		contactService.createContact(model);
		return "redirect:/contacts/list";
	}

	@RequestMapping(value="/delete/{id}")
	public String saveNewContact(@PathVariable String id, ModelMap map) {
		contactService.deleteContact(new Integer(id));
		return "redirect:/contacts/list";
	}

}
