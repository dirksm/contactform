package gov.mo.dolir.services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.mo.dolir.dao.StatesDAO;
import gov.mo.dolir.models.StatesModel;

 
 
@Service("statesService")
public class StatesService {
 
    private static Logger log = LoggerFactory.getLogger(StatesService.class);

	 
    @Autowired
    private StatesDAO statesDao;
	 

    public List<StatesModel> getStatesList() {
        return statesDao.getStatesList();
    }


    public StatesModel getStates(Integer id) {
        return statesDao.getStates(id);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateStates(StatesModel bean) {
        return statesDao.updateStates(bean);
    }

	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteStates(Integer id) {
        return statesDao.deleteStates(id);
    }

	 
}
