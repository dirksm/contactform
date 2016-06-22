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
    private String address1;
    private String address2;
    private String city;
    private Integer state;
    private String shortState;
    private String longState;
    private String zip;
    private String notes;

	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
