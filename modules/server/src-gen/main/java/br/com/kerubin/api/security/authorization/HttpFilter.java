/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@DependsOn("serviceConfig")
public class HttpFilter implements Filter {
	
	@Inject
	private ServiceConfig serviceConfig;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", serviceConfig.getAllowOrigin());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        String url = request.getRequestURL().toString();
        log.info("request.getRequestURL:", url);
        
        log.info("doFilter: isEnableHttps: {}, allowOrigin: {}", serviceConfig.isEnableHttps(), serviceConfig.getAllowOrigin());
        log.info("request.getHeader(Origin): ", request.getHeader("Origin"));
		
		if ("OPTIONS".equals(request.getMethod()) && serviceConfig.getAllowOrigin().equals(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        	response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
			log.info("Aceitou OPTIONS para request.getRequestURL:", url);
		} else {
			chain.doFilter(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 
	}


	@Override
	public void destroy() {
		// 
	}

}

