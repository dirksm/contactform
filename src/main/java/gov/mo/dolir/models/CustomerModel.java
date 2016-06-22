package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class CustomerModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(CustomerModel.class);
    private Integer id;
    private String firstName;
    private String lastName;
    private String company;
    private Date initDate;
    private String notes;
    private AddressModel currentAddress;
    private List<AddressModel> previousAddress;
    private List<ContactModel> contacts;

	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getInitDate() {
        return this.initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }


    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



	
    public AddressModel getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(AddressModel currentAddress) {
		this.currentAddress = currentAddress;
	}

	public List<AddressModel> getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(List<AddressModel> previousAddress) {
		this.previousAddress = previousAddress;
	}

	public List<ContactModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactModel> contacts) {
		this.contacts = contacts;
	}

	public String toString() {
				StringBuffer sb = new StringBuffer();
				Field[] fields = this.getClass().getDeclaredFields();
        		for (Field field : fields) {
				try {
					Method getter = null;
					if (!"log".equals(field.getName())) {
						if(field.getType() == Boolean.class) {
							if (StringUtils.isAllUpperCase(field.getName())) {
								getter = this.getClass().getMethod("is"+field.getName(), null);
							} else {
						        getter = this.getClass().getMethod("is"+StringUtils.capitalize(field.getName()), null);
							}
						} else {
							if (StringUtils.isAllUpperCase(field.getName())) {
								getter = this.getClass().getMethod("get"+field.getName(), null);
							} else {
						        getter = this.getClass().getMethod("get"+StringUtils.capitalize(field.getName()), null);
							}
						}
						sb.append(field.getName()+": ");
						sb.append(getter.invoke(this, null)).append(" ");
					}
				} catch (Exception e) {
					log.error("Exception outputting toString: " + e.getMessage(), e);
				}
			}
        return sb.toString();
    }

}
