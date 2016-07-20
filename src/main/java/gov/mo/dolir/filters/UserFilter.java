package gov.mo.dolir.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import gov.mo.dolir.constants.AppConstants;
import gov.mo.dolir.services.UserService;

@Component("userFilter")
@Configuration
public class UserFilter implements Filter {

	Logger log = LoggerFactory.getLogger(UserFilter.class);
	
	@Autowired
	UserService userService;
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		log.debug("userService: "+userService);
		if(userService==null){
            ServletContext servletContext = request.getSession().getServletContext();
            log.debug("servletContext: "+servletContext);
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            log.debug("webApplicationContext: "+webApplicationContext);
            userService = webApplicationContext.getBean(UserService.class);
        }
		if (request.getSession().getAttribute(AppConstants.USER_PROFILE) == null && StringUtils.isNotBlank(request.getRemoteUser())) {
			log.debug("Setting last log in for " + request.getRemoteUser() + " and populating the user profile in the session.");
			userService.setLastLoggedIn(request.getRemoteUser());
			request.getSession().setAttribute(AppConstants.USER_PROFILE, userService.getUserByUsername(request.getRemoteUser()));
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		 SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
		            filterConfig.getServletContext());		
	}

}
