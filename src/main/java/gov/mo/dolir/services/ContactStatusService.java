package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.ContactStatusDAO;
import gov.mo.dolir.models.ContactStatusModel;

 
 
@Service("contactStatusService")
public class ContactStatusService {
 
    private static Logger log = LoggerFactory.getLogger(ContactStatusService.class);

	 
    @Autowired
    private ContactStatusDAO contactStatusDao;
	 

    public List<ContactStatusModel> getContactStatusList() {
        return contactStatusDao.getContactStatusList();
    }


    public ContactStatusModel getContactStatus(Integer id) {
        return contactStatusDao.getContactStatus(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateContactStatus(ContactStatusModel bean) {
        return contactStatusDao.updateContactStatus(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteContactStatus(Integer id) {
        return contactStatusDao.deleteContactStatus(id);
    }

	 
}
