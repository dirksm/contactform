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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import gov.mo.dolir.constants.AppConstants;
import gov.mo.dolir.services.UserService;

@Component
public class UserFilter implements Filter {

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
		if(userService==null){
            ServletContext servletContext = request.getSession().getServletContext();
            System.err.println("servletContext: "+servletContext);
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            System.err.println("webApplicationContext: "+webApplicationContext);
            userService = webApplicationContext.getBean(UserService.class);
        }
		System.err.println("userService: "+userService);
		if (request.getSession().getAttribute(AppConstants.USER_PROFILE) == null && StringUtils.isNotBlank(request.getRemoteUser())) {
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
