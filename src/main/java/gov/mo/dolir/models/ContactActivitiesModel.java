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



	
    @Override
	public String toString() {
		return "ContactActivitiesModel [id=" + id + ", contactId=" + contactId + ", activityTypeId=" + activityTypeId
				+ ", activityOutcomeId=" + activityOutcomeId + ", activityDate=" + activityDate + ", notes=" + notes
				+ "]";
	}

}
