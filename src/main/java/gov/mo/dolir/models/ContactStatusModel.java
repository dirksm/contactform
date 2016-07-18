package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class ContactStatusModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(ContactStatusModel.class);
    private Integer id;
    private String description;

	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



	
    @Override
	public String toString() {
		return "ContactStatusModel [id=" + id + ", description=" + description + "]";
	}

}
