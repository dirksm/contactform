package gov.mo.dolir.models;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerAddressModel extends AddressModel {

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



	
    @Override
	public String toString() {
		return "CustomerAddressModel [customerId=" + customerId + ", addressId=" + addressId + ", dateAddressFrom="
				+ dateAddressFrom + ", dateAddressTo=" + dateAddressTo + ", notes=" + notes + "]";
	}

}
