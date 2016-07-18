package gov.mo.dolir.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.mo.dolir.dao.UserAuthDAO;
import gov.mo.dolir.dao.UserDAO;
import gov.mo.dolir.dao.UserRolesDAO;
import gov.mo.dolir.models.PasswordResetModel;
import gov.mo.dolir.models.UserModel;

@Service("userService")
public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class);

 
    @Autowired
    private UserDAO userDao;
	@Autowired
	private UserRolesDAO userRolesDao;
	@Autowired
	private UserAuthDAO userAuthDao;

    public List<UserModel> getUsersList() {
    	List<UserModel> users = userDao.getUsersList(); 
    	for (UserModel user : users) {
			user.setUserRoles(userRolesDao.getUserRoles(user.getUsername()));
		}
        return users;
    }


    public UserModel getUsers(Integer id) {
    	UserModel user = userDao.getUsers(id);
    	if (user != null) {
			user.setUserRoles(userRolesDao.getUserRoles(user.getUsername()));
		}
        return user;
    }

    public UserModel getUserByUsername(String username) {
    	UserModel user = userDao.getUserByUsername(username);
    	if (user != null) {
			user.setUserRoles(userRolesDao.getUserRoles(user.getUsername()));
		}
    	return user;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public int adduser(UserModel bean) {
    	int numRows = userDao.addUsers(bean);
    	userRolesDao.addUserRoles(bean.getUsername(), UserModel.RoleList.USER.getValue());
    	if (bean.isAdmin()) {
        	userRolesDao.addUserRoles(bean.getUsername(), UserModel.RoleList.ADMIN.getValue());
		}
    	return numRows;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateUsers(UserModel bean) {
    	int numRows = userDao.updateUsers(bean);
    	userRolesDao.deleteUserRoles(bean.getUsername());
    	userRolesDao.addUserRoles(bean.getUsername(), UserModel.RoleList.USER.getValue());
    	if (bean.isAdmin()) {
        	userRolesDao.addUserRoles(bean.getUsername(), UserModel.RoleList.ADMIN.getValue());
		}
        return numRows;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateProfile(UserModel bean) {
    	return userDao.updateUsers(bean);
    }
	 
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteUsers(Integer id) {
    	userRolesDao.deleteUserRoles(userDao.getUsers(id).getUsername());
        return userDao.deleteUsers(id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
	public int setLastLoggedIn(String username) {
    	return userDao.setLastLoggedIn(username);
	}
    
    @Transactional(propagation=Propagation.REQUIRED)
	public int updatePassword(String username, String password, String modifiedUser) {
    	return userDao.updatePassword(username, password, modifiedUser);
	}

    public boolean validateUser(String username, String password) {
    	return userDao.validateUser(username, password);
    }
    
    public boolean doesUserExist(String userName)
    {
		return userAuthDao.doesUserExist(userName);
    }
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String setPasswordResetKey(String username) {
		return userAuthDao.setPasswordResetKey(username);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int resetPassword(Integer id, String username, String password){
		int numrows = 0;
		numrows += userAuthDao.clearPasswordResetKeys(id);
		numrows += userDao.updatePassword(username, password, username);
		return numrows;
	}
	
	public UserModel getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public PasswordResetModel getResetInfo(Integer id) {
		return userAuthDao.getResetInfo(id);
	}
}
