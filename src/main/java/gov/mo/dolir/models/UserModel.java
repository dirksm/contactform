package gov.mo.dolir.models;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

public class UserModel implements Serializable {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String oldPassword;
    private String password;
    private String password2;
	private String email;
    private boolean activated;
    private String newPasswordKey;
    private Date newPasswordRequested;
    private String position;
    private Date lastLogin;
    private Date created;
    private Date modified;
    private String createdBy;
    private String modifiedBy;
    private TreeSet<String> userRoles;
    
    public static enum RoleList {
    	ADMIN("admin"), USER("user");

    	private String value;
    	
    	private RoleList(String value) {
    		this.value = value;
    	}
    	
    	public String getValue() {
    		return this.value;
    	}
    };
    
    public boolean isAdmin() {
    	return this.userRoles!=null && !this.userRoles.isEmpty() && this.userRoles.contains(RoleList.ADMIN.getValue());
    }
    
    public void setAdmin(boolean adminFlag) {
		if (this.userRoles == null ) {
			this.userRoles = new TreeSet<String>();
		}
    	if (adminFlag) {
			this.userRoles.add(RoleList.ADMIN.getValue());
		} else {
			this.userRoles.remove(RoleList.ADMIN.getValue());
		}
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getNewPasswordKey() {
		return newPasswordKey;
	}

	public void setNewPasswordKey(String newPasswordKey) {
		this.newPasswordKey = newPasswordKey;
	}

	public Date getNewPasswordRequested() {
		return newPasswordRequested;
	}

	public void setNewPasswordRequested(Date newPasswordRequested) {
		this.newPasswordRequested = newPasswordRequested;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public TreeSet<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(TreeSet<String> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", oldPassword=" + oldPassword + ", password=" + password + ", password2=" + password2 + ", email="
				+ email + ", activated=" + activated + ", newPasswordKey=" + newPasswordKey + ", newPasswordRequested="
				+ newPasswordRequested + ", position=" + position + ", lastLogin=" + lastLogin + ", created=" + created
				+ ", modified=" + modified + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", userRoles="
				+ userRoles + "]";
	}    

}
