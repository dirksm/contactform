package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.ActivityTypeDAO;
import gov.mo.dolir.models.ActivityTypeModel;

 
 
@Service("activityTypeService")
public class ActivityTypeService {
 
    private static Logger log = LoggerFactory.getLogger(ActivityTypeService.class);

	 
    @Autowired
    private ActivityTypeDAO activityTypeDao;
	 

    public List<ActivityTypeModel> getActivityTypeList() {
        return activityTypeDao.getActivityTypeList();
    }


    public ActivityTypeModel getActivityType(Integer id) {
        return activityTypeDao.getActivityType(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateActivityType(ActivityTypeModel bean) {
        return activityTypeDao.updateActivityType(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteActivityType(Integer id) {
        return activityTypeDao.deleteActivityType(id);
    }

	 
}
