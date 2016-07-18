package gov.mo.dolir.models;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class PasswordResetModel implements Serializable {

	private Integer id;
	private String key;
	private String username;
	private Date requestDate;
	private String password;
	private String password2;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
	public boolean passwordsMatch() {
		return StringUtils.isNotBlank(password) && StringUtils.isNotBlank(password2) && password.equals(password2);
	}
	@Override
	public String toString() {
		return "PasswordResetModel [id=" + id + ", key=" + key + ", username=" + username + ", requestDate="
				+ requestDate + ", password=" + password + ", password2=" + password2 + "]";
	}
	
	

}
