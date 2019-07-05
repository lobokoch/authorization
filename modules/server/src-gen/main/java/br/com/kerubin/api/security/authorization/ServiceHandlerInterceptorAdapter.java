/**********************************************************************************************
Code generated with MKL Plug-in version: 6.10.2
Code generated at time stamp: 2019-07-04T22:19:23.242
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.kerubin.api.database.core.ServiceContext;

public class ServiceHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	
	public static final String HEADER_USER = "X-User-Header";
	public static final String HEADER_TENANT = "X-Tenant-Header";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		updateServiceContext(request);
		
		return true;
	}
	
	private void updateServiceContext(HttpServletRequest request) {
		String currentTenant = request.getHeader(HEADER_TENANT);
		String currentUser = request.getHeader(HEADER_USER);
		
		ServiceContext.setTenant(currentTenant);
		ServiceContext.setUser(currentUser);
		
		ServiceContext.setDomain(SecurityAuthorizationConstants.DOMAIN);
		ServiceContext.setService(SecurityAuthorizationConstants.SERVICE);
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		ServiceContext.clear();
		
	}

}

