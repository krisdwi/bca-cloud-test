package bca.pricing.config.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bca.pricing.config.token.JWTTokenUtil;
import io.jsonwebtoken.Claims;

@Component
public class AppAuthFilter implements Filter {
	
	Logger logger = LogManager.getLogger(AppAuthFilter.class);

	@Autowired
	JWTTokenUtil jwtTokenUtil;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		boolean isvalid = false;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String authSessionId = request.getHeader("Authorization");
		MDC.put("sessionid", null == authSessionId ? "no-auth" : authSessionId);
		
		if (isAllowedAnonymousURL(request)) {
			MDC.put("userid", "-");
			isvalid = true;
		}else if (StringUtils.isNotBlank(authSessionId)) {
			String userId = "";
			
			// Get JWT
			Claims jwtBody = jwtTokenUtil.getAllClaimsFromToken(authSessionId);
			userId = String.valueOf(jwtBody.get("sub"));
			
			MDC.put("userid", userId);
			
			isvalid = true;
		} else {
			MDC.put("userid", "-");
			isvalid = false;
		}
		
		if (isvalid) {
			
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(servletRequest, servletResponse);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendRedirect(request.getContextPath() + "/unauthorized");
			logger.info("Unauthorized access attempt from IP : {}, PC Name : {}", request.getRemoteAddr(),
					request.getRemoteHost());
		}
	}
	
	private boolean isAllowedAnonymousURL(HttpServletRequest request) {
		List<String> allowedAnonymousURL = new ArrayList<>();
		allowedAnonymousURL.add("/pricing-svc-int/users/login");
		allowedAnonymousURL.add("/pricing-svc-int/unauthorized");
		
		return isRequestedUrlIsAllowed(request, allowedAnonymousURL);
	}
	
	private boolean isRequestedUrlIsAllowed(HttpServletRequest request, List<String> allowedURLs) {
		boolean retVal = false;
		String uri = request.getRequestURI();
		logger.info("URI: " + uri);

		for (String url : allowedURLs) {
			if (uri.startsWith(url)) {
				retVal = true;
				break;
			}
		}

		return retVal;
	}
}
