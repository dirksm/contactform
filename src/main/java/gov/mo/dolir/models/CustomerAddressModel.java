package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class CustomerAddressModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(CustomerAddressModel.class);
    private Integer customerId;
    private Integer addressId;
    private Date dateAddressFrom;
    private Date dateAddressTo;
    private String notes;

	
    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }


    public Date getDateAddressFrom() {
        return this.dateAddressFrom;
    }

    public void setDateAddressFrom(Date dateAddressFrom) {
        this.dateAddressFrom = dateAddressFrom;
    }


    public Date getDateAddressTo() {
        return this.dateAddressTo;
    }

    public void setDateAddressTo(Date dateAddressTo) {
        this.dateAddressTo = dateAddressTo;
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
					sb.append(getter.invoke(this, null)).append("");
				} catch (Exception e) {
					log.error("Exception outputting toString: " + e.getMessage(), e);
				}
			}
        return sb.toString();
    }

}
