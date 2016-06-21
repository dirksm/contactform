package gov.mo.dolir.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class ActivityOutcomeModel implements Serializable {

    private static Logger log = LoggerFactory.getLogger(ActivityOutcomeModel.class);
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
