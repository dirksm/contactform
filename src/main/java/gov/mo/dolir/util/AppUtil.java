package gov.mo.dolir.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {

	private static Logger log = LoggerFactory.getLogger(AppUtil.class);
	 
    public static String encodeSHA(String sha) {
		return DigestUtils.sha256Hex(sha);
	}
    
}
