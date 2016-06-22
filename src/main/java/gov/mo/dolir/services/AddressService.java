package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.AddressDAO;
import gov.mo.dolir.models.AddressModel;

 
 
@Service("addressService")
public class AddressService {
 
    private static Logger log = LoggerFactory.getLogger(AddressService.class);

	 
    @Autowired
    private AddressDAO addressDao;
	 

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
    	return addressDao.addAddress(bean);
    }
    
    public int getAddressCount() {
    	return addressDao.getAddressCount();
    }
	 
}
