package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class ContactModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(ContactModel.class);
    private Integer id;
    private Integer customerId;
    private Integer statusCd;
    private String email;
    private String website;
    private Integer salutation;
    private String contactName;
    private String title;
    private String dept;
    private String workPhone;
    private String cellPhone;
    private String fax;
    private String notes;
    private String companyName;
    private String status;
    
    public ContactModel() {
    	
    }
    
    public ContactModel(Integer customerId) {
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
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public Integer getStatusCd() {
        return this.statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public Integer getSalutation() {
        return this.salutation;
    }

    public void setSalutation(Integer salutation) {
        this.salutation = salutation;
    }


    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


    public String getWorkPhone() {
        return this.workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }


    public String getCellPhone() {
        return this.cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }


    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



	
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", customerId=" + customerId + ", statusCd=" + statusCd + ", email=" + email
				+ ", website=" + website + ", salutation=" + salutation + ", contactName=" + contactName + ", title="
				+ title + ", dept=" + dept + ", workPhone=" + workPhone + ", cellPhone=" + cellPhone + ", fax=" + fax
				+ ", notes=" + notes + ", companyName=" + companyName + ", status=" + status + "]";
	}

}
