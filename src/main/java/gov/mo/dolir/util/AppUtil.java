package gov.mo.dolir.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {

	private static Logger log = LoggerFactory.getLogger(AppUtil.class);
	 
    public static String encodeSHA(String sha) {
		return DigestUtils.sha256Hex(sha);
	}
    
    public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		boolean isValid = true;
		try {
			Matcher match = pattern.matcher(email);
			if (StringUtils.isNotBlank(email) && !match.matches()) {
				isValid = false;
			}
		} catch (Exception e) {
			isValid = false;
		}
		return isValid;
	}
    
}
