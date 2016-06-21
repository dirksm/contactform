package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.ContactActivitiesDAO;
import gov.mo.dolir.models.ContactActivitiesModel;

 
 
@Service("contactActivitiesService")
public class ContactActivitiesService {
 
    private static Logger log = LoggerFactory.getLogger(ContactActivitiesService.class);

	 
    @Autowired
    private ContactActivitiesDAO contactActivitiesDao;
	 

    public List<ContactActivitiesModel> getContactActivitiesList() {
        return contactActivitiesDao.getContactActivitiesList();
    }


    public ContactActivitiesModel getContactActivities(Integer id) {
        return contactActivitiesDao.getContactActivities(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateContactActivities(ContactActivitiesModel bean) {
        return contactActivitiesDao.updateContactActivities(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteContactActivities(Integer id) {
        return contactActivitiesDao.deleteContactActivities(id);
    }

	 
}
