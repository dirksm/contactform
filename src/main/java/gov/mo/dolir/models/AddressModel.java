package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class AddressModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(AddressModel.class);
    private Integer id;
    private Integer customerId;
    private String address1;
    private String address2;
    private String city;
    private Integer state;
    private String shortState;
    private String longState;
    private String zip;
    private String notes;

    public AddressModel() {
    }

    public AddressModel(Integer customerId) {
    	this();
    	setCustomerId(customerId);
    }
	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getShortState() {
		return shortState;
	}

	public void setShortState(String shortState) {
		this.shortState = shortState;
	}

	public String getLongState() {
		return longState;
	}

	public void setLongState(String longState) {
		this.longState = longState;
	}

	public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", customerId=" + customerId + ", address1=" + address1 + ", address2="
				+ address2 + ", city=" + city + ", state=" + state + ", shortState=" + shortState + ", longState="
				+ longState + ", zip=" + zip + ", notes=" + notes + "]";
	}



	
 
}
