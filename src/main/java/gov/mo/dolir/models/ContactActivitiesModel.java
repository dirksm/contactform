package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class ContactActivitiesModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(ContactActivitiesModel.class);
    private Integer id;
    private Integer contactId;
    private Integer activityTypeId;
    private Integer activityOutcomeId;
    private Date activityDate;
    private String notes;

	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getContactId() {
        return this.contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }


    public Integer getActivityTypeId() {
        return this.activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }


    public Integer getActivityOutcomeId() {
        return this.activityOutcomeId;
    }

    public void setActivityOutcomeId(Integer activityOutcomeId) {
        this.activityOutcomeId = activityOutcomeId;
    }


    public Date getActivityDate() {
        return this.activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
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
