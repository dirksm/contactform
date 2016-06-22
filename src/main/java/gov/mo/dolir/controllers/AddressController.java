package gov.mo.dolir.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.mo.dolir.models.AddressModel;
import gov.mo.dolir.services.AddressService;
import gov.mo.dolir.services.StatesService;

@Controller
@RequestMapping(value="/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	StatesService statesService;
	
	@RequestMapping(value={"/list", "/"})
	public String getAddresss(ModelMap map) {
		List<AddressModel> list = addressService.getAddressList();
		map.addAttribute("addresses", list);
		return "address.list";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String getAddressForEdit(ModelMap map, @PathVariable String id) {
		map.addAttribute("addressForm", addressService.getAddress(new Integer(id)));
		map.addAttribute("stateList", statesService.getStatesList());
		return "address.edit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String saveAddress(@ModelAttribute("addressForm") AddressModel model, ModelMap map) {
		System.out.println(model);
		addressService.updateAddress(model);
		return "redirect:/addresses/list";
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String newAddress(ModelMap map) {
		map.addAttribute("addressForm", new AddressModel());
		map.addAttribute("stateList", statesService.getStatesList());
		return "address.create";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveNewAddress(@ModelAttribute("addressForm") AddressModel model, ModelMap map) {
		addressService.createAddress(model);
		return "redirect:/addresses/list";
	}

	@RequestMapping(value="/delete/{id}")
	public String saveNewAddress(@PathVariable String id, ModelMap map) {
		addressService.deleteAddress(new Integer(id));
		return "redirect:/addresses/list";
	}

}
