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
    private List<CustomerAddressModel> previousAddress;
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

	public List<CustomerAddressModel> getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(List<CustomerAddressModel> previousAddress) {
		this.previousAddress = previousAddress;
	}

	public List<ContactModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactModel> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "CustomerModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", company="
				+ company + ", initDate=" + initDate + ", notes=" + notes + ", currentAddress=" + currentAddress
				+ ", previousAddress=" + previousAddress + ", contacts=" + contacts + "]";
	}

}
