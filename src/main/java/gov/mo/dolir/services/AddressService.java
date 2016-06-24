package gov.mo.dolir.services;
 
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.AddressDAO;
import gov.mo.dolir.dao.CustomerAddressDAO;
import gov.mo.dolir.models.AddressModel;
import gov.mo.dolir.models.CustomerAddressModel;

 
 
@Service("addressService")
public class AddressService {
 
    private static Logger log = LoggerFactory.getLogger(AddressService.class);

	 
    @Autowired
    private AddressDAO addressDao;
    
    @Autowired
    private CustomerAddressDAO customerAddressDao;
	 

    public List<AddressModel> getAddressList() {
        return addressDao.getAddressList();
    }


    public AddressModel getAddress(Integer id) {
        return addressDao.getAddress(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateAddress(AddressModel bean) {
        return addressDao.updateAddress(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteAddress(Integer id) {
        return addressDao.deleteAddress(id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int createAddress(AddressModel bean) {
    	int addressId = addressDao.addAddress(bean);
    	if (addressId != 0 && bean.getCustomerId() != 0) {
			addressDao.inactivateAddressForCustomer(bean.getCustomerId());
			CustomerAddressModel cam = new CustomerAddressModel();
			cam.setAddressId(addressId);
			cam.setCustomerId(bean.getCustomerId());
			cam.setDateAddressFrom(new Date());
			customerAddressDao.addCustomerAddress(cam);
		}
    	return addressId;
    }
    
    public int getAddressCount() {
    	return addressDao.getAddressCount();
    }
	 
}
