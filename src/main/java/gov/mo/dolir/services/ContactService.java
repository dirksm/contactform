package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.ContactDAO;
import gov.mo.dolir.models.ContactModel;

 
 
@Service("contactService")
public class ContactService {
 
    private static Logger log = LoggerFactory.getLogger(ContactService.class);

	 
    @Autowired
    private ContactDAO contactDao;
	 

    public List<ContactModel> getContactList() {
        return contactDao.getContactList();
    }


    public ContactModel getContact(Integer id) {
        return contactDao.getContact(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateContact(ContactModel bean) {
        return contactDao.updateContact(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteContact(Integer id) {
        return contactDao.deleteContact(id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int createContact(ContactModel bean) {
    	return contactDao.addContact(bean);
    }
	 
    public int getContactCount() {
    	return contactDao.getContactCount();
    }
}
