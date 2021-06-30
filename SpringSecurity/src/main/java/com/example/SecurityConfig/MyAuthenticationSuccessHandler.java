package com.example.SecurityConfig;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		    String targetUrl = determineTargetUrl(authentication);
		    if (response.isCommitted()) {
	              System.out.println("Response has already been committed. Unable to redirect to "
	                        + targetUrl);
	        return;
	    }
	    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	    redirectStrategy.sendRedirect(request, response, targetUrl);
		System.out.println("");
	}
	
	protected String determineTargetUrl(final Authentication authentication) {
		 
	    Map<String, String> roleTargetUrlMap = new HashMap<>();
	    roleTargetUrlMap.put("ROLE_user", "/swagger-ui.html");
	 
	    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    for (final GrantedAuthority grantedAuthority : authorities) {
	        String authorityName = grantedAuthority.getAuthority();
	        if(roleTargetUrlMap.containsKey(authorityName)) {
	            return roleTargetUrlMap.get(authorityName);
	        }
	    }
	 
	    throw new IllegalStateException();
	}
	
}
