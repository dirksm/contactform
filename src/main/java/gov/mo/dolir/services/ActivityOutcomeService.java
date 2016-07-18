package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.ActivityOutcomeDAO;
import gov.mo.dolir.models.ActivityOutcomeModel;

 
 
@Service("activityOutcomeService")
public class ActivityOutcomeService {
 
    private static Logger log = LoggerFactory.getLogger(ActivityOutcomeService.class);

	 
    @Autowired
    private ActivityOutcomeDAO activityOutcomeDao;
	 

    public List<ActivityOutcomeModel> getActivityOutcomeList() {
        return activityOutcomeDao.getActivityOutcomeList();
    }


    public ActivityOutcomeModel getActivityOutcome(Integer id) {
        return activityOutcomeDao.getActivityOutcome(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateActivityOutcome(ActivityOutcomeModel bean) {
        return activityOutcomeDao.updateActivityOutcome(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteActivityOutcome(Integer id) {
        return activityOutcomeDao.deleteActivityOutcome(id);
    }

	 
}
