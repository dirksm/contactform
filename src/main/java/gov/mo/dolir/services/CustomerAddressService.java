package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.CustomerAddressDAO;
import gov.mo.dolir.models.CustomerAddressModel;

 
 
@Service("customerAddressService")
public class CustomerAddressService {
 
    private static Logger log = LoggerFactory.getLogger(CustomerAddressService.class);

	 
    @Autowired
    private CustomerAddressDAO customerAddressDao;
	 

    public List<CustomerAddressModel> getCustomerAddressList() {
        return customerAddressDao.getCustomerAddressList();
    }


    public CustomerAddressModel getCustomerAddress(Integer customerId) {
        return customerAddressDao.getCustomerAddress(customerId);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateCustomerAddress(CustomerAddressModel bean) {
        return customerAddressDao.updateCustomerAddress(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteCustomerAddress(Integer customerId) {
        return customerAddressDao.deleteCustomerAddress(customerId);
    }

	 
}
