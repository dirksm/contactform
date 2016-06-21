package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.CustomerDAO;
import gov.mo.dolir.models.CustomerModel;

 
 
@Service("customerService")
public class CustomerService {
 
    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

	 
    @Autowired
    private CustomerDAO customerDao;
	 

    public List<CustomerModel> getCustomerList() {
        return customerDao.getCustomerList();
    }


    public CustomerModel getCustomer(Integer id) {
        return customerDao.getCustomer(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateCustomer(CustomerModel bean) {
        return customerDao.updateCustomer(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteCustomer(Integer id) {
        return customerDao.deleteCustomer(id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
	public int createCustomer(CustomerModel bean) {
		return customerDao.addCustomer(bean);
	}
    
    public int getCustomerCount() {
    	return customerDao.getCustomerCount();
    }
}
