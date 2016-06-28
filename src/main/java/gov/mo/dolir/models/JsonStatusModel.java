package gov.mo.dolir.models;

import java.io.Serializable;

public class JsonStatusModel implements Serializable {

	public final Object data;
	public final String httpStatus;
	
	public JsonStatusModel(Object obj, String status) {
		this.data = obj;
		this.httpStatus = status;
	}

	public Object getData() {
		return data;
	}

	public String getHttpStatus() {
		return httpStatus;
	}
	
	
}
